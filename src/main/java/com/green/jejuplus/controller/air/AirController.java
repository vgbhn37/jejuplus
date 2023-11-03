package com.green.jejuplus.controller.air;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.jejuplus.dto.air.AirPlanDTO;
import com.green.jejuplus.dto.air.CustomerDTO;
import com.green.jejuplus.service.air.OpenApiAirService;

@Controller
@RequestMapping("/air")
public class AirController {

	@Autowired
	private OpenApiAirService openApiAirService;

	// main page (강중현)
	@GetMapping("/index")
	public String index() throws Exception {

		return "air/index";
	}

	@PostMapping("/index")
	public String indexProc(AirPlanDTO airPlanDTO, HttpSession session) throws Exception {

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
	public String booking(Model model, HttpSession session) throws Exception {
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
	public String bookingProc(HttpSession session, CustomerDTO customerDTO) throws Exception {
		
		// 세션에 customerDTO 데이터 담음
		session.setAttribute("customerDTO", customerDTO);
		System.out.println("booking에서 customerDTO 데이터 넘김 확인 " + customerDTO);

		
		return "redirect:/air/bookingcomplete";
	}

	// 결제 완료 페이지
	@GetMapping("/bookingcomplete")
	public String bookingcomplete(Model model, HttpSession session) {
		
		
        
		return "air/bookingcomplete";
	}

}
