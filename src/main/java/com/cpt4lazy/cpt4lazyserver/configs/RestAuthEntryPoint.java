package com.cpt4lazy.cpt4lazyserver.configs;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Rest entry point. Check if user has valid authorization
 * @author cmmap
 *
 */
@Component
public class RestAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		// send error response to the client (401 unauthorized)
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

	
}