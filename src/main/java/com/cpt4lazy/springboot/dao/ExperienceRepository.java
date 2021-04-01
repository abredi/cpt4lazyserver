package com.cpt4lazy.springboot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cpt4lazy.springboot.entity.Experience;

public interface ExperienceRepository extends MongoRepository<Experience, String>{

}
