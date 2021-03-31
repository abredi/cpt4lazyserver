package com.cpt4lazy.springboot.dao;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cpt4lazy.springboot.entity.User;


public interface UserRepository extends MongoRepository<User, String>{

	User findByEmail(String email);

}
