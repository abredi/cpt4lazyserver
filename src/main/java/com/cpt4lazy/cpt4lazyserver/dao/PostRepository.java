package com.cpt4lazy.cpt4lazyserver.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cpt4lazy.cpt4lazyserver.entity.Post;

public interface PostRepository extends MongoRepository<Post, String>{
	@Query("{ '_id' : ?0 }")
    Optional<Post> findById(int id);
	
	@Query("{ '_id' : ?0 }")
	Optional<Post> findOne(int id);
	
	@Query("{ 'postedBy' : ?0  }")
	List<Post> findAllByUser(String email);
	
//	@Query("{ '_id' : ?0 }")
//	void deleteById(int id);
}
