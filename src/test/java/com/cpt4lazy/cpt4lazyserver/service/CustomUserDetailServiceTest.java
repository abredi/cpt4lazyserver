package com.cpt4lazy.cpt4lazyserver.service;


import com.cpt4lazy.cpt4lazyserver.dao.UserRepository;
import com.cpt4lazy.cpt4lazyserver.entity.JobSeeker;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class CustomUserDetailServiceTest {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @MockBean
    private UserRepository userRepo;

    @MockBean
    private UserDetails userDetail;

    @Test
     void isTrueSearchTest(){

        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer","Microsoft");

        User user = new User("jdeo@email.com", "password1",jobSeeker);
        given(userRepo.findByEmail("jdoe@email.com")).willReturn(user);
        assertEquals(user, customUserDetailService.findUserByEmail("jdoe@email.com"));
    }
    @Test
     void isNotTrueSearchTest(){

        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer","Microsoft");
        User user2 = new User("john@email.com", "password1",jobSeeker);
        given(userRepo.findByEmail("jdoe@email.com")).willReturn(user2);
        assertNotEquals(customUserDetailService.findUserByEmail("jdoe@email.com"), user2.getEmail());
    }


}
