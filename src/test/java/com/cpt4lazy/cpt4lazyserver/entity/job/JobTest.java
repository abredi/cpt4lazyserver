package com.cpt4lazy.cpt4lazyserver.entity.job;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class JobTest {
    private final Job job = new Job();

    @Test
    void getLocation() {
        job.setLocation(mock(Location.class));

        assertThat(job.getLocation()).isInstanceOf(Location.class);
    }

    @Test
    void getPosition() {

        assertThat(job.getJobPosition()).isNull();
    }

    @Test
    void getCompany() {
        job.setJobPlace(mock(Company.class));

        assertThat(job.getJobPlace()).isInstanceOf(Company.class);
    }
}