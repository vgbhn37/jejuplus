package com.green.jejuplus.service.admin;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.jejuplus.dto.admin.AdminUserDto;
import com.green.jejuplus.handler.exception.CustomException;
import com.green.jejuplus.repository.interfaces.UserRepository;
import com.green.jejuplus.repository.model.User;





@Service
@Transactional
public class AdminService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdminUserDto adminUserDto;
	
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

}
