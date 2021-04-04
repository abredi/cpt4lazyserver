package com.cpt4lazy.cpt4lazyserver.service;

import com.cpt4lazy.cpt4lazyserver.dao.ExperienceRepository;
import com.cpt4lazy.cpt4lazyserver.dao.UserRoleRepository;
import com.cpt4lazy.cpt4lazyserver.entity.Alumni;
import com.cpt4lazy.cpt4lazyserver.entity.Experience;
import com.cpt4lazy.cpt4lazyserver.entity.JobSeeker;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.helper.CPT4LazyUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest()
class ExperienceServiceTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ExperienceService experienceService;

    @MockBean
    private ExperienceRepository expRepo;

    @MockBean
    private UserRoleRepository userRoleRepo;

    @MockBean
    private CustomUserDetailService customUserDetailService;

    @MockBean
    private CPT4LazyUtility cpt4LazyUtility;

    @Test
    void experienceIsCreated_shouldReturnFalseForUnavailableUser() throws JsonProcessingException {
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer", "Microsoft");

        Experience experience = new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));

        User user = new User("jdeo@email.com", "password1", jobSeeker);

        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(user.getEmail());
        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(null);

        String jsonExperience = mapper.writeValueAsString(experience);

        assertFalse(experienceService.createExperience(jsonExperience, anyString()));
    }

    @Test
    void experienceIsCreated_shouldReturnFalseForUserIsNotJobSeeker() throws JsonMappingException, JsonProcessingException {
        Alumni alumni = new Alumni("john",
                "+12341543",
                "texas",
                "Alumni",
                "Java Developer", "Microsoft");

        Experience experience = new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));

        User user = new User("jdeo@email.com", "password1", alumni);

        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(user.getEmail());
        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(user);

        String jsonExperience = mapper.writeValueAsString(experience);

        assertFalse(experienceService.createExperience(jsonExperience, anyString()));
    }

    @Test
    void experienceIsCreated_shouldReturnTrueForValidUser() throws JsonMappingException, JsonProcessingException {
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer", "Microsoft");

        Experience experience = new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));

        User user = new User("jdeo@email.com", "password1", jobSeeker);

        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(user.getEmail());

        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(user);
        given(expRepo.save(experience)).willReturn(experience);
        given(userRoleRepo.save(user.getRole())).willReturn(jobSeeker);

        String jsonExperience = mapper.writeValueAsString(experience);

        assertTrue(experienceService.createExperience(jsonExperience, anyString()));
    }


    /**
     * updateExperience
     */
    @Test
    void updateExperience_shouldReturnFalseForUnavailableUser() throws JsonProcessingException {
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer", "Microsoft");

        Experience experience = new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));

        User user = new User("jdeo@email.com", "password1", jobSeeker);

        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(user.getEmail());

        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(null);


        String jsonExperience = mapper.writeValueAsString(experience);

        assertFalse(experienceService.updateExperience(jsonExperience,
                Integer.parseInt(String.valueOf(user.getId())), anyString()));
    }

    @Test
    void updateExperience_shouldReturnFalseForUserIsNotJobSeeker() throws JsonMappingException, JsonProcessingException {
        Alumni alumni = new Alumni("john",
                "+12341543",
                "texas",
                "Alumni",
                "Java Developer", "Microsoft");

        Experience experience = new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));

        User user = new User("jdeo@email.com", "password1", alumni);

        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(user.getEmail());

        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(user);
        given(expRepo.save(experience)).willReturn(experience);

        given(expRepo.findById(anyInt())).willReturn(Optional.of(experience));

        String jsonExperience = mapper.writeValueAsString(experience);

        assertFalse(experienceService.updateExperience(jsonExperience,
                Integer.parseInt(String.valueOf(user.getId())), anyString()));
    }

    @Test
    void updateExperience_shouldReturnTrueForValidUser() throws JsonMappingException, JsonProcessingException {
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer", "Microsoft");

        Experience experience = new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));

        User user = new User("jdeo@email.com", "password1", jobSeeker);

        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(user.getEmail());

        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(user);
        given(expRepo.save(experience)).willReturn(experience);

        given(expRepo.findById(anyInt())).willReturn(Optional.of(experience));

        String jsonExperience = mapper.writeValueAsString(experience);

        assertTrue(experienceService.updateExperience(jsonExperience, Integer.parseInt(String.valueOf(user.getId())), anyString()));
    }
}