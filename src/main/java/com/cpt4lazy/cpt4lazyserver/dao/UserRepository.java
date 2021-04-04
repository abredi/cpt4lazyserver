package com.cpt4lazy.cpt4lazyserver.dao;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cpt4lazy.cpt4lazyserver.entity.User;


public interface UserRepository extends MongoRepository<User, String>{

	User findByEmail(String email);

}
