package com.cpt4lazy.cpt4lazyserver.controller;

import com.cpt4lazy.cpt4lazyserver.service.JobLinkPostService;
import com.cpt4lazy.cpt4lazyserver.service.JobReferalPostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/postjob")
@RestController
public class PostController {
		
	@Autowired
	private JobReferalPostService jobReferService;
	
	@Autowired
	private JobLinkPostService jobLinkService;
	
	@PostMapping( value="{type}")
	public ResponseEntity<String> createPost(@RequestBody String json, @PathVariable String type) throws JsonMappingException, JsonProcessingException{
		
		boolean success = false;
		if(type.equals("joblink")) {
			success = jobLinkService.createPost(json);
		}
		if(type.equals("referal")) {
			success = jobReferService.createPost(json);
		}
		
		return success ? ResponseEntity.ok("Successfully created post") : ResponseEntity.badRequest().body("Error creating your post");
	}
	
	
	@DeleteMapping("/{type}/{id}")
	public ResponseEntity<String> deletePost(@RequestBody String json, @PathVariable String type, @PathVariable String id){
		
		boolean success = false;
		if(type.equals("joblink")) {
			success = jobLinkService.deletePost(json, id);
		}
		if(type.equals("referal")) {
			success = jobReferService.deletePost(json, id);
		}
		
		return success ? ResponseEntity.ok("Successfully created post") : ResponseEntity.badRequest().body("Error creating your post");
	}
	

	@PutMapping("/{type}/{id}")
	public ResponseEntity<String> updatePost(@RequestBody String json, @PathVariable String type, @PathVariable String id){
		
		boolean success = false;
		if(type.equals("joblink")) {
			success = jobLinkService.updatePost(json, id);
		}
		if(type.equals("referal")) {
			success = jobReferService.updatePost(json, id);
		}
		
		return success ? ResponseEntity.ok("Successfully created post") : ResponseEntity.badRequest().body("Error creating your post");
	}
	
}
