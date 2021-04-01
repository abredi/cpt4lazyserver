package com.cpt4lazy.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt4lazy.springboot.dao.JobLinkPostRepository;
import com.cpt4lazy.springboot.dao.UserRoleRepository;
import com.cpt4lazy.springboot.entity.Alumni;
import com.cpt4lazy.springboot.entity.JobLinkPost;
import com.cpt4lazy.springboot.entity.Post;
import com.cpt4lazy.springboot.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobLinkPostService {
	
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired 
	private JobLinkPostRepository linkPostRepo;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;

	public boolean createPost(String json) throws JsonMappingException, JsonProcessingException {
		
		JSONObject jsonObj = new JSONObject(json);
		User user = userService.findUserByEmail(jsonObj.getString("email"));
		
		if(user == null)
			return false;
		
		String post = jsonObj.getJSONArray("post").toString();
		if(post.isEmpty())
			return false;
				
		List<JobLinkPost> posts = parsePost(post);
		Alumni alumni = null;
		
		if(!(user.getRole() instanceof Alumni))
			return false;
		
		alumni = (Alumni)user.getRole();
		List<Post> postList = alumni.getPost() == null ? new ArrayList<>() : new ArrayList<>(alumni.getPost());
		for(JobLinkPost p : posts) {
			p.setId(sequenceGenerator.generateSequence(Post.SEQUENCE_NAME));
			linkPostRepo.save(p);
			postList.add(p);
		}
		alumni.setPost(postList);		
		userRoleRepo.save(alumni);
		return true;
	}
	
	private List<JobLinkPost> parsePost(String post) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<JobLinkPost> p = objectMapper.readValue(post, new TypeReference<ArrayList<JobLinkPost>>(){});
		System.out.println(Arrays.toString(p.toArray()));
		return p;
	}

	public boolean deletePost(String json, String id) {
		
		linkPostRepo.deleteById(id);
		return true;
	}

	public boolean updatePost(String json, String id) {
		//linkPostRepo.
		return false;
	}
	
}
