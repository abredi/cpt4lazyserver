package com.cpt4lazy.cpt4lazyserver.controller;

import com.cpt4lazy.cpt4lazyserver.entity.ReferralRequest;
import com.cpt4lazy.cpt4lazyserver.service.ReferralRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReferralRequestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReferralRequestController.class.getName()); 
	
	@Autowired
	private ReferralRequestService referRequestService;
	
	@RequestMapping(method=RequestMethod.POST, value="request/{referalId}")
	public ResponseEntity<?> sendReferralRequest(@RequestBody String referralRequest, @PathVariable int referalId,@RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		
		boolean success = referRequestService.sendReferralRequest(referralRequest, referalId, token);
		return success ? ResponseEntity.ok("Successfully sent the referral request") : ResponseEntity.badRequest().body("Error sending your referral request");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/request")
	public ResponseEntity<?> viewAllRequest(@RequestHeader("Authorization") String token) throws JsonMappingException, JsonProcessingException {
		List<ReferralRequest>  refRequestList = referRequestService.viewAllRequest(token);
		return refRequestList != null ? ResponseEntity.ok(refRequestList) : ResponseEntity.badRequest().body("Error sending your referral request/s");
	}
	
	

	
}
