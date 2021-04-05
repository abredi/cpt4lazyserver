package com.cpt4lazy.cpt4lazyserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpt4lazy.cpt4lazyserver.entity.JobLinkPost;
import com.cpt4lazy.cpt4lazyserver.entity.JobReferalPost;
import com.cpt4lazy.cpt4lazyserver.service.JobLinkPostService;
import com.cpt4lazy.cpt4lazyserver.service.JobReferalPostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * RestController for Alumni related "Post (Referral and Link post)" 
 * @author cmmap
 *
 */
@RestController
public class PostController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostController.class.getName());
	
	@Autowired
	private JobReferalPostService jobReferService;
	
	@Autowired
	private JobLinkPostService jobLinkService;
	
	
	//@PreAuthorize("hasRole('ALUMNI') or hasRole('STUDENT')")
	@RequestMapping(method=RequestMethod.GET, value="postjob/{type}")
	public ResponseEntity<?> viewPost(@PathVariable String type){
		
		if(type.equals("joblink")) {
			List<JobLinkPost> jobLinkPost = jobLinkService.viewPost();
			return ResponseEntity.ok(jobLinkPost);
		}
		if(type.equals("referral")) {
			List<JobReferalPost> jobReferralPost = jobReferService.viewPost();
			return ResponseEntity.ok(jobReferralPost);
		}
		
		return ResponseEntity.badRequest().body("message: Error getting Job Referral Post/Job Link Post");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="postjob/{type}/user")
	public ResponseEntity<?> viewPost(@PathVariable String type, @RequestHeader("Authorization") String token){
		if(type.equals("joblink")) {
			List<JobLinkPost> jobLinkPost = jobLinkService.viewPost(token);
			return ResponseEntity.ok(jobLinkPost);
		}
		if(type.equals("referral")) {
			List<JobReferalPost> jobReferralPost = jobReferService.viewPost(token);
			return ResponseEntity.ok(jobReferralPost);
		}
		
		return ResponseEntity.badRequest().body("message: Error getting Job Referral Post/Job Link Post");
	}
	
	//@PreAuthorize("hasRole('ALUMNI')")
	@RequestMapping(method=RequestMethod.POST, value="postjob/{type}")
	public ResponseEntity<String> createPost(@RequestBody String post, @PathVariable String type, @RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException{
		boolean success = false;
		if(type.equals("joblink")) {
			
			logger.info("Calling the Job Link Service to create a job link post");
			success = jobLinkService.createPost(post, token);
			logger.info("Job link post post creation {}", success);
		}
		if(type.equals("referral")) {
			System.out.println("PostController: postjob/referral");
			logger.info("Calling the Job Referal Service to create a job referral post");
			success = jobReferService.createPost(post, token);
			logger.info("Referral post post creation {}", success);
		}
		
		return success ? ResponseEntity.ok("message: Successfully created post") : ResponseEntity.badRequest().body("message: Error creating your post");
	}
	
	//@PreAuthorize("hasRole('ALUMNI')")
	@RequestMapping(method=RequestMethod.DELETE, value="postjob/{type}/{id}")
	public ResponseEntity<String> deletePost(@PathVariable String type, @PathVariable int id){
		
		boolean success = false;
		if(type.equals("joblink")) {
			success = jobLinkService.deletePost(id);
		}
		if(type.equals("referral")) {
			success = jobReferService.deletePost(id);
		}
		
		return success ? ResponseEntity.ok("message: Successfully deleted your post") : ResponseEntity.badRequest().body("Error: Unable to delete your post. You may not have rights to delete");
	}
	
	//@PreAuthorize("hasRole('ALUMNI')")
	@RequestMapping(method=RequestMethod.PUT, value="postjob/{type}/{id}")
	public ResponseEntity<String> updatePost(@RequestBody String post, @PathVariable String type, @PathVariable int id, @RequestHeader("Authorization") String token){
		
		boolean success = false;
		if(type.equals("joblink")) {
			success = jobLinkService.updatePost(post, id, token);
		}
		if(type.equals("referral")) {
			success = jobReferService.updatePost(post, id, token);
		}
		
		return success ? ResponseEntity.ok("message: Successfully updated post") : ResponseEntity.badRequest().body("Error: Unable to update your post. You may not have rights to update.");
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="postjob/referral/update/{id}")
	public ResponseEntity<String> updatePostReferralRequest(@RequestBody String referalRequest, @PathVariable int id, @RequestHeader("Authorization") String token){
		boolean success = jobReferService.updatePostReferralRequest(referalRequest, id, token);
		return success ? ResponseEntity.ok("message: Successfully updated the referral request") : ResponseEntity.badRequest().body("Error: Unable to update your post. You may not have rights to update.");
	}
	
}
