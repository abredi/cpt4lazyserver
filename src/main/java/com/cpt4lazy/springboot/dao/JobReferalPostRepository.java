package com.cpt4lazy.springboot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.springboot.entity.JobReferalPost;

public interface JobReferalPostRepository extends MongoRepository<JobReferalPost, String>{

}
