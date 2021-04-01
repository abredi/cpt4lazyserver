package com.cpt4lazy.springboot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.springboot.entity.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
