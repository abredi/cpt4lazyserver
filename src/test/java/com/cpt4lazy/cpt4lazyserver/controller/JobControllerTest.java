package com.cpt4lazy.cpt4lazyserver.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Todo
 * not finished
 * Not Authorized 401
 */

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobControllerTest {

//    private static final String URI_JOB = "/jobs";
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    void getJobs() {
//
//        ResponseEntity<JobPost[]> entity = restTemplate.getForEntity("/jobs?what=Chicago", JobPost[].class);
//
//        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(entity.getHeaders().get("Content-Type")).containsOnly(MediaType.APPLICATION_JSON_VALUE);
//    }
}