package com.cpt4lazy.cpt4lazyserver.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt4lazy.cpt4lazyserver.dao.ExperienceRepository;
import com.cpt4lazy.cpt4lazyserver.dao.UserRepository;
import com.cpt4lazy.cpt4lazyserver.dao.UserRoleRepository;
import com.cpt4lazy.cpt4lazyserver.entity.Experience;
import com.cpt4lazy.cpt4lazyserver.entity.JobSeeker;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class ExperienceService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private ExperienceRepository expRepo;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	private CustomUserDetailService userService;
	
	public boolean createExperience(User user, String experience) throws JsonMappingException, JsonProcessingException {
		List<Experience> exp = parseExperience(experience);
		JobSeeker js = null;
		if(user == null)
			System.out.println("Error: user is null");
		System.out.println("Role: " + user.getRole());
		System.out.println(user.getRole() instanceof JobSeeker);
		if(!(user.getRole() instanceof JobSeeker))
			return false;
		
		js = (JobSeeker)user.getRole();
		for(Experience e : exp) {
			e.setId(sequenceGenerator.generateSequence(Experience.SEQUENCE_NAME));
			expRepo.save(e);
		}
		js.setExperience(exp);		
		userRoleRepo.save(js);
		
		return true;
	}
	
	public boolean updateExperience(String json) throws JsonMappingException, JsonProcessingException {
		
		//Check if json string is empty
		if(json.isEmpty())
			return false;
		
		JSONObject jsonObj = new JSONObject(json);
		User user = userService.findUserByEmail(jsonObj.getString("email"));
		if(user == null)
			return false;
		
		String experience = jsonObj.getJSONArray("experience").toString();
		if(experience.isEmpty())
			return false;
		
		List<Experience> exp = parseExperience(experience);
		JobSeeker js = null;
		
		if(!(user.getRole() instanceof JobSeeker))
			return false;
		
		js = (JobSeeker)user.getRole();
		if(js.getExperience() != null) {
			expRepo.deleteAll(js.getExperience());
			for(Experience e : exp) {
				e.setId(sequenceGenerator.generateSequence(Experience.SEQUENCE_NAME));
				expRepo.save(e);
			}
		}
		
		js.setExperience(exp);		
		userRoleRepo.save(js);
		return true;
	}

	public boolean deleteExperience(String json) throws JsonMappingException, JsonProcessingException {
		//Check if json string is empty
		if(json.isEmpty())
			return false;
				
		JSONObject jsonObj = new JSONObject(json);
		User user = userService.findUserByEmail(jsonObj.getString("email"));
		if(user == null)
			return false;
				
		String experience = jsonObj.getJSONArray("experience").toString();
		if(experience.isEmpty())
			return false;
				
		List<Experience> exp = parseExperience(experience);
		JobSeeker js = null;
				
		if(!(user.getRole() instanceof JobSeeker))
			return false;
				
		js = (JobSeeker)user.getRole();
		if(js.getExperience() != null) {
			expRepo.deleteAll(js.getExperience());
		}
		
		if(exp.isEmpty()) {
			js.setExperience(null);
			return true;
		
		}
				
		for(Experience e : exp) {
			e.setId(sequenceGenerator.generateSequence(Experience.SEQUENCE_NAME));
			expRepo.save(e);
		}
				
		js.setExperience(exp);		
		userRoleRepo.save(js);
						
		return true;

	}
	
	private List<Experience> parseExperience(String experience) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.registerModule(new JavaTimeModule());
		List<Experience> exp = objectMapper.readValue(experience, new TypeReference<ArrayList<Experience>>(){});
		System.out.println(Arrays.toString(exp.toArray()));
		return exp;
	}

}
