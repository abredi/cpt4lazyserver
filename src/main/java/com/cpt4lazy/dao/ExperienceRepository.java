package com.cpt4lazy.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cpt4lazy.entity.Experience;
import com.cpt4lazy.entity.JobLinkPost;


public interface ExperienceRepository extends MongoRepository<Experience, String>{
	@Query("{ '_id' : ?0 }")
    Optional<Experience> findById(int id);
	
	@Query("{ '_id' : ?0 }")
	Optional<Experience> findOne(int id);
	
//	@Query("{ '_id' : ?0 }")
//	void deleteById(int id);
	
//	@Query("{ 'postedBy' : ?0  }")
//	List<Experience> findAllByUser(String email);
}
