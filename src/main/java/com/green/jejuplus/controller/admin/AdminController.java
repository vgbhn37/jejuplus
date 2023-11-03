package com.green.jejuplus.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.jejuplus.dto.admin.AdminPlaceDto;
import com.green.jejuplus.dto.admin.AdminUserDto;
import com.green.jejuplus.handler.exception.CustomException;
import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Schedule;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.service.admin.AdminService;
import com.green.jejuplus.service.schedule.ScheduleService;
import com.green.jejuplus.util.Define;
import com.green.jejuplus.util.Pagination;
import com.green.jejuplus.util.PagingDto;




@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ScheduleService scheduleService;
	
	@GetMapping("/adminUserManagement")
	public String adminUserManagement(
	    @RequestParam(name = "page", defaultValue = "1") int page, 
	    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, 
	    @RequestParam(value = "category", required = false) String category, 
	    @RequestParam(value = "search", required = false) String search, 
	    Model model) {
	    // 페이지 및 페이지 크기 관련 코드 (기존 코드와 동일)
	    // ...

	    List<AdminUserDto> users;
	    int totalUsers;

	    if (category != null && search != null) {
	        // 사용자가 검색 조건과 검색어를 지정한 경우
	        users = adminService.searchUsers(category, search, page, pageSize);
	        totalUsers = adminService.getTotalUsersWithSearch(category, search); // 검색된 사용자 수 가져오기
	    } else {
	        // 사용자가 검색하지 않은 경우
	        users = adminService.getUsers(page, pageSize);
	        totalUsers = adminService.getTotalUsers(); // 모든 사용자 수 가져오기
	    }

	    int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

	    model.addAttribute("users", users);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("currentPage", page);

	    return "/admin/adminUserManagement";
	}

	@PostMapping("/adminUserDelete/{username}")
    @ResponseBody
    public Map<String, String> adminUserDelete(@PathVariable("username") String username) {
        Map<String, String> response = new HashMap<>();
        try {
            adminService.userDelete(username);
            response.put("result", "success");
        } catch (Exception e) {
            response.put("result", "error");
        }
        return response;
    }
	
	@PostMapping("/updateUserLevel")
    public  ResponseEntity<Map<String, String>> updateUserLevel(@RequestParam("username") String username, @RequestParam("newLevelId") int newLevelId) {
		
		
		System.out.println("컨트롤러 레벨 아이디 확인 :" + newLevelId);
	    try {
	    	System.out.println(username);
	        // Perform the necessary logic to update the user's level
	        adminService.updateUserLevel(username, newLevelId);
	        
	        // Return a success response
	        Map<String, String> response = new HashMap<>();
	        response.put("result", "success");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        // Handle any exceptions or errors
	        Map<String, String> response = new HashMap<>();
	        response.put("result", "error");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	
	
	}
	
	@GetMapping("/adminPlaceManagement")
	public String adminPlaceManagement() {
		return "/admin/adminPlaceManagement";
	}
	


}
