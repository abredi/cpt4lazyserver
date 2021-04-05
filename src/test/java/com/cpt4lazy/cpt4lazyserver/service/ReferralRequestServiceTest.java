package com.cpt4lazy.cpt4lazyserver.service;

import com.cpt4lazy.cpt4lazyserver.dao.JobReferalPostRepository;
import com.cpt4lazy.cpt4lazyserver.dao.ReferralRequestRepository;
import com.cpt4lazy.cpt4lazyserver.entity.JobReferalPost;
import com.cpt4lazy.cpt4lazyserver.entity.JobSeeker;
import com.cpt4lazy.cpt4lazyserver.entity.ReferralRequest;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.helper.CPT4LazyUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReferralRequestServiceTest {
    @MockBean
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    private ReferralRequestService referralRequestService;

    @MockBean
    private CustomUserDetailService customUserDetailService;

    @MockBean
    private CPT4LazyUtility cpt4LazyUtility;

    @MockBean
    private JobReferalPostRepository jobReferalPostRepository;

    @MockBean
    private ReferralRequestRepository referralRequestRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void sendReferralRequest() throws JsonProcessingException {
        int referralId = 1;
         ReferralRequest referralRequest = new ReferralRequest("jdeo@email.com", LocalDate.of(2020, 3, 4),
                "active" );
        JobReferalPost referralPost = new JobReferalPost("jdeo@email.com", "random text", LocalDate.of(2020, 3, 4),
                "active" );

        given(jobReferalPostRepository.findById(referralId)).willReturn(Optional.of(referralPost));
        given(sequenceGenerator.generateSequence(ReferralRequest.SEQUENCE_NAME)).willReturn(1L);
        given(referralRequestRepository.save(referralRequest)).willReturn(referralRequest);
        given(jobReferalPostRepository.save(referralPost)).willReturn(referralPost);


        String jsonReferralRequest = mapper.writeValueAsString(referralRequest);

        assertTrue(referralRequestService.sendReferralRequest(jsonReferralRequest,
                 referralId));
    }

    @Test
    void sendReferralRequest_shouldReturnFalseForUnavailableReferral() throws JsonProcessingException {
        int referralId = 1;
        ReferralRequest referralRequest = new ReferralRequest("jdeo@email.com", LocalDate.of(2020, 3, 4),
                "active" );
        JobReferalPost referralPost = new JobReferalPost("jdeo@email.com", "random text", LocalDate.of(2020, 3, 4),
                "active" );

        given(jobReferalPostRepository.findById(referralId)).willReturn(Optional.empty());
        given(sequenceGenerator.generateSequence(ReferralRequest.SEQUENCE_NAME)).willReturn(1L);
        given(referralRequestRepository.save(referralRequest)).willReturn(referralRequest);
        given(jobReferalPostRepository.save(referralPost)).willReturn(referralPost);


        String jsonReferralRequest = mapper.writeValueAsString(referralRequest);

        assertFalse(referralRequestService.sendReferralRequest(jsonReferralRequest,
                referralId));
    }


    @Test
    void viewAllRequest() {
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer", "Microsoft");

        User user = new User("jdeo@email.com", "password1", jobSeeker);
        user.setId(1);
        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(anyString());
        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(user);

        given(referralRequestRepository.findAllByEmail(user.getEmail())).willReturn(mock(List.class));


        assertThat(referralRequestService.viewAllRequest(anyString())).isInstanceOf(List.class);
    }

    @Test
    void viewAllRequest_shouldReturnNull() {
        final String email = "email@provider.org";
        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(email);
        given(customUserDetailService.findUserByEmail(email)).willReturn(null);

        assertNull(referralRequestService.viewAllRequest(anyString()));
    }


}