package com.cpt4lazy.springboot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.springboot.entity.Alumni;

public interface AlumniRepository extends MongoRepository<Alumni, String>{

}
