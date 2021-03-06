package com.cpt4lazy.cpt4lazyserver.service;

<<<<<<< HEAD
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

>>>>>>> 0b292784a2277421c5cc82fd5444185d59ccf241
import com.cpt4lazy.cpt4lazyserver.dao.JobReferalPostRepository;
import com.cpt4lazy.cpt4lazyserver.dao.ReferralRequestRepository;
import com.cpt4lazy.cpt4lazyserver.entity.JobReferalPost;
import com.cpt4lazy.cpt4lazyserver.entity.ReferralRequest;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.helper.CPT4LazyUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReferralRequestService {
	
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired 
	private JobReferalPostRepository referPostRepo;
	
	@Autowired
	private ReferralRequestRepository refRequestRepo;
	
	@Autowired
	private CPT4LazyUtility utility;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;

	private static final Logger logger = LoggerFactory.getLogger(ReferralRequestService.class.getName()); 
	
	public boolean sendReferralRequest(String referralRequest, int referalId, String token) {
		
		String email = utility.getEmailFromToken(token);
		//User user = userService.findUserByEmail(email);
		
		try {
			ReferralRequest refRequest = parseReferralRequest(referralRequest);
			Optional<JobReferalPost> refPost = referPostRepo.findById(referalId);

			if(refPost.isEmpty())
				return false;
			JobReferalPost jrp = refPost.get();
			List<ReferralRequest> reqList = jrp.getReferralRequest() == null ? new ArrayList<>() : new ArrayList<>(jrp.getReferralRequest());
			refRequest.setId(sequenceGenerator.generateSequence(ReferralRequest.SEQUENCE_NAME));
			refRequest.setRequestorName(email);
			refRequest.setAskedDate(LocalDate.now());
			reqList.add(refRequest);
			refRequestRepo.save(refRequest);
			jrp.setReferralRequest(reqList);
			referPostRepo.save(jrp);
			
		}

		catch (JsonMappingException e){
			logger.error("Error on mapping json string to JobReferalPost object: {}", e.getMessage());
			return false;
		}
		catch (JsonProcessingException e) {
			logger.error("Error on processing json string to JobReferalPost object: {}", e.getMessage());
			return false;
		}
		
		return true;
	}
	
	private ReferralRequest parseReferralRequest(String referralRequest) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		ReferralRequest refRequest = objectMapper.readValue(referralRequest, new TypeReference<ReferralRequest>(){});
		return refRequest;
	}

	public List<ReferralRequest> viewAllRequest(String token) {
		
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		if(user == null) {
			logger.debug("Message: user is null");
			return null;
		}
		
		List<ReferralRequest> refRequestList = refRequestRepo.findAllByEmail(email);
		return refRequestList;
	}

	

}
