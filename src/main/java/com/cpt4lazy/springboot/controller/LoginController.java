package com.cpt4lazy.springboot.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpt4lazy.springboot.entity.User;
import com.cpt4lazy.springboot.service.CustomUserDetailService;

@RestController
public class LoginController {
	
	@Autowired
	private CustomUserDetailService userService;
	
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public ResponseEntity<User> authenticateUser(@RequestBody String json) {
		System.out.println("hello");
		JSONObject jsonObj = new JSONObject(json);
		User user = userService.findUserByEmail(jsonObj.getString("email"));
		if((user == null) || (!user.getPassword().equals(jsonObj.getString("password")))){
			return ResponseEntity.badRequest()
					.body(null);	        
		}

		return ResponseEntity.ok(user);	 
	}
}
