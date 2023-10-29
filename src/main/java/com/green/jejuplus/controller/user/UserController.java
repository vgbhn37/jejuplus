package com.green.jejuplus.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.green.jejuplus.dto.user.KakaoProfile;
import com.green.jejuplus.dto.user.OAuthToken;
import com.green.jejuplus.dto.user.SignInFormDto;
import com.green.jejuplus.dto.user.SignUpFormDto;
import com.green.jejuplus.dto.user.UserUpdateDto;
import com.green.jejuplus.handler.exception.CustomException;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.service.user.EmailService;
import com.green.jejuplus.service.user.UserService;
import com.green.jejuplus.util.Define;
import com.green.jejuplus.util.RandomCodeGenerator;




@Controller
@RequestMapping("/user")
public class UserController {

	@Value("{tenco.key}")
	private String tencoKey;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired // DI 처리
	private HttpSession session;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 사용자 등록 폼을 표시하는 메서드
	@GetMapping("/register")
	public String showRegistrationForm() {
		return "user/register";
	}

	// 사용자 등록 요청을 처리하는 메서드
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestParam("email") String email,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("fullname") String fullname,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("code") String code,
			HttpSession session,
			SignUpFormDto signUpFormDto) {
		String savedCode = (String) session.getAttribute("randomCode");

		// 1. 유효성 검사
		if(signUpFormDto.getUsername() == null 
				|| signUpFormDto.getUsername().isEmpty() ) {
			throw new CustomException("아이디를 입력하세요.",
					HttpStatus.BAD_REQUEST);		
		}
		if(signUpFormDto.getPassword() == null
				|| signUpFormDto.getPassword().isEmpty() ) {
			throw new CustomException("비밀번호를 입력하세요.",
					HttpStatus.BAD_REQUEST);	
		}
		if(signUpFormDto.getEmail() == null
				|| signUpFormDto.getEmail().isEmpty() ) {
			throw new CustomException("email을 입력하세요.",
					HttpStatus.BAD_REQUEST);	
		}
		if(signUpFormDto.getFullname() == null
				|| signUpFormDto.getFullname().isEmpty() ) {
			throw new CustomException("이름을 입력하세요.",
					HttpStatus.BAD_REQUEST);	
		}
		if(signUpFormDto.getPhoneNumber() == null
				|| signUpFormDto.getPhoneNumber().isEmpty() ) {
			throw new CustomException("전화번호를 입력해주세요.",
					HttpStatus.BAD_REQUEST);	
		}



		// 아이디 중복 확인
		if (!userService.isUsernameUnique(username)) {
			return ResponseEntity.badRequest().body("아이디가 이미 사용 중입니다.");
		}

		// 이메일 중복 확인
		if (!userService.isEmailUnique(email)) {
			return ResponseEntity.badRequest().body("이메일이 이미 사용 중입니다.");
		}



		if (savedCode != null && savedCode.equals(code)) {
			// 인증 코드가 일치하는 경우, 사용자 정보를 데이터베이스에 저장
			signUpFormDto.setEmail(email);
			signUpFormDto.setUsername(username);
			signUpFormDto.setPassword(password);
			signUpFormDto.setFullname(fullname);
			signUpFormDto.setPhoneNumber(phoneNumber);
			userService.registerUser(signUpFormDto);

			return ResponseEntity.ok("Registration successful");
		} else {
			return ResponseEntity.badRequest().body("Verification failed");
		}
	}

	// 인증 코드를 전송하는 메서드
	@PostMapping("/send-verification-code")
	public ResponseEntity<String> sendVerificationCode(@RequestParam("email") String email, HttpSession session) {
		// 이메일 검증 로직을 추가하여 유효한 이메일에만 코드를 전송
		// 여기서는 간단한 예시로 모든 이메일에 코드를 전송하고 있습니다.

		String randomCode = RandomCodeGenerator.generateRandomCode();
		session.setAttribute("randomCode", randomCode);

		// 이메일로 코드 전송
		emailService.sendVerificationCode(email, randomCode);

		return ResponseEntity.ok("Verification code sent");
	}

	// 인증 코드를 확인하는 메서드
	@PostMapping("/verify-code")
	public ResponseEntity<String> verifyCode(@RequestParam("code") String code, HttpSession session) {
		String savedCode = (String) session.getAttribute("randomCode");

		if (savedCode != null && savedCode.equals(code)) {
			// 인증 코드가 일치하는 경우
			return ResponseEntity.ok("Verification successful");
		} else {
			return ResponseEntity.ok("Verification failed");
		}
	}
	// 아이디
	@GetMapping("/check-username")
	public ResponseEntity<String> checkUsername(@RequestParam("username") String username) {
		if (userService.isUsernameUnique(username)) {
			return ResponseEntity.ok("사용 가능한 아이디입니다.");
		} else {
			return ResponseEntity.ok("이미 사용중인 아이디입니다.");
		}
	}
	// 이메일
	@GetMapping("/check-email")
	public ResponseEntity<String> checkEmail(@RequestParam("email") String email) {
		if (userService.isEmailUnique(email)) {
			return ResponseEntity.ok("사용 가능한 이메일 입니다.");
		} 

		else {
			return ResponseEntity.ok("이미 사용중인 이메일 입니다.");
		}
	}
	
	@PostMapping("/check-update-password/{userId}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> checkAndUpdatePassword(@RequestBody Map<String, String> request, @PathVariable("userId") int userId) {
	    User user = userService.getUserPassword(userId);

	    if (user != null) {
	        String currentPassword = request.get("currentPassword");
	        String newPassword = request.get("newPassword");
	        
	        if (passwordEncoder.matches(currentPassword, user.getPassword())) {
	            // 현재 비밀번호가 일치할 경우 비밀번호를 업데이트합니다.
	            user.setPassword(newPassword);
	            userService.updateUserPassword(user); // 변경된 정보를 저장
	            Map<String, String> response = new HashMap<>();
	            response.put("status", "PasswordUpdated");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            Map<String, String> response = new HashMap<>();
	            response.put("status", "PasswordMismatch");
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }
	    } else {
	        Map<String, String> response = new HashMap<>();
	        response.put("status", "UserNotFound");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}

	// 로그인 페이지 요청
	// http://localhost:80/user/sign-in
	@GetMapping({"/sign-in", ""})
	public String signIn() {
		return "user/signIn";
	}

	@PostMapping("/sign-in")
	public String signInProc(SignInFormDto signInFormDto) {

		// 1. 유효성 검사
		if(signInFormDto.getUsername() == null || 
				signInFormDto.getUsername().isEmpty()) {
			throw new CustomException("username을 입력하시오", HttpStatus.BAD_REQUEST);
		}
		if(signInFormDto.getPassword() == null || 
				signInFormDto.getPassword().isEmpty()) {
			throw new CustomException("password를 입력하시오", HttpStatus.BAD_REQUEST);
		}
		// 2. 서비스 -> 인증된 사용자 여부 확인
		// principal <-- 접근 주체
		User principal = userService.signIn(signInFormDto);
		principal.setPassword(null);
		// 3. 쿠키 + 세션 
		session.setAttribute(Define.PRINCIPAL, principal);

		return "redirect:/main";
	}

	/**
	 * 로그아웃 처리
	 * @return 리다이렉트 - 로그인 페이지 이동
	 */
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/user/sign-in";
	}

	// http://localhost:80/user/kakao/callback?code="ZESasa"
	@GetMapping("kakao/callback")
	// @ResponseBody // 이녀석은 data 반환 명시
	public String kakaoCallback(@RequestParam String code) { // 토큰이 여기 code로 닮김
		System.out.println("메서드 동작");

		// POST 방식 - exchange() 메서드 활용
		// Header 헤더 구성
		// body 구성

		RestTemplate rt = new RestTemplate();
		// 헤더 구성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");    // 문서 토큰란에헤더 형식 키,value 값 가져와서 복붙



		// body 구성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");		
		params.add("client_id", "1c9a0248b81dbbc743e8918bc64a86e5");		// value 값에 아까 발급받아서 복사해둔 RESTAPI 넣으란거임
		params.add("redirect_uri", "http://localhost:80/user/kakao/callback");	// value 값에 아까 발급받아서 복사해둔 RESTURI 넣으란거임	
		params.add("code", code); 

		// HttpEntity 결합 (헤더 + 바디)
		HttpEntity<MultiValueMap<String, String>> reqMes 
		= new HttpEntity<>(params, headers);

		// Http 요청
		ResponseEntity<OAuthToken> response  = rt.exchange("https://kauth.kakao.com/oauth/token",
				HttpMethod.POST, reqMes, OAuthToken.class);     // 토큰 받기에 있던 적혀있던 복사한 URL 넣어주는 곳, POST로 받겠다고 되있었음

		// 1. DTO 파싱
		System.out.println("액세트 토근 확인 " + response.getBody().toString());
		// 액세스 토큰
		// 엑세스 토근 --> 카카오 서버 (정보)

		// 문서 확인 - 정보 요청 주소 
		System.out.println("--------------------------------");
		RestTemplate ret2 = new RestTemplate();

		// 헤더 생성
		HttpHeaders headers2 = new HttpHeaders();   // response.getBody().getAccessToken() --> 액세스 토큰
		headers2.add("Authorization", "Bearer " + response.getBody().getAccessToken()); // Bearer 뒤에 공백 한칸도 같이 복사해옴
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); 		
		// 바디 생성 - 생략 , GET방식은 바디 없어도 됨
		HttpEntity<MultiValueMap<String, String>> kokaoInfo
		= new HttpEntity<>(headers2); // 바디 없이 헤더만 추가

		// HTTP 요청
		ResponseEntity<KakaoProfile> response2 = ret2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, 
				kokaoInfo, KakaoProfile.class); 
		System.out.println("----------------------");
		System.out.println(response2.getBody().getKakaoAccount().getEmail());

		System.out.println("------------카카오 서버에 정보 받기 완료---------------");


		// 1. 회원 가입 여부 확인 - 최초 사용자 라면
		// - 최초 사용자 라면 우리 회원 가입 맞는 형식을 만들어서 회원 가입 처리
		// DB -> user_tb --> username, password, fullname
		// password <-- 직접 만들어서 넣어야 합니다.
		// 소셜 로그인 사용자는 모든 패스워드가 동일 합니다.

		// username -> 동일한 값이 저장되지 않도록 처리

		KakaoProfile kakaoProfile = response2.getBody();

		SignUpFormDto signUpFormDto = SignUpFormDto
				.builder()
				.username(kakaoProfile.getKakaoAccount().getEmail() + "_" + kakaoProfile.getId())
				.fullname(kakaoProfile.getProperties().getNickname())
				.password(tencoKey)
				.email(kakaoProfile.getKakaoAccount().getEmail())
				.build();


		User olderUser = userService.searchUsername(signUpFormDto.getUsername());
		if(olderUser == null) {
			// 사용자가 최초 소셜 로그인 사용자면 자동 회원 가입처리
			userService.registerUserKakao(signUpFormDto);
			olderUser.setUsername(signUpFormDto.getUsername());
			olderUser.setFullname(signUpFormDto.getFullname());

		}
		olderUser.setPassword(null);
		// null 아닌 경우 처리
		// 그게 아니라면 바로 세션에 데이터 등록 로그인 처리
		session.setAttribute(Define.PRINCIPAL,olderUser); // 로그인 처리


		System.out.println("signUpFormDto : " + signUpFormDto);

		return "redirect:/main";

	}

	@GetMapping("/userUpdate/{userId}")
	public String updateForm(@PathVariable("userId") int userId,Model model) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		System.out.println("로그인확인:" + user);


		UserUpdateDto userDetailDto = userService.findUser(userId);
		if(userDetailDto==null) {
			model.addAttribute("userDetailDto", null);
		} else {
			model.addAttribute("userDetailDto",userDetailDto);
		}
		return "/user/update";
	}

	@PostMapping("/userUpdate/{userId}")
	public String updateFormProc(@PathVariable("userId") int userId, UserUpdateDto userUpdateDto, Model model) {

		int result = userService.userDetailUpdate(userId, userUpdateDto);

		if (result > 0) {
			model.addAttribute("message", "success");
		} else {
			model.addAttribute("message", "failure");
		}

		return "redirect:/user/userUpdate/" + userId;
	}

	@PostMapping("/update-email/{userId}")
	public ResponseEntity<String> updateEmail(@RequestParam("email") String email,@PathVariable("userId") int userId ) {
		// Assuming you have a userService or repository to update the email in the database
		boolean emailUpdated = userService.updateEmail(email,userId);
		System.out.println(" 컨트롤러 : " + email);
		System.out.println(" 컨트롤러 : " + userId);
		if (emailUpdated) {
			return new ResponseEntity<>("Email updated successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Email update failed", HttpStatus.BAD_REQUEST);
		}
	}
}





