package com.green.jejuplus.service.admin;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.green.jejuplus.dto.admin.AdminPromotionDto;
import com.green.jejuplus.dto.admin.AdminUserDto;
import com.green.jejuplus.handler.exception.CustomException;
import com.green.jejuplus.repository.interfaces.PromotionRepository;
import com.green.jejuplus.repository.interfaces.UserRepository;
import com.green.jejuplus.repository.model.Promotion;
import com.green.jejuplus.repository.model.PromotionImg;
import com.green.jejuplus.repository.model.User;





@Service
@Transactional
public class AdminService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PromotionRepository promotionRepository;

	@Autowired
	AdminUserDto adminUserDto;

	@Value("${UPLOAD_DIRECTORY}")
	private String uploadDirectory;

	public List<AdminUserDto> getUsers(int page, int pageSize) {
		// 페이지 번호와 페이지 크기를 이용하여 오프셋(offset) 계산
		int offset = (page - 1) * pageSize;

		// 데이터베이스에서 사용자 목록을 가져옴
		List<AdminUserDto> users = userRepository.findUsers(pageSize, offset);
		System.out.println("서비스 :" + pageSize);
		System.out.println("서비스 :" + offset);
		// 가져온 사용자 목록 반환
		return users;
	}

	public int getTotalUsers() {
		return userRepository.count();
	}

	public void userDelete(String username) {
		userRepository.adminUserDelete(username);

	}

	public void updateUserLevel(String username, int newLevelId) {
		// Retrieve the user by username from the repository
		System.out.println("서비스에서 확인:" + username);
		System.out.println("서비스에서 확인:" + newLevelId);

		User user = userRepository.findByUsername(username);
		System.out.println("서비스에서 확인:" + user);
		if (user != null) {
			// Update the user's level
			System.out.println("서비스 레벨아이디 :" + newLevelId);
			user.setLevelId(newLevelId);
			userRepository.userLevelUpdate(user); // Save the updated user to the database
		} else {
			System.out.println("서비스에서 확인:" + username);
			System.out.println("서비스에서 확인:" + newLevelId);
			System.out.println("서비스에서 확인:" + user);
			throw new CustomException("User not found",HttpStatus.BAD_REQUEST); // Define your custom exception
		}

	}

	public List<AdminUserDto> searchUsers(String category, String search, int page, int pageSize) {
		// 페이지 번호와 페이지 크기를 이용하여 오프셋(offset) 계산
		int offset = (page - 1) * pageSize;

		// 데이터베이스에서 사용자 목록을 검색하고 검색 결과를 가져옴
		List<AdminUserDto> users = userRepository.searchUsers(category, search, pageSize, offset);

		// 검색된 사용자 목록 반환
		return users;
	}

	public int getTotalUsersWithSearch(String category, String search) {
		// 데이터베이스에서 검색 조건에 맞는 총 사용자 수를 가져옴
		int totalUsers = userRepository.countWithSearch(category, search);

		// 총 사용자 수 반환
		return totalUsers;
	}


	// 이미지 파일 업로드 및 경로 저장
	public void uploadImage(MultipartFile imageFile) {
		if (!imageFile.isEmpty()) {
			try {
				String originalFilename = imageFile.getOriginalFilename();
				// 이미지 파일을 저장할 경로 설정
				Path filePath = Paths.get(uploadDirectory, originalFilename);

				// 경로의 디렉토리가 없는 경우 생성
				if (!Files.exists(filePath.getParent())) {
					Files.createDirectories(filePath.getParent());
				}

				Files.write(filePath, imageFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				// 예외 처리
			}
		}
	}


	public void insertPromotion(String title, String introduce, String content, MultipartFile[] images) {
		Promotion promotion = Promotion.builder()
				.title(title)
				.introduce(introduce)
				.content(content)
				.build();

		// 프로모션 정보를 저장
		promotionRepository.savePromotion(promotion);

		// 이미지 정보를 별도로 저장
		for (MultipartFile imageFile : images) {
			uploadImage(imageFile); // 이미지 파일을 업로드하고 저장
			String imageFilename = "/images/promotion/" + imageFile.getOriginalFilename();
			String imagePath = uploadDirectory +'/'+ imageFilename;

			PromotionImg image = PromotionImg.builder()
					.filename(imageFilename)
					.imgPath(imagePath) // 이미지 파일의 저장 경로를 저장
					.promotionId(promotion.getPromotionId()) // 이미지와 프로모션 간의 관계 설정
					.build();

			promotionRepository.savePromotionImg(image);
		}
	}  



	public List<AdminPromotionDto> getPromotions() {
		return promotionRepository.getPromotions();
	}

	public String getImageUrlsByPromotionId(int promotionId) {
		return promotionRepository.getImageUrlsByPromotionId(promotionId);
	}

	public List<AdminPromotionDto> searchPromotions(String search, int page, int pageSize) {
		// 페이지 번호와 페이지 크기를 이용하여 오프셋(offset) 계산
		int offset = (page - 1) * pageSize;

		// 데이터베이스에서 검색된 프로모션 목록을 가져옴
		List<AdminPromotionDto> searchResults = promotionRepository.searchPromotions(search, pageSize, offset);

		// 가져온 검색 결과 반환
		return searchResults;
	}

	public int getTotalPromotionsWithSearch(String search) {
		return promotionRepository.countPromotionsWithSearch(search);
	}

	public List<AdminPromotionDto> getPromotionsByPage(int page, int pageSize) {
		int promotionsPerPage = pageSize; // Use the provided pageSize parameter as promotionsPerPage
		int offset = (page - 1) * promotionsPerPage;

		List<AdminPromotionDto> promotions = promotionRepository.findPromotions(promotionsPerPage, offset);

		return promotions;
	}

	public int getTotalPromotions() {
		// TODO Auto-generated method stub
		return promotionRepository.count();
	}


}
