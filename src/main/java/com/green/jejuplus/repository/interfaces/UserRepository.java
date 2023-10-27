package com.green.jejuplus.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.jejuplus.dto.SignUpFormDto;
import com.green.jejuplus.dto.UserUpdateDto;
import com.green.jejuplus.repository.model.User;

@Mapper
public interface UserRepository {

	User findByVerificationToken(String token);
	User findByEmail(@Param("email") String email);
	int insertUser(SignUpFormDto signUpFormDto);
	User findByUsername(@Param("username") String username);
	int insertKakaoUser(SignUpFormDto signUpFormDto);
	User findById(int userId);
	UserUpdateDto findByUser(int userId);
	int updateUser(UserUpdateDto userUpdateDto);

}
