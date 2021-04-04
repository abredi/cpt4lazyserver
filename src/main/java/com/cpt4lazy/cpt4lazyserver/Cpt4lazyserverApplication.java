package com.cpt4lazy.cpt4lazyserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.cpt4lazy.cpt4lazyserver.dao.AlumniRepository;
import com.cpt4lazy.cpt4lazyserver.dao.UserRepository;
import com.cpt4lazy.cpt4lazyserver.dao.UserRoleRepository;
import com.cpt4lazy.cpt4lazyserver.service.SequenceGeneratorService;



@SpringBootApplication
public class Cpt4lazyserverApplication {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired 
	private AlumniRepository alumniRepo;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Cpt4lazyserverApplication.class, args);
	}
}
