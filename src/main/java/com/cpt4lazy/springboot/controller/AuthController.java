package com.cpt4lazy.springboot.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpt4lazy.springboot.entity.Alumni;
import com.cpt4lazy.springboot.entity.JobSeeker;
import com.cpt4lazy.springboot.entity.User;
import com.cpt4lazy.springboot.service.CustomUserDetailService;


@RestController()
public class AuthController {
	
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	@RequestMapping("/{email}/{password}")
//	public User authenticateUser(@PathVariable String email, @PathVariable String password) {
//		
//		User user = userService.findUserByEmail(email);
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		if (user.getPassword().equals(password))
//		//System.out.println(user.getPassword());
//		//System.out.println(bCryptPasswordEncoder.encode(password));
//		//if (user.getPassword().equals(bCryptPasswordEncoder.encode(password)))
//			return user;
//		
//		return null;	
//	}
//	
//	@RequestMapping(method=RequestMethod.POST, value="/signup")
//	public ResponseEntity<String> createNewUser(@RequestBody String json) {
//		
//		User user = new User();
//		JSONObject jsonObj = new JSONObject(json);
//		user.setId(jsonObj.getString("id"));
//		user.setEmail(jsonObj.getString("email"));
//		user.setPassword(jsonObj.getString("password"));
//		
//		//check if user is already registered
//		User userExists = userService.findUserByEmail(user.getEmail());
//		if(userExists != null) {
//			return ResponseEntity.badRequest()
//			        .body("There is already auser registered with the username provided.");
//		}
//
//		JSONObject roleObject = jsonObj.getJSONObject("role");
//		String id = roleObject.getString("id");
//		String name = roleObject.getString("name");
//		String telephoneNumber = roleObject.getString("telephoneNumber");
//		String address = roleObject.getString("address");
//		String roleName = roleObject.getString("roleName");
//		if(roleName.equals("ALUMNI")){
//			String currentJob = roleObject.getString("currentJob");
//			String currentCompany = roleObject.getString("currentCompany");
//			Alumni alumni = new Alumni(id, name, telephoneNumber, address, roleName, currentJob, currentCompany);
//			user.setRole(alumni);
//			
//		}
//		if(roleName.equals("STUDENT")){
//			String preferredJob = roleObject.getString("preferredJob");
//			String preferredCompany = roleObject.getString("preferredCompany");
//			JobSeeker jobSeeker = new JobSeeker(id, name, telephoneNumber, address, roleName, preferredJob, preferredCompany);
//			user.setRole(jobSeeker);
//		}
//		System.out.println("user" + user);
//		userService.saveUser(user);
//		return ResponseEntity.ok("User has been registered successfully.");
//			
//	}
	
}
