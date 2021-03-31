package com.cpt4lazy.springboot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.springboot.entity.JobSeeker;

public interface JobSeekerRepository extends MongoRepository<JobSeeker, String>{

}
