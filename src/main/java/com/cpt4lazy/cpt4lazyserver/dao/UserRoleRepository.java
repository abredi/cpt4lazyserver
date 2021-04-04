package com.cpt4lazy.cpt4lazyserver.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.cpt4lazyserver.entity.UserRole;

public interface UserRoleRepository extends MongoRepository<UserRole, String>{

}
