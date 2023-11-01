package com.green.jejuplus.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.jejuplus.dto.admin.AdminUserDto;
import com.green.jejuplus.service.admin.AdminService;




@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/adminUserManagement")
	public String adminUserManagement(@RequestParam(name = "page", defaultValue = "1") int page, 
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, 
            Model model) {
		
		System.out.println("컨트롤러 : " + page );
		System.out.println("컨트롤러 : " + pageSize );
		List<AdminUserDto> users = adminService.getUsers(page, pageSize);
	        model.addAttribute("users", users);
	        
	        int totalUsers = adminService.getTotalUsers(); // 예를 들어, 데이터베이스에서 총 사용자 수 가져오기
	        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

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
}
