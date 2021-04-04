package com.cpt4lazy.cpt4lazyserver.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.cpt4lazyserver.entity.Alumni;

public interface AlumniRepository extends MongoRepository<Alumni, String>{

}
