package com.green.jejuplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.jejuplus.repository.interfaces.DummyRepository;

@Controller
public class DummyController {

	@Autowired
	DummyRepository dummyRepository;

	@GetMapping("/insertData")
	@ResponseBody
	@Transactional
	public String insertData() {

//		String result = "";
//
//		for (int i = 1; i <= 53; i++) {
//			try {
//				Contents contents = new Contents();
//				URL url = new URL(
//						"https://api.visitjeju.net/vsjApi/contents/searchList?apiKey={API_KEY}&locale=kr&page="
//								+ i);
//				BufferedReader bf;
//				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//				result = bf.readLine();
//
//				JSONParser jsonParser = new JSONParser();
//				JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
//				JSONArray items = (JSONArray) jsonObject.get("items");
//				for (int j = 0; j < items.size(); j++) {
//					
//					JSONObject item = (JSONObject) items.get(j);
//					JSONObject contentscd = (JSONObject) item.get("contentscd");
//					JSONObject region1cd = (JSONObject) item.get("region1cd");
//					JSONObject region2cd = (JSONObject) item.get("region2cd");
//					JSONObject repPhoto = (JSONObject) item.get("repPhoto");
//					
//					if (repPhoto != null) {
//						JSONObject photoid = (JSONObject) repPhoto.get("photoid");
//						if (photoid != null) {
//							String imgPath = (String) photoid.get("imgpath");
//							String thumbnailPath = (String) photoid.get("thumbnailpath");
//							contents.setImgPath(imgPath);
//							contents.setThumbnailPath(thumbnailPath);
//						}
//					}
//
//					String contentsTempId = (String) item.get("contentsid");
//					String contentsLabel = (String) contentscd.get("label");
//					String title = (String) item.get("title");
//					if (region1cd != null) {
//						String region1 = (String) region1cd.get("label");
//						contents.setRegion1(region1);
//					}
//					if (region2cd != null) {
//						String region2 = (String) region2cd.get("label");
//						contents.setRegion2(region2);
//					}
//					String jibeonAddress = (String) item.get("address");
//					String roadAddress = (String) item.get("roadaddress");
//					String tag = (String) item.get("tag");
//					String introduction = (String) item.get("introduction");
//					if((Double)item.get("latitude")!=null) {
//						String latitude =  Double.toString((Double) item.get("latitude")); 
//						contents.setLatitude(latitude);
//					}
//					if((Double)item.get("longitude")!=null) {
//						String longitude = Double.toString((Double) item.get("longitude")); 
//						contents.setLongitude(longitude);
//					}
//					
//					String phoneNo = (String) item.get("phoneno");
//					
//					
//					contents.setContentsTempId(contentsTempId);
//					contents.setContentsLabel(contentsLabel);
//					contents.setTitle(title);
//					contents.setJibeonAddress(jibeonAddress);
//					contents.setRoadAddress(roadAddress);
//					contents.setTag(tag);
//					contents.setIntroduction(introduction);	
//					contents.setPhoneNo(phoneNo);
//
//					dummyRepository.insertData(contents);
//					
//					contents.setImgPath(null);
//					contents.setThumbnailPath(null);
//					contents.setRegion1(null);
//					contents.setRegion2(null);
//					contents.setLatitude(null);
//					contents.setLongitude(null);

//
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				return count + " success";
//			}
//		}

		return "All Complete";	

	}

}
