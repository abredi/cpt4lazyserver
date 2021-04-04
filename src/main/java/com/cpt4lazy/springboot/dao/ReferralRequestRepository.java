package com.cpt4lazy.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.cpt4lazy.springboot.entity.ReferralRequest;

public interface ReferralRequestRepository extends MongoRepository<ReferralRequest, String>{
	
	@Query("{ '_id' : ?0 }")
	Optional<ReferralRequest> findById(int id);
	
	@Query("{ 'requestorEmail' : ?0  }")
	List<ReferralRequest> findAllByEmail(String email);
}
