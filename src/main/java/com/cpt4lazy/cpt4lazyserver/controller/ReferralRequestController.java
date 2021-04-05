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

import com.cpt4lazy.cpt4lazyserver.entity.ReferralRequest;
import com.cpt4lazy.cpt4lazyserver.service.ReferralRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class ReferralRequestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReferralRequestController.class.getName()); 
	
	@Autowired
	private ReferralRequestService referRequestService;
	
	@RequestMapping(method=RequestMethod.POST, value="request/{referalId}")
	public ResponseEntity<?> sendReferralRequest(@RequestBody String referralRequest, @PathVariable int referalId) throws JsonMappingException, JsonProcessingException {
		
		boolean success = referRequestService.sendReferralRequest(referralRequest, referalId);
		return success ? ResponseEntity.ok("Successfully added experience/s") : ResponseEntity.badRequest().body("Error adding your experience/s");
	
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/request")
	public ResponseEntity<?> viewAllRequest(@RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		List<ReferralRequest>  refRequestList = referRequestService.viewAllRequest(token);
		return refRequestList != null ? ResponseEntity.ok(refRequestList) : ResponseEntity.badRequest().body("Error retrieving your referral request/s");
	}
	
	

	
}
