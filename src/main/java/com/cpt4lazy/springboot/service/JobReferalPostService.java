package com.cpt4lazy.springboot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpt4lazy.springboot.dao.JobReferalPostRepository;
import com.cpt4lazy.springboot.dao.PostRepository;
import com.cpt4lazy.springboot.dao.ReferralRequestRepository;
import com.cpt4lazy.springboot.entity.JobReferalPost;
import com.cpt4lazy.springboot.entity.Post;
import com.cpt4lazy.springboot.entity.ReferralRequest;
import com.cpt4lazy.springboot.entity.User;
import com.cpt4lazy.springboot.helper.CPT4LazyUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobReferalPostService {
	
	private static final Logger logger = LoggerFactory.getLogger(JobReferalPostService.class.getName());
	
	@Autowired 
	private PostRepository postRepo;
	
	@Autowired
	private CustomUserDetailService userService;

	@Autowired 
	private JobReferalPostRepository referPostRepo;
	
	@Autowired
	private ReferralRequestRepository refRequestRepo;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	private CPT4LazyUtility utility;

	/***
	 * This will create a new JobReferalPost
	 * @param json
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @author Carl Michael Mapada
	 */
	public boolean createPost(String post, String token) throws JsonMappingException, JsonProcessingException {
		
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);

		if(!utility.isALUMNI(user)) 
			return false;
		
		if(post.isEmpty())
			return false;
				
		JobReferalPost jrPost = parsePost(post);
		jrPost.setDatePosted(LocalDate.now());
		jrPost.setPostedby(user.getEmail());
		jrPost.setId(sequenceGenerator.generateSequence(Post.SEQUENCE_NAME));
			
		postRepo.save(jrPost);
		return true;
	}
	
	/***
	 * This method will handle deletion of a single post
	 * @param json
	 * @param id
	 * @return
	 * @author Carl Michael Mapada
	 */
	public boolean deletePost(int id) {
		Optional<Post> jrp = postRepo.findById(id);
		if(jrp != null)
			postRepo.delete(jrp.get());
		
		return postRepo.findById(id) == null ? true : false;	
	}

	/***
	 * This will update the job referral post with the changes made by the user
	 * @param json
	 * @param id
	 * @return
	 * @author Carl Michael Mapada
	 */
	public boolean updatePost(String post, int id, String token) {
		
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		if(!utility.isALUMNI(user)) 
			return false;
		
		Optional<JobReferalPost> jrpOption = referPostRepo.findById(id);
		if(!jrpOption.isEmpty()) {
			try {
				
				JobReferalPost updatedJrp = parsePost(post);
				if(updatedJrp != null) {
					updatedJrp.setId(id);
					updatedJrp.setPostedby(email);
					updatedJrp.setDatePosted(LocalDate.now());
					referPostRepo.save(updatedJrp);
					
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

	/***
	 * This will retrieved all the job referral post and returns as a list of JobReferalPost
	 * @return
	 * @author Carl Michael Mapada
	 */
	public List<JobReferalPost> viewPost() {
		return referPostRepo.findAll();
	}
	
	/***
	 * This will retrieved all the job referral post by the ALUMNI user
	 * This will return a list of JobReferalPost
	 * @return
	 * @author Carl Michael Mapada
	 */
	public List<JobReferalPost> viewPost(String token) {
		
		String email = utility.getEmailFromToken(token);
		return referPostRepo.findAllByUser(email);
	}
	
	public boolean updatePostReferralRequest(String referalRequest, int id, String token) {
		
		String email = utility.getEmailFromToken(token);
		User user = userService.findUserByEmail(email);
		if(!utility.isALUMNI(user)) 
			return false;
		
		Optional<ReferralRequest> refRequest = refRequestRepo.findById(id);
		try {
			ReferralRequest rr = parseReferralRequest(referalRequest);
			if(refRequest != null) {
				rr.setId(id);	
				refRequestRepo.save(rr);
				return true;
			}
		} 
		catch (JsonMappingException e){
			logger.error("Error on mapping json string to ReferralRequest object: {}", e.getMessage());
		}
		catch (JsonProcessingException e) {
			logger.error("Error on processing json string to ReferralRequest object: {}", e.getMessage());
		}
		return false;
	}
	
	/**
	 * This method will deserialize the JSON string parameter post directly into an instance of the JobReferalPost.
	 * @param post
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @author Carl Michael Mapada
	 */
	@SuppressWarnings("unchecked")
	private JobReferalPost parsePost(String post) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JobReferalPost p = objectMapper.readValue(post, new TypeReference<JobReferalPost>(){});
		return p;
	}
	
	private ReferralRequest parseReferralRequest(String referralRequest) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		ReferralRequest refRequest = objectMapper.readValue(referralRequest, new TypeReference<ReferralRequest>(){});
		return refRequest;
	}

	
	
}
