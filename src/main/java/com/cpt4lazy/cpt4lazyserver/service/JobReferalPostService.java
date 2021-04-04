package com.cpt4lazy.cpt4lazyserver.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt4lazy.cpt4lazyserver.dao.JobReferalPostRepository;
import com.cpt4lazy.cpt4lazyserver.dao.UserRoleRepository;
import com.cpt4lazy.cpt4lazyserver.entity.Alumni;
import com.cpt4lazy.cpt4lazyserver.entity.JobReferalPost;
import com.cpt4lazy.cpt4lazyserver.entity.Post;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobReferalPostService {
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private CustomUserDetailService userService;

	@Autowired 
	private JobReferalPostRepository referPostRepo;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;

	public boolean createPost(String json) throws JsonProcessingException {
		
		JSONObject jsonObj = new JSONObject(json);
		User user = userService.findUserByEmail(jsonObj.getString("email"));
		
		if(user == null)
			return false;
		
		String post = jsonObj.getJSONArray("post").toString();
		if(post.isEmpty())
			return false;
				
		List<JobReferalPost> posts = parsePost(post);
		Alumni alumni = null;
		
		if(!(user.getRole() instanceof Alumni))
			return false;
		
		alumni = (Alumni)user.getRole();
		List<Post> postList = alumni.getPost() == null ? new ArrayList<>() : new ArrayList<>(alumni.getPost());
		for(JobReferalPost p : posts) {
			p.setId(sequenceGenerator.generateSequence(Post.SEQUENCE_NAME));
			referPostRepo.save(p);
			postList.add(p);
		}
		alumni.setPost(postList);		
		userRoleRepo.save(alumni);
		return true;
	}
	
	private List<JobReferalPost> parsePost(String post) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<JobReferalPost> p = objectMapper.readValue(post, new TypeReference<ArrayList<JobReferalPost>>(){});
		return p;
	}

	public boolean deletePost(String json, String id) {
		return false;
	}

	public boolean updatePost(String json, String id) {
		return false;
	}

}