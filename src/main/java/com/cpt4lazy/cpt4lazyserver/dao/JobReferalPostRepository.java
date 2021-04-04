package com.cpt4lazy.cpt4lazyserver.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cpt4lazy.cpt4lazyserver.entity.JobReferalPost;

public interface JobReferalPostRepository extends MongoRepository<JobReferalPost, String>{
	@Query("{ '_id' : ?0 }")
	Optional<JobReferalPost> findById(int id);
	
	@Query("{ '_id' : ?0 }")
	Optional<JobReferalPost> findOne(int id);	
	
	@Query("{ 'postedBy' : ?0  }")
	List<JobReferalPost> findAllByUser(String email);
	
//	@Query("{ '_id' : ?0 }")
//	void deleteById(int id);
}
