package com.cpt4lazy.cpt4lazyserver.service;


import com.cpt4lazy.cpt4lazyserver.dao.JobLinkPostRepository;
import com.cpt4lazy.cpt4lazyserver.dao.PostRepository;
import com.cpt4lazy.cpt4lazyserver.entity.JobLinkPost;
import com.cpt4lazy.cpt4lazyserver.entity.JobSeeker;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.helper.CPT4LazyUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobLinkPostServiceTest {

    @Autowired
    private JobLinkPostService postService;

    @MockBean
    private CPT4LazyUtility utility;

    @MockBean
    private JobLinkPostRepository repository;

    @MockBean
    private CustomUserDetailService userService;

    @MockBean
    private PostRepository postRepo;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void createPost() throws JsonProcessingException {

        String json = "{ \"email\": \"jdeo@email.com\", \"post\": [ {"
                + "\"jobURL\": \"http://example.com/career/123\"}]}";

        JobLinkPost jobLinkPost = new JobLinkPost();
        jobLinkPost.setJobURL("http://example.com/career/123");
        jobLinkPost.setPostText("Actively hiring");

        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer", "Microsoft");

        User user = new User("jdeo@email.com", "password1", jobSeeker);

        given(utility.getEmailFromToken(anyString())).willReturn(user.getEmail());
        given(repository.save(jobLinkPost)).willReturn(jobLinkPost);
        given(utility.isALUMNI(user)).willReturn(true);
        given(userService.findUserByEmail(user.getEmail())).willReturn(user);
        String jPost = mapper.writeValueAsString(jobLinkPost);

        given(postRepo.save(jobLinkPost)).willReturn(jobLinkPost);

        assertTrue(postService.createPost(jPost, "noop-token"));
    }
}