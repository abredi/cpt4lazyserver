package com.cpt4lazy.cpt4lazyserver.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpt4lazy.cpt4lazyserver.service.CustomUserDetailService;


@RestController()
public class AuthController {
	
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

}
