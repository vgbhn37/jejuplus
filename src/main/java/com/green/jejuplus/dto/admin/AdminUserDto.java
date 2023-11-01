package com.green.jejuplus.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDto {
	int userId;
	int levelId;
	String username; 
	String password; 
	String fullname;
	String email;
	String phoneNumber; 
	String isKakao;
}
