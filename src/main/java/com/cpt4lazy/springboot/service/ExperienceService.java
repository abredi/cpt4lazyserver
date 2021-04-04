package com.cpt4lazy.springboot.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt4lazy.springboot.dao.ExperienceRepository;
import com.cpt4lazy.springboot.dao.UserRoleRepository;
import com.cpt4lazy.springboot.entity.Experience;
import com.cpt4lazy.springboot.entity.JobSeeker;
import com.cpt4lazy.springboot.entity.User;
import com.cpt4lazy.springboot.helper.CPT4LazyUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExperienceService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExperienceService.class.getName()); 
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private ExperienceRepository expRepo;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private CPT4LazyUtility utility;
	
	public boolean createExperience(String experience, String token) throws JsonMappingException, JsonProcessingException {
		
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		if(user == null) {
			logger.debug("Message: user is null");
			return false;
		}
		
		if(!(user.getRole() instanceof JobSeeker)) {
			logger.debug("Message: user is not Job seeker/student");
			return false;
		}
			
		Experience exp = parseExperience(experience);
		JobSeeker js = (JobSeeker)user.getRole();
		exp.setId(sequenceGenerator.generateSequence(Experience.SEQUENCE_NAME));
		List<Experience> e = js.getExperience() == null ? new ArrayList<>() : new ArrayList<>(js.getExperience());
		e.add(exp);
		expRepo.save(exp);
		
		js.setExperience(e);		
		userRoleRepo.save(js);
		
		return true;
	}
	
	public boolean updateExperience(String experience, int id, String token) throws JsonMappingException, JsonProcessingException {
		
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		if(user == null) {
			logger.debug("Message: user is null");
			return false;
		}
		
		if(experience.isEmpty()) {
			logger.debug("Message: experience data is empty");
			return false;
		}
			
		Experience exp = parseExperience(experience);
		if(!(user.getRole() instanceof JobSeeker)) {
			logger.debug("Message: user is not Job seeker/student");
			return false;
		}
		
		JobSeeker js = (JobSeeker)user.getRole();
		Optional<Experience> expTobeUpdated = expRepo.findById(id);
		if(expTobeUpdated == null) {
			logger.debug("Message: unable to find experience with the given id");
			return false;
		}
		exp.setId(id);
		expRepo.save(exp);
		return true;
	}

	public boolean deleteExperience(int id, String token) throws JsonMappingException, JsonProcessingException {
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		if(user == null) {
			logger.debug("Message: user is null");
			return false;
		}
		
		if(!(user.getRole() instanceof JobSeeker)) {
			logger.debug("Message: user is not Job seeker/student");
			return false;
		}
		
		Optional<Experience> exp = expRepo.findById(id);
		if(exp != null)
			expRepo.delete(exp.get());		
		return true;

	}
	
	private Experience parseExperience(String experience) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.registerModule(new JavaTimeModule());
		Experience exp = objectMapper.readValue(experience, new TypeReference<Experience>(){});
		//System.out.println(Arrays.toString(exp.toArray()));
		return exp;
	}

	public List<Experience> viewExpereince(String token) {
		
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		if(user == null) {
			logger.debug("Message: user is null");
			return null;
		}
		
		if(!(user.getRole() instanceof JobSeeker)) {
			logger.debug("Message: user is not Job seeker/student");
			return null;
		}
		
		JobSeeker js = (JobSeeker)user.getRole();
		
		return js.getExperience();
		
	}

}
