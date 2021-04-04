package com.cpt4lazy.cpt4lazyserver.service;


import com.cpt4lazy.cpt4lazyserver.dao.JobLinkPostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobLinkPostServiceTest {

    @Autowired
    private JobLinkPostService postService;

    @MockBean
    private JobLinkPostRepository repository;

    @Test
    void createPost() throws JsonProcessingException {
//
//        String json = "{ \"email\": \"ab@lazy.miu\", \"post\": [ {"
//                + "\"jobURL\": \"http://example.com/career/123\"}]}";
//
//        JobLinkPost jobLinkPost = new JobLinkPost("String postText","https://attacomsian.com/blog/processing-json-spring-boot");
//
//        given(repository.save(jobLinkPost)).willReturn(jobLinkPost);
//        assertThat(postService.createPost(json)).isTrue();
    }
}