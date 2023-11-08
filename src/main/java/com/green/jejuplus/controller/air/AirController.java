package com.green.jejuplus.controller.air;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.jejuplus.dto.air.AirPlanDTO;
import com.green.jejuplus.dto.air.CustomerDTO;
import com.green.jejuplus.dto.payment.PaymentDTO;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.service.air.OpenApiAirService;
import com.green.jejuplus.service.payment.PaymentService;
import com.green.jejuplus.util.Define;

@Controller
@RequestMapping("/air")
public class AirController {

	@Autowired
	private OpenApiAirService openApiAirService;

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private PaymentService paymentService;

	// main page (강중현)
	@GetMapping("/index")
	public String index() throws Exception {
		// 유저 정보 받아옴
		User user = (User)session.getAttribute(Define.PRINCIPAL);
		System.out.println("받은 user" + user);

		return "air/index";
	}

	@PostMapping("/index")
	public String indexProc(AirPlanDTO airPlanDTO) throws Exception {

		// 출발지와 도착지가 '선택'으로 설정된 경우에 페이지 이동 금지
		if (airPlanDTO.getDepAirportNm() == null || airPlanDTO.getArrAirportNm() == null
				|| airPlanDTO.getDepAirportNm().equals("선택") || airPlanDTO.getArrAirportNm().equals("선택")) {
			return "redirect:/air/index";
		}
		// 출발지와 도착지가 동일한 경우 페이지 이동 안됨
		if (airPlanDTO.getDepAirportNm().equals(airPlanDTO.getArrAirportNm())) {
			return "redirect:/air/index";
		}

		// 날짜 형식을 "yyyymmdd"로 변경
		String depPlandTime = airPlanDTO.getDepPlandTime().replaceAll("-", "");
		String arrPlandTime = airPlanDTO.getArrPlandTime().replaceAll("-", "");
		airPlanDTO.setDepPlandTime(depPlandTime);
		airPlanDTO.setArrPlandTime(arrPlandTime);

		// 세션에 airPlanDTO 데이터 담음
		session.setAttribute("airPlanDTO", airPlanDTO);
		System.out.println("index 데이터 넘김 확인 " + airPlanDTO);
		return "redirect:/air/booking";
	}

	// 예약 및 결제 페이지
	@GetMapping("/booking")
	public String booking(Model model) throws Exception {
		
		// 유저 정보 받아옴
		User user = (User)session.getAttribute(Define.PRINCIPAL);

		// session에 담긴 데이터를 받음
		AirPlanDTO airPlanDTO = (AirPlanDTO) session.getAttribute("airPlanDTO");

		String depAirportId = airPlanDTO.getDepAirportNm();
		String arrAirportId = airPlanDTO.getArrAirportNm();
		String depPlandTime = airPlanDTO.getDepPlandTime();
		String arrPlandTime = airPlanDTO.getArrPlandTime();

		// openApiAirService.OpenApiAir() 메서드를 호출하여 API 응답 데이터 호출
		String apiResponse = openApiAirService.OpenApiAir(depAirportId, arrAirportId, depPlandTime, arrPlandTime);

		// ObjectMapper를 사용하여 JSON 데이터를 Map으로 파싱
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> responseMap = objectMapper.readValue(apiResponse, Map.class);

		// 필요한 데이터 추출
		Map<String, Object> responseBody = (Map<String, Object>) responseMap.get("response");
		Map<String, Object> body = (Map<String, Object>) responseBody.get("body");
		Map<String, Object> items = (Map<String, Object>) body.get("items");
		List<Map<String, Object>> itemList = (List<Map<String, Object>>) items.get("item");
//	    System.out.println("[booking] itemList 데이터 추출 성공 : " + itemList);

		// 모델에 데이터를 추가
		for (int i = 0; i < itemList.size(); i++) {
			Map<String, Object> item = itemList.get(i);
			depPlandTime = item.get("depPlandTime").toString();
			arrPlandTime = item.get("arrPlandTime").toString();

			// 시간을 추출할 위치 정의 (예: "202310291030" 형식에서 "1030"을 추출)
			String depTime = depPlandTime.substring(8); // 앞 8자리를 잘라내고 그 뒤부터 출력
			String arrTime = arrPlandTime.substring(8);

			// 추출된 시간을 "hh/mm" 형식으로 변환
			String depTimeFormatted = depTime.substring(0, 2) + ":" + depTime.substring(2); // 10:30 으로 표시
			String arrTimeFormatted = arrTime.substring(0, 2) + ":" + arrTime.substring(2);

			// 나머지 데이터 가져오기
			String airlineNm = (String) item.get("airlineNm");
			String depAirportNm = (String) item.get("depAirportNm");
			String arrAirportNm = (String) item.get("arrAirportNm");

			// 출발 도착 시간 차이 계산
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
			Date depTimeDate = dateFormat.parse(depPlandTime);
			Date arrTimeDate = dateFormat.parse(arrPlandTime);
			long timeDifference = arrTimeDate.getTime() - depTimeDate.getTime();
			long minutesDifference = timeDifference / (1000 * 60);

			// 시간 put 으로 삽입
			item.put("flightTimeMinutes", minutesDifference);
			item.put("depTimeFormatted", depTimeFormatted);
			item.put("arrTimeFormatted", arrTimeFormatted);

			// 모델에 데이터를 추가
			model.addAttribute("depPlandTime" + i, depTimeFormatted);
			model.addAttribute("arrPlandTime" + i, arrTimeFormatted);
			model.addAttribute("airlineNm" + i, airlineNm);
			model.addAttribute("depAirportNm" + i, depAirportNm);
			model.addAttribute("arrAirportNm" + i, arrAirportNm);
			model.addAttribute("flightTimeMinutes" + i, minutesDifference);
//			System.out.println("depPlandTime" + depPlandTime);
//			System.out.println("arrPlandTime" + arrPlandTime);
//		    System.out.println("airlineNm" + airlineNm);
		}

		model.addAttribute("itemList", itemList);

		return "air/booking";
	}

	// 예약 및 결제 페이지
	@PostMapping("/booking")
	@ResponseBody
	public String bookingProc(CustomerDTO customerDTO, PaymentDTO paymentDTO) throws Exception {

		// 유저 정보 받아옴
		User user = (User)session.getAttribute(Define.PRINCIPAL);
		System.out.println("받은 user : " + user);
		
		String pgTid = request.getParameter("pg_tid"); // "pg_tid" 값을 직접 가져옴
	    System.out.println("받은 pg_tid : " + pgTid);
		
	    
	    paymentService.insertPayment(paymentDTO, user.getUserId());
	    
		
		// 세션에 customerDTO 데이터 담음
		session.setAttribute("customerDTO", customerDTO);
		System.out.println("booking에서 customerDTO 데이터 넘김 확인 " + customerDTO);
		
	    
	    // Object 로 리턴해야 중간에 메세지 컨번터가 JSON 문자열로 변환해서 던져 준다.
		String jsonResult = "1";
	    
	    return jsonResult;
//		return "{name:result}";
//		return "redirect:/air/bookingcomplete";
	}

	// 결제 완료 페이지
	@GetMapping("/bookingcomplete")
	public String bookingcomplete(Model model) {

		// 유저 정보 받아옴
		User user = (User)session.getAttribute(Define.PRINCIPAL);

		return "air/bookingcomplete";
	}

}
