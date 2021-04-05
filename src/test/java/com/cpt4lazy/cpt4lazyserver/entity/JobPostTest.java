package com.cpt4lazy.cpt4lazyserver.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class JobPostTest {

    @Test
    void setResults() {
        JobPost jobPost = mock(JobPost.class);

        assertThat(jobPost.getResults()).isInstanceOf(List.class);
    }
}