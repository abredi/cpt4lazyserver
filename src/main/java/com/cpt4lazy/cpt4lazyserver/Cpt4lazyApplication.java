package com.cpt4lazy.cpt4lazyserver;


import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
public class Cpt4lazyApplication {
	
//	@Autowired
//	private UserRepository userRepo;
//	
//	@Autowired
//	private UserRoleRepository userRoleRepo;
//	
//	@Autowired 
//	private AlumniRepository alumniRepo;
//	
//	@Autowired
//	private SequenceGeneratorService sequenceGenerator;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Cpt4lazyApplication.class, args);
	}
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cpt4lazy.controller"))
                .build()
                .apiInfo(apiDetails());
        
    }
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"CPT4Lazy API",
				"API docuemntation for CPT4Lazy Application",
				"1.0",
				"Free to use by MIU Student and Alumni",
				new springfox.documentation.service.Contact("CPT4Lazy Developers", "http://CPT4Lazy.com", "cpt4lazy@miu.com"),
				"API License",
				"http://CPT4Lazy.com",
				Collections.emptyList());
	}
	
//	@Bean
//    CommandLineRunner init(UserRoleRepository roleRepository) {
//
//        return args -> {
//        	//initialize 2 alumni in database
//    		Alumni a1 = new Alumni("John Doe","+1 312-452-7458","Seatle","ALUMNI","Full Stack Developer", "Microsoft");
//    		a1.setId(sequenceGenerator.generateSequence(UserRole.SEQUENCE_NAME));
//    		Alumni a2 = new Alumni("Joseph Powers","+1 312-457-7458","Chicago","ALUMNI","Technical Architect", "Apple");
//    		a2.setId(sequenceGenerator.generateSequence(UserRole.SEQUENCE_NAME));
//    		
//    		
//    		//initialize 2 JobSeeker in database
//    		JobSeeker js1 = new JobSeeker("Roger Tyson","+1 412-452-7458","Minneapolis","STUDENT","Software Developer","Google");
//    		js1.setId(sequenceGenerator.generateSequence(UserRole.SEQUENCE_NAME));
//    		JobSeeker js2 = new JobSeeker("Sharon Stone","+1 512-452-7458", "Newyork","STUDENT", "Mobile Application Developer","Apple");
//    		js2.setId(sequenceGenerator.generateSequence(UserRole.SEQUENCE_NAME));
//    		
//    		
////    		List<User> users = new ArrayList<>();
//    		User u1 = new User("jdoe@email.com", "password1", a1);
//    		u1.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
//    		User u2 = new User("jpowers@email.com", "password2", a2);
//    		u2.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
//    		User u3 = new User("rtyson@email.com", "password3", js1);
//    		u3.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
//    		User u4 = new User("sstone@email.com", "password4", js2);
//    		u4.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
//    		
////    		users.add(u1);
////    		users.add(u2);
////    		users.add(u3);
////    		users.add(u4);
//    		
//    		alumniRepo.save(a1);
//    		alumniRepo.save(a2);
//    		userRoleRepo.save(js1);
//    		userRoleRepo.save(js2);
//    		userRepo.save(u1);
//    		userRepo.save(u2);
//    		userRepo.save(u3);
//    		userRepo.save(u4);
//    		
//    		
//    		System.out.println("****************************************");
//    		List<User> users = userRepo.findAll();
//    		
//    		for(User u : users) {
//    			System.out.println(u.toString());
//    		}
//
//            
//        };
//
//    }
}
