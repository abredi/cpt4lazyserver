package com.cpt4lazy.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpt4lazy.entity.Experience;
import com.cpt4lazy.entity.User;
import com.cpt4lazy.service.CustomUserDetailService;
import com.cpt4lazy.service.ExperienceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Rest Controller for "Experience" related request
 * @author cmmap
 *
 */
@RestController
public class ExperienceController {
	
	@Autowired
	private ExperienceService expService;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/experience")
	public ResponseEntity<?> viewExpereince(@RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		List<Experience> exp = expService.viewExpereince(token);
		return exp!=null ? ResponseEntity.ok(exp) : ResponseEntity.badRequest().body("Error retrieving your experience/s");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="experience/edit")
	public ResponseEntity<String> createExpereince(@RequestBody String experience, @RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		
		boolean success = expService.createExperience(experience, token);
		return success ? ResponseEntity.ok("Successfully added experience/s") : ResponseEntity.badRequest().body("Error adding your experience/s");
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="experience/edit/{id}")
	public ResponseEntity<String> updateExpereince(@RequestBody String experience, @PathVariable int id, @RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		
		boolean success = expService.updateExperience(experience, id, token);
		return success ? ResponseEntity.ok("Successfully updated experience/s") : ResponseEntity.badRequest().body("Error updating your experience/s");
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="experience/delete/{id}")
	public ResponseEntity<String> deleteExperience(@PathVariable int id, @RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		
		boolean success = expService.deleteExperience(id, token);
		return success ? ResponseEntity.ok("Successfully deleted experience/s") : ResponseEntity.badRequest().body("Error deleting your experience/s");
		
	}
}
