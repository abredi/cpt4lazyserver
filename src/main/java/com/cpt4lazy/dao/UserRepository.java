package com.cpt4lazy.dao;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cpt4lazy.entity.User;


public interface UserRepository extends MongoRepository<User, String>{

	User findByEmail(String email);

}
