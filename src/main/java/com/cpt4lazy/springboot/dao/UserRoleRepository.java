package com.cpt4lazy.springboot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.springboot.entity.UserRole;

public interface UserRoleRepository extends MongoRepository<UserRole, String>{

}
