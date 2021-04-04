package com.cpt4lazy.cpt4lazyserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpt4lazy.cpt4lazyserver.service.JobLinkPostService;
import com.cpt4lazy.cpt4lazyserver.service.JobReferalPostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class PostController {
		
	@Autowired
	private JobReferalPostService jobReferService;
	
	@Autowired
	private JobLinkPostService jobLinkService;
	
	@RequestMapping(method=RequestMethod.POST, value="postjob/{type}")
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
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="postjob/{type}/{id}")
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
	

	@RequestMapping(method=RequestMethod.PUT, value="postjob/{type}/{id}")
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
