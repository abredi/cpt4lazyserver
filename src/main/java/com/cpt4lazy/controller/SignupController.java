package com.cpt4lazy.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpt4lazy.entity.Alumni;
import com.cpt4lazy.entity.JobSeeker;
import com.cpt4lazy.entity.User;
import com.cpt4lazy.entity.UserRole;
import com.cpt4lazy.service.CustomUserDetailService;
import com.cpt4lazy.service.SequenceGeneratorService;

@RestController
public class SignupController {

	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@RequestMapping(method=RequestMethod.POST, value="/signup")
	public ResponseEntity<String> createNewUser(@RequestBody String json) {
		
		User user = new User();
		JSONObject jsonObj = new JSONObject(json);
		//user.setId(jsonObj.getString("id"));
		user.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
		user.setEmail(jsonObj.getString("email"));
		user.setPassword(jsonObj.getString("password"));
		
		//check if user is already registered
		User userExists = userService.findUserByEmail(user.getEmail());
		if(userExists != null) {
			return ResponseEntity.badRequest()
			        .body("There is already auser registered with the username provided.");
		}

		JSONObject roleObject = jsonObj.getJSONObject("role");
		//String id = roleObject.getString("id");
		
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
		System.out.println("user" + user);
		userService.saveUser(user);
		return ResponseEntity.ok("User has been registered successfully.");
			
	}
}
