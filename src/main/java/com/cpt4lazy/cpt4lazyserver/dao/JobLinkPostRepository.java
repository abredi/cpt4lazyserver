package com.cpt4lazy.cpt4lazyserver.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cpt4lazy.cpt4lazyserver.entity.JobLinkPost;


public interface JobLinkPostRepository extends MongoRepository<JobLinkPost, String>{
	
	@Query("{ '_id' : ?0 }")
    Optional<JobLinkPost> findById(int id);
	
	@Query("{ '_id' : ?0 }")
	Optional<JobLinkPost> findOne(int id);
	
	@Query("{ 'postedBy' : ?0  }")
	List<JobLinkPost> findAllByUser(String email);
	
//	@Query("{ '_id' : ?0 }")
//	void deleteById(int id);
}
