package com.cpt4lazy.cpt4lazyserver.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.cpt4lazy.cpt4lazyserver.configs.JwtUtils;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.service.CustomUserDetailService;

/**
 * Rest Controller for Login Request
 * @author cmmap
 *
 */
@RestController
public class LoginController {
	
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
		
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody String json) {
		JSONObject jsonObj = new JSONObject(json);
		User user = userService.findUserByEmail(jsonObj.getString("email"));
		
		//performs authentication of the user
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jsonObj.get("email"), jsonObj.get("password")));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//once user is successfully authenticated, a JWT token will be generated and will be passed
		//as part of the response
		String jwt = jwtUtils.generateJwtToken(authentication);

		return ResponseEntity.ok(new JWTResponse(user, jwt));
	}	
}
