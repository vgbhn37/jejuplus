//package com.green.jejuplus.controller.air;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.green.jejuplus.service.air.AirService;
//import com.green.jejuplus.service.air.OpenApiAirService;
//
//@Controller
//@RequestMapping("/air")
//public class ApiController {
//
//	@Autowired
//	private AirService airService;
//
//	@Autowired
//	private OpenApiAirService openApiAirService;
//
//	// API
//	@GetMapping("/api")
//	public String api(Model model) {
//		try {
//			String apiResponse = openApiAirService.OpenApiAir();
//			ObjectMapper objectMapper = new ObjectMapper(); // Jackson 라이브러리를 사용하여 JSON 파싱
//			OpenApiAirDTO openApiAirDTO = objectMapper.readValue(apiResponse, OpenApiAirDTO.class);
//			System.out.println("OpenApiAirDTO 데이터 수신 성공: " + openApiAirDTO);
//
//			// AirService를 사용하여 데이터 저장
//			airService.saveAirData(openApiAirDTO);
//
//			// 모델에 데이터 추가
//			model.addAttribute("depPlandTime", openApiAirDTO.getDepPlandTime());
//			model.addAttribute("arrPlandTime", openApiAirDTO.getArrPlandTime());
//			model.addAttribute("airlineNm", openApiAirDTO.getAirlineNm());
//			model.addAttribute("depAirportNm", openApiAirDTO.getDepAirportNm());
//			model.addAttribute("arrAirportNm", openApiAirDTO.getArrAirportNm());
//
//			return "air/booking";
//		} catch (Exception e) {
//			System.out.println("controller catch : ");
//			e.printStackTrace(); // 예외 스택 트레이스 출력
//			return null;
//		}
//	}
//
//	// API
//	@GetMapping("/api")
//	public String api(Model model) {
//		try {
//			// openApiAirService.OpenApiAir() 메서드를 호출하여 API 응답 데이터를 가져옵니다.
//			String apiResponse = openApiAirService.OpenApiAir();
//
//			// ObjectMapper를 사용하여 JSON 데이터를 Map으로 파싱합니다.
//			ObjectMapper objectMapper = new ObjectMapper();
//			Map<String, Object> responseMap = objectMapper.readValue(apiResponse, Map.class);
//
//			// "body" 객체의 존재 여부 확인
//			if (responseMap.containsKey("body")) {
//				Map<String, Object> body = (Map<String, Object>) responseMap.get("body");
//
//				// "items" 배열의 존재 여부 확인
//				if (body.containsKey("items")) {
//					List<Map<String, Object>> items = (List<Map<String, Object>>) body.get("items");
//
//					if (items != null && !items.isEmpty()) {
//						// 첫 번째 아이템을 가져와 필요한 데이터를 추출
//						Map<String, Object> firstItem = items.get(0);
//
//						// 필요한 데이터를 모델에 추가
//						model.addAttribute("depPlandTime", firstItem.get("depPlandTime"));
//						model.addAttribute("arrPlandTime", firstItem.get("arrPlandTime"));
//						model.addAttribute("airlineNm", firstItem.get("airlineNm"));
//						model.addAttribute("depAirportNm", firstItem.get("depAirportNm"));
//						model.addAttribute("arrAirportNm", firstItem.get("arrAirportNm"));
//
//						// 데이터 확인을 위해 각 데이터를 System.out.println으로 출력
//						System.out.println("depPlandTime: " + firstItem.get("depPlandTime"));
//						System.out.println("arrPlandTime: " + firstItem.get("arrPlandTime"));
//						System.out.println("airlineNm: " + firstItem.get("airlineNm"));
//						System.out.println("depAirportNm: " + firstItem.get("depAirportNm"));
//						System.out.println("arrAirportNm: " + firstItem.get("arrAirportNm"));
//					}
//				}
//			}
//
//			return "air/booking";
//		} catch (Exception e) {
//			System.out.println("controller catch : ");
//			e.printStackTrace(); // 예외 스택 트레이스 출력
//			return null;
//		}
//	}
//}
