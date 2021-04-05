package com.cpt4lazy.cpt4lazyserver.service;

import com.cpt4lazy.cpt4lazyserver.dao.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobReferalPostServiceTest {

    @MockBean
    private PostRepository postRepo;

    @Autowired
    private JobReferalPostService jobReferalPostService;

    @Test
    void deletePost_shouldReturnTrue() {
        given(postRepo.findById(anyInt())).willReturn(null);
        assertTrue(jobReferalPostService.deletePost(anyInt()));
    }
}