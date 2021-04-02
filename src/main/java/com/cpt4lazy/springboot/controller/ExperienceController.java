package com.cpt4lazy.springboot.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cpt4lazy.springboot.entity.User;
import com.cpt4lazy.springboot.service.CustomUserDetailService;
import com.cpt4lazy.springboot.service.ExperienceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class ExperienceController {
	
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private ExperienceService expService;
	
	
	@RequestMapping(method=RequestMethod.POST, value="experience/edit")
	public ResponseEntity<String> createExpereince(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		
		boolean success = false;
		JSONObject jsonObj = new JSONObject(json);
		User user = userService.findUserByEmail(jsonObj.getString("email"));
		
		if(user != null) {
			success = expService.createExperience(user, jsonObj.getJSONArray("experience").toString());
		}
		
		return success ? ResponseEntity.ok("Successfully added experience/s") : ResponseEntity.badRequest().body("Error adding your experience/s");
		
	}
	

	@RequestMapping(method=RequestMethod.PUT, value="experience/edit")
	public ResponseEntity<String> updateExpereince(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		
		boolean success = expService.updateExperience(json);
		return success ? ResponseEntity.ok("Successfully added experience/s") : ResponseEntity.badRequest().body("Error adding your experience/s");
		
	}
	

	@RequestMapping(method=RequestMethod.DELETE, value="experience/edit")
	public ResponseEntity<String> deleteExpereince(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		
		boolean success = expService.deleteExperience(json);
		return success ? ResponseEntity.ok("Successfully added experience/s") : ResponseEntity.badRequest().body("Error adding your experience/s");
		
	}
}
