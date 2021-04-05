package com.cpt4lazy.cpt4lazyserver.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cpt4lazy.cpt4lazyserver.entity.Alumni;
import com.cpt4lazy.cpt4lazyserver.entity.JobSeeker;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.entity.UserRole;
import com.cpt4lazy.cpt4lazyserver.helper.CPT4LazyUtility;
import com.cpt4lazy.cpt4lazyserver.service.CustomUserDetailService;
import com.cpt4lazy.cpt4lazyserver.service.SequenceGeneratorService;

@RestController
public class SignupController {

	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	private CPT4LazyUtility utility;
	
	@PostMapping("/signup")
	public ResponseEntity<String> createNewUser(@RequestBody String json) {
		
		User user = new User();
		JSONObject jsonObj = new JSONObject(json);
		user.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
		user.setEmail(jsonObj.getString("email"));
		user.setPassword(jsonObj.getString("password"));
		
		User userExists = userService.findUserByEmail(user.getEmail());
		if(userExists != null) {
			return ResponseEntity.badRequest()
			        .body("There is already auser registered with the username provided.");
		}

		JSONObject roleObject = jsonObj.getJSONObject("role");

		String name = roleObject.getString("name");
		String telephoneNumber = roleObject.getString("telephoneNumber");
		String address = roleObject.getString("address");
		String roleName = roleObject.getString("roleName");
		if(roleName.equals("ALUMNI")){
			String currentJob = roleObject.getString("currentJob");
			String currentCompany = roleObject.getString("currentCompany");
			Alumni alumni = new Alumni(name, telephoneNumber, address, roleName, currentJob, currentCompany);
			alumni.setId(sequenceGenerator.generateSequence(UserRole.SEQUENCE_NAME));
			user.setRole(alumni);
			
		}
		if(roleName.equals("STUDENT")){
			String preferredJob = roleObject.getString("preferredJob");
			String preferredCompany = roleObject.getString("preferredCompany");
			JobSeeker jobSeeker = new JobSeeker(name, telephoneNumber, address, roleName, preferredJob, preferredCompany);
			jobSeeker.setId(sequenceGenerator.generateSequence(UserRole.SEQUENCE_NAME));
			user.setRole(jobSeeker);
		}

		userService.saveUser(user);
		return ResponseEntity.ok("User has been registered successfully.");
			
	}
	
	@GetMapping("/profile")
	public ResponseEntity<?> viewProfile(@RequestHeader("Authorization") String token) {
		
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.badRequest().body("message: Error viewing profile");
	}
}
