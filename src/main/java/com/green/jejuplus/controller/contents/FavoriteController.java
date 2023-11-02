package com.green.jejuplus.controller.contents;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.jejuplus.dto.contents.FavoriteRequestDto;
import com.green.jejuplus.handler.exception.CustomRestfulException;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.service.contents.FavoriteService;
import com.green.jejuplus.util.Define;


@RestController
@RequestMapping("/api")
public class FavoriteController {
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private HttpSession session;
}
