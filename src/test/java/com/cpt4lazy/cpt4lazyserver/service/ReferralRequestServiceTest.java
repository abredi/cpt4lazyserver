package com.cpt4lazy.cpt4lazyserver.service;

import com.cpt4lazy.cpt4lazyserver.dao.JobReferalPostRepository;
import com.cpt4lazy.cpt4lazyserver.dao.ReferralRequestRepository;
import com.cpt4lazy.cpt4lazyserver.entity.JobReferalPost;
import com.cpt4lazy.cpt4lazyserver.entity.ReferralRequest;
import com.cpt4lazy.cpt4lazyserver.helper.CPT4LazyUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;


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
}