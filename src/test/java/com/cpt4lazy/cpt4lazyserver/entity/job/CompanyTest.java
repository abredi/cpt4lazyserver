package com.cpt4lazy.cpt4lazyserver.entity.job;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CompanyTest {

    @Test
    void setDisplay_name() {
        Company company = mock(Company.class);

        assertThat(company.getDisplay_name()).isNull();
    }
}