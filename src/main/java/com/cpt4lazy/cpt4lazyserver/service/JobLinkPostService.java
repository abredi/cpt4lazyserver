package com.cpt4lazy.cpt4lazyserver.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt4lazy.cpt4lazyserver.dao.JobLinkPostRepository;
import com.cpt4lazy.cpt4lazyserver.dao.PostRepository;
import com.cpt4lazy.cpt4lazyserver.entity.JobLinkPost;
import com.cpt4lazy.cpt4lazyserver.entity.Post;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.helper.CPT4LazyUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobLinkPostService {
	
	private static final Logger logger = LoggerFactory.getLogger(JobLinkPostService.class);
	
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired 
	private JobLinkPostRepository linkPostRepo;
	
	@Autowired 
	private PostRepository postRepo;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	private CPT4LazyUtility utility;

	public boolean createPost(String post, String token) throws JsonMappingException, JsonProcessingException {
	
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		if(!utility.isALUMNI(user)) 
			return false;
		
		if(post.isEmpty())
			return false;
				
		JobLinkPost jlPost = parsePost(post);
		jlPost.setDatePosted(LocalDate.now());
		jlPost.setPostedby(user.getEmail());
		jlPost.setId(sequenceGenerator.generateSequence(Post.SEQUENCE_NAME));
			
		postRepo.save(jlPost);
		return true;
	}
	
	public boolean deletePost(int id) {
		Optional<JobLinkPost> jlp = linkPostRepo.findById(id);
		if(jlp != null)
			linkPostRepo.delete(jlp.get());
		
		return linkPostRepo.findById(id) == null ? true : false;
	}

	public boolean updatePost(String post, int id, String token) {
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		if(!utility.isALUMNI(user)) 
			return false;
		
		Optional<JobLinkPost> jlpOption = linkPostRepo.findById(id);
		if(!jlpOption.isEmpty()) {
			try {
				
				JobLinkPost updatedJlp = parsePost(post);
				if(updatedJlp != null) {
					updatedJlp.setId(id);
					updatedJlp.setPostedby(email);
					updatedJlp.setDatePosted(LocalDate.now());
					postRepo.save(updatedJlp);
					
			        return true;
				}
			}
			catch (JsonMappingException e){
				logger.error("Error on mapping json string to JobReferalPost object: {}", e.getMessage());
			}
			catch (JsonProcessingException e) {
				logger.error("Error on processing json string to JobReferalPost object: {}", e.getMessage());
			}
		}
		return false;
	}

	public List<JobLinkPost> viewPost() {		
		return linkPostRepo.findAll();
	}

	public List<JobLinkPost> viewPost(String token) {
		String email = utility.getEmailFromToken(token);
		return linkPostRepo.findAllByUser(email);
	}
	
	private JobLinkPost parsePost(String post) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JobLinkPost p = objectMapper.readValue(post, new TypeReference<JobLinkPost>(){});
		System.out.println(p);
		return p;
	}
	
}
