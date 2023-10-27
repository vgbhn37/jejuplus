package com.green.jejuplus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.jejuplus.dto.SignInFormDto;
import com.green.jejuplus.dto.SignUpFormDto;
import com.green.jejuplus.dto.UserUpdateDto;
import com.green.jejuplus.handler.exception.CustomException;
import com.green.jejuplus.repository.interfaces.UserRepository;
import com.green.jejuplus.repository.model.User;




@Service
@Transactional
public class UserService {


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;



	public String getRandomCodeForUser(String userEmail) {
      User user = userRepository.findByEmail(userEmail);
      if (user != null) {
	         return user.getRandomCode();
      }
	     return null; // 사용자를 찾을 수 없으면 null 또는 다른 처리를 수행
	 }
	
	@Transactional
	public void registerUser(SignUpFormDto signUpFormDto) {		
			String rawPwd = signUpFormDto.getPassword();
			String hashPwd = passwordEncoder.encode(rawPwd);
			System.out.println("rawPwd : " + rawPwd);
			System.out.println("hashPwd : " + hashPwd);		
			signUpFormDto.setPassword(hashPwd);	
		 System.out.println("세번째 : " + signUpFormDto.getUsername());
		int result = userRepository.insertUser(signUpFormDto);
		if(result != 1) {
					throw new CustomException("회원가입실패",
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
		
	}					

	
	public boolean isUsernameUnique(String username) {
        // 아이디가 중복되지 않았는지 확인
        User existingUser = userRepository.findByUsername(username);
        return existingUser == null; // 중복되지 않으면 true, 중복되면 false 반환
    }

    public boolean isEmailUnique(String email) {
        // 이메일이 중복되지 않았는지 확인
        User existingUser = userRepository.findByEmail(email);
        return existingUser == null; // 중복되지 않으면 true, 중복되면 false 반환
    }

	public User signIn(SignInFormDto signInFormDto) {
		// 계정 이름만 확인으로 변경 처리
				User userEntity = userRepository.findByUsername(signInFormDto.getUsername());
				
				
				// 계정 확인
				// 계정이 존재 하지 않습니다.
				if(userEntity == null || 
						userEntity
						.getUsername().equals(signInFormDto.getUsername()) == false) {
					throw new CustomException("존재하지 않는 계정입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
				// 비번 확인
				// 비밀번호가 틀렸습니다.
				boolean isPwdMatched = passwordEncoder
						.matches(signInFormDto.getPassword(), userEntity.getPassword());
				
				if(isPwdMatched == false) {
					throw new CustomException("비밀번호가 틀렸습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
				}
								
			
				return userEntity;	
	}

	public User searchUsername(String username) {		
		 return userRepository.findByUsername(username);
	}

	public void registerUserKakao(SignUpFormDto signUpFormDto) {
		String rawPwd = signUpFormDto.getPassword();
		String hashPwd = passwordEncoder.encode(rawPwd);
		System.out.println("rawPwd : " + rawPwd);
		System.out.println("hashPwd : " + hashPwd);		
		signUpFormDto.setPassword(hashPwd);	
	 System.out.println("세번째 : " + signUpFormDto.getUsername());
	int result = userRepository.insertKakaoUser(signUpFormDto);
	if(result != 1) {
				throw new CustomException("회원가입실패",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}

	public UserUpdateDto findUser(int userId) {
		UserUpdateDto userDetailDto = userRepository.findByUser(userId);
		return userDetailDto;
	}

	public int userDetailUpdate(int userId,UserUpdateDto userUpdateDto) {
		int result = userRepository.updateUser(userUpdateDto);
		return result;
	}
	

}
