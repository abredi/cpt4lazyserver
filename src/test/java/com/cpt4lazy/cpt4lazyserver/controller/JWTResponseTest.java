package com.cpt4lazy.cpt4lazyserver.controller;

import com.cpt4lazy.cpt4lazyserver.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@EnableAutoConfiguration
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JWTResponseTest {
    private final static String BEARER = "Bearer";

    @Test
    void getUser() {
        User user = mock(User.class);
        JWTResponse jwtResponse = new JWTResponse(user, "");

        assertEquals(jwtResponse.getTokenType(), BEARER);
        assertThat(jwtResponse.getUser()).isInstanceOf(User.class);
    }
}