package com.cpt4lazy.cpt4lazyserver.controller;

import com.cpt4lazy.cpt4lazyserver.entity.JobPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Todo
 * not finished
 * Not Authorized 401
 */

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobControllerTest {

    private static final String URI_JOB = "/jobs";

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void getJobs() {

        ResponseEntity<JobPost[]> entity = restTemplate.withBasicAuth("ncage@email.com","password3").getForEntity("/jobs?what=Chicago", JobPost[].class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}