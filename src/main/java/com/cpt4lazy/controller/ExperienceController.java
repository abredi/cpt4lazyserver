package com.cpt4lazy.controller;

import com.cpt4lazy.entity.Experience;
import com.cpt4lazy.service.ExperienceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for "Experience" related request
 * @author cmmap
 *
 */
@RestController
@RequestMapping("/experience")
public class ExperienceController {
	
	@Autowired
	private ExperienceService expService;
	
	
	@GetMapping("/")
	public ResponseEntity<?> viewExpereince(@RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		List<Experience> exp = expService.viewExpereince(token);
		return exp!=null ? ResponseEntity.ok(exp) : ResponseEntity.badRequest().body("Error retrieving your experience/s");
	}
	
	@PostMapping("/edit")
	public ResponseEntity<String> createExpereince(@RequestBody String experience, @RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		
		boolean success = expService.createExperience(experience, token);
		return success ? ResponseEntity.ok("Successfully added experience/s") : ResponseEntity.badRequest().body("Error adding your experience/s");
		
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> updateExpereince(@RequestBody String experience, @PathVariable int id, @RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		
		boolean success = expService.updateExperience(experience, id, token);
		return success ? ResponseEntity.ok("Successfully updated experience/s") : ResponseEntity.badRequest().body("Error updating your experience/s");
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteExperience(@PathVariable int id, @RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		boolean success = expService.deleteExperience(id, token);
		return success ? ResponseEntity.ok("Successfully deleted experience/s") : ResponseEntity.badRequest().body("Error deleting your experience/s");
		
	}
}
