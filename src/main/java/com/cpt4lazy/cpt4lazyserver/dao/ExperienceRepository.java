package com.cpt4lazy.cpt4lazyserver.dao;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cpt4lazy.cpt4lazyserver.entity.Experience;


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
