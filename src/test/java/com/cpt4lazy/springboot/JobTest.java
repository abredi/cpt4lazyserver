package com.cpt4lazy.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import com.cpt4lazy.springboot.utils.JobPost;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnJob() {
        ResponseEntity<JobPost[]> entity = restTemplate.getForEntity("/jobs?what=Chicago", JobPost[].class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getHeaders().get("Content-Type")).containsOnly(MediaType.APPLICATION_JSON_VALUE);
    }
}
