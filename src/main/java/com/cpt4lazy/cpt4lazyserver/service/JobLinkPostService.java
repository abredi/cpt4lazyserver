package com.cpt4lazy.cpt4lazyserver.service;

import com.cpt4lazy.cpt4lazyserver.dao.JobLinkPostRepository;
import com.cpt4lazy.cpt4lazyserver.dao.UserRoleRepository;
import com.cpt4lazy.cpt4lazyserver.entity.Alumni;
import com.cpt4lazy.cpt4lazyserver.entity.JobLinkPost;
import com.cpt4lazy.cpt4lazyserver.entity.Post;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

	public boolean createPost(String json) throws JsonProcessingException {
		
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
	
	private List<JobLinkPost> parsePost(String post) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<JobLinkPost> p = objectMapper.readValue(post, new TypeReference<ArrayList<JobLinkPost>>(){});
		return p;
	}

	public boolean deletePost(String json, String id) {
		
		linkPostRepo.deleteById(id);
		return true;
	}

	public boolean updatePost(String json, String id) {
		return false;
	}
	
}
