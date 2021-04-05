package com.cpt4lazy.cpt4lazyserver.service;

import com.cpt4lazy.cpt4lazyserver.entity.JobSeeker;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.helper.CPT4LazyUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private CPT4LazyUtility utility;

    @Autowired
    private CustomUserDetailService userService;

    public String getPreference(String token) {
        String userPreference = " ";
        String email = utility.getEmailFromToken(token);
        User user = userService.findUserByEmail(email);

        if (utility.isALUMNI(user))
            return null;

        JobSeeker jobSeeker = (JobSeeker) user.getRole();
        if (!jobSeeker.getPreferredJob().isEmpty()) {
            userPreference += jobSeeker.getPreferredJob() + " ";
        }
        if (!jobSeeker.getPreferredCompany().isEmpty()) {
            userPreference += jobSeeker.getPreferredCompany();
        }
        return userPreference;
    }
}
