package com.cpt4lazy.cpt4lazyserver.helper;

import com.cpt4lazy.cpt4lazyserver.entity.JobSeeker;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CPT4LazyUtilityTest {

    @Autowired
    private CPT4LazyUtility utility;

    @Test
    void isALUMNI() {
        JobSeeker jobSeeker = new JobSeeker("john",
                "+12341543",
                "texas",
                "JobSeeker",
                "Java Developer", "Microsoft");
        User user = new User("jdeo@email.com", "password1", jobSeeker);
        assertFalse(utility.isALUMNI(user));
    }
}