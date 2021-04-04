package com.cpt4lazy.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.entity.UserRole;

public interface UserRoleRepository extends MongoRepository<UserRole, String>{

}
