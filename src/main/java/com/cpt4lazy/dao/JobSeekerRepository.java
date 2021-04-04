package com.cpt4lazy.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.entity.JobSeeker;

public interface JobSeekerRepository extends MongoRepository<JobSeeker, String>{

}
