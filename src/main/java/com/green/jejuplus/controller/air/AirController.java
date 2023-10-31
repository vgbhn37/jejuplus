package com.green.jejuplus.controller.air;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.jejuplus.service.air.OpenApiAirService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/air")
public class AirController {

//	@Autowired
//	private AirService airService;

	@Autowired
	private OpenApiAirService openApiAirService;

	// main page (강중현)
	@GetMapping("/index")
	public String index(Model model) throws Exception {

		// openApiAirService.OpenApiAir() 메서드를 호출하여 API 응답 데이터를 가져옵니다.
		String apiResponse = openApiAirService.OpenApiAir();

		// ObjectMapper를 사용하여 JSON 데이터를 Map으로 파싱합니다.
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> responseMap = objectMapper.readValue(apiResponse, Map.class);
//	    System.out.println("objectMapper : 1. " + objectMapper);
//	    System.out.println("responseMap : 2. " + responseMap);
//	    System.out.println("apiResponse : 3. " + apiResponse);

		// depAirportNm 정보 가져오기
		Map<String, Object> responseBody = (Map<String, Object>) responseMap.get("response");
		Map<String, Object> body = (Map<String, Object>) responseBody.get("body");
		Map<String, Object> items = (Map<String, Object>) body.get("items");
		// "item" 항목을 배열로 가져오기
		List<Map<String, Object>> itemList = (List<Map<String, Object>>) items.get("item");
//        System.out.println("itemList : " + itemList);

		// 데이터를 순회하면서 모델에 추가
		for (int i = 0; i < itemList.size(); i++) {
			Map<String, Object> item = itemList.get(i);
			String depAirportNm = (String) item.get("depAirportNm");
			String arrAirportNm = (String) item.get("arrAirportNm");
			model.addAttribute("depAirportNm" + i, depAirportNm);
			model.addAttribute("arrAirportNm" + i, arrAirportNm);
//            System.out.println("depAirportNm" + depAirportNm);
//            System.out.println("arrAirportNm" + arrAirportNm);
		}

		return "air/index";
	}

//	@PostMapping("/index")
//	public String indexProc(AirPlanDTO airPlanDTO) {
//		// airPlanDTO에서 depAirportId, arrAirportId, depPlandTime, arrPlandTime 값을 가져옵니다.
//        String depAirportId = airPlanDTO.getDepAirportId();
//        String arrAirportId = airPlanDTO.getArrAirportId();
//
//        // 이제 OpenApiAirService를 호출하고 데이터를 가져오는 작업을 수행합니다.
//        // 필요한 값들을 OpenApiAirService 메서드에 전달합니다.
//        String apiResponse = openApiAirService.OpenApiAir(depAirportId, arrAirportId);
//
//        // 가져온 데이터를 필요에 맞게 처리하고 뷰에 전달합니다.
//		
//		return "redirect:/air/booking";
//	}

	// 예약 및 결제 페이지
	@GetMapping("/booking")
	public String booking(Model model) throws Exception {
		// openApiAirService.OpenApiAir() 메서드를 호출하여 API 응답 데이터를 가져옵니다.
		String apiResponse = openApiAirService.OpenApiAir();

		// ObjectMapper를 사용하여 JSON 데이터를 Map으로 파싱합니다.
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> responseMap = objectMapper.readValue(apiResponse, Map.class);

		// 필요한 데이터 추출
		Map<String, Object> responseBody = (Map<String, Object>) responseMap.get("response");
		Map<String, Object> body = (Map<String, Object>) responseBody.get("body");
		Map<String, Object> items = (Map<String, Object>) body.get("items");
		List<Map<String, Object>> itemList = (List<Map<String, Object>>) items.get("item");
//	    System.out.println("itemList : " + itemList);

		// 모델에 데이터를 추가
		for (int i = 0; i < itemList.size(); i++) {
			Map<String, Object> item = itemList.get(i);
			String depPlandTime = item.get("depPlandTime").toString();
			String arrPlandTime = item.get("arrPlandTime").toString();

			String depTime = depPlandTime.substring(8);
			String arrTime = arrPlandTime.substring(8);

			String depTimeFormatted = depTime.substring(0, 2) + ":" + depTime.substring(2);
			String arrTimeFormatted = arrTime.substring(0, 2) + ":" + arrTime.substring(2);

			String airlineNm = (String) item.get("airlineNm");
			String depAirportNm = (String) item.get("depAirportNm");
			String arrAirportNm = (String) item.get("arrAirportNm");

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
			Date depTimeDate = dateFormat.parse(depPlandTime);
			Date arrTimeDate = dateFormat.parse(arrPlandTime);
			long timeDifference = arrTimeDate.getTime() - depTimeDate.getTime();
			long minutesDifference = timeDifference / (1000 * 60);

			item.put("flightTimeMinutes", minutesDifference);
			item.put("depTimeFormatted", depTimeFormatted);
			item.put("arrTimeFormatted", arrTimeFormatted);

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

	// 결제 완료 페이지
	@GetMapping("/bookingcomplete")
	public String bookingcomplete() {
		return "air/bookingcomplete";
	}

}
