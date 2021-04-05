package com.cpt4lazy.cpt4lazyserver.service;

import com.cpt4lazy.cpt4lazyserver.dao.JobReferalPostRepository;
import com.cpt4lazy.cpt4lazyserver.dao.PostRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobReferalPostServiceTest {

    @MockBean
    private PostRepository postRepo;

    @Autowired
    private JobReferalPostService jobReferalPostService;

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
    void deletePost_shouldReturnTrue() {
        given(postRepo.findById(anyInt())).willReturn(null);
        assertTrue(jobReferalPostService.deletePost(anyInt()));
    }

    @Test
    void updatePostReferralRequest_shouldReturnTrueForAvailableUser() throws JsonProcessingException {
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer", "Microsoft");

        User user = new User("jdeo@email.com", "password1", jobSeeker);
        user.setId(1);
        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(user.getEmail());
        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(user);
        given(cpt4LazyUtility.isALUMNI(user)).willReturn(true);


        ReferralRequest referralRequest= new ReferralRequest("jdeo@email.com", LocalDate.of(2020, 3, 4),
                "active" );
        JobReferalPost referralPost = new JobReferalPost(user.getEmail(), "random text", LocalDate.of(2020, 3, 4),
                "active" );
        given(jobReferalPostRepository.findById((int)user.getId())).willReturn(Optional.of(referralPost));


        String jsonReferralRequest = mapper.writeValueAsString(referralRequest);

        given(referralRequestRepository.save(referralRequest)).willReturn(referralRequest);

        assertTrue(jobReferalPostService.updatePostReferralRequest(jsonReferralRequest,
                Integer.parseInt(String.valueOf(user.getId())), anyString()));
    }

    @Test
    void updatePostReferralRequest_shouldReturnFalseForUnavailableUser() throws JsonProcessingException {
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer", "Microsoft");

        User user = new User("jdeo@email.com", "password1", jobSeeker);
        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(user.getEmail());
        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(null);
        ReferralRequest referralRequest= new ReferralRequest("jdeo@email.com", LocalDate.of(2020, 3, 4),
                "active" );
        String jsonReferralRequest = mapper.writeValueAsString(referralRequest);
        assertFalse(jobReferalPostService.updatePostReferralRequest(jsonReferralRequest,
                Integer.parseInt(String.valueOf(user.getId())), anyString()));
    }

    @Test
    void updatePostReferralRequest_shouldReturnFalseForUserIsNotAlumni() throws JsonProcessingException {
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "Alumni",
                "Java Developer", "Microsoft");
        JobReferalPost post = new JobReferalPost("poster","Java Developer" ,
                LocalDate.of(2020, 9, 5),
                "java developer");
        ReferralRequest referralRequest= new ReferralRequest("jdeo@email.com", LocalDate.of(2020, 3, 4),
                "active" );
        User user = new User("jdeo@email.com", "password1", jobSeeker);
        given(cpt4LazyUtility.getEmailFromToken(anyString())).willReturn(user.getEmail());
        given(customUserDetailService.findUserByEmail(user.getEmail())).willReturn(user);
        given(jobReferalPostRepository.save(post)).willReturn(post);

        String jsonRequest = mapper.writeValueAsString(referralRequest);
        assertFalse(jobReferalPostService.updatePostReferralRequest(jsonRequest,
                Integer.parseInt(String.valueOf(user.getId())), anyString()));
    }
}