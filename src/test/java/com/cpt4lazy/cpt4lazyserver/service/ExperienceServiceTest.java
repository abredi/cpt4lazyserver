package com.cpt4lazy.cpt4lazyserver.service;

import com.cpt4lazy.cpt4lazyserver.dao.ExperienceRepository;
import com.cpt4lazy.cpt4lazyserver.entity.Experience;
import com.cpt4lazy.cpt4lazyserver.entity.JobSeeker;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest()
class ExperienceServiceTest {

    @Autowired
    private ExperienceService experienceService;

    @MockBean
    private ExperienceRepository expRepo;

    //Create Experience
    @Test
    public void experienceIsCreated()throws JsonMappingException, JsonProcessingException {


        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer","Microsoft");
        String experience1 = "this is for test";
        Experience experience =new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));
        User user = new User("jdeo@email.com", "password1",jobSeeker);
        given(expRepo.save(experience)).willReturn(experience);
        assertTrue(experienceService.createExperience(user.toString(), experience1));
    }

    @Test
     void experienceIsNotCreated()throws JsonMappingException, JsonProcessingException {

        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer","Microsoft");
        Experience experience =new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));

        User user = new User("jdeo@email.com", "password1",jobSeeker);
        given(expRepo.save(experience)).willReturn(experience);
//        assertTrue(experienceService.createExperience(user, experience.toString()));

    }

    // Experience update
    @Test
     void experienceIsUpdated() throws JSONException, JsonProcessingException {

        String experience1 = "IBM Team leader team management 15/04/1989 16/04/2000";
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer","Microsoft");
        Experience experience =new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));

        User user = new User("jdeo@email.com", "password1",jobSeeker);
        given(expRepo.save(experience)).willReturn(experience);
//        assertTrue(experienceService.updateExperience(experience1));
    }

    @Test
    void experienceIsNotUpdated() throws JSONException, JsonProcessingException {

        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer","Microsoft");
        Experience experience = new Experience("IBM", "Developer",
                "Adminstrition", LocalDate.of(1999, 4, 5),
                LocalDate.of(2000, 11, 3));
        String json = "{ \"microsoft\": \"developer\", \"management\": [ {"
                + "\"1955/9/6\": \"2000/2/7\"}]}";
        User user = new User("jdeo@email.com", "password1", jobSeeker);
//        experienceService.deleteExperience(json);
//        verify(expRepo.times(1)).deleteAll(jobSeeker.getExperience());
    }

    @Test
    void deleteExperience_shouldReturnFalseForEmptyObject() throws JsonProcessingException {
//        assertFalse(experienceService.deleteExperience("{}"));
    }
}