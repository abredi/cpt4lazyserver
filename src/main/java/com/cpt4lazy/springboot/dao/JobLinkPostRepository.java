package com.cpt4lazy.springboot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.springboot.entity.JobLinkPost;

public interface JobLinkPostRepository extends MongoRepository<JobLinkPost, String>{

}
