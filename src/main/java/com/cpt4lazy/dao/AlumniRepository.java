package com.cpt4lazy.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.entity.Alumni;

public interface AlumniRepository extends MongoRepository<Alumni, String>{

}
