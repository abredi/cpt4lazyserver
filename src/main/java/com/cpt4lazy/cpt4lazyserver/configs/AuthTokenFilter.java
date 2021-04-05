package com.cpt4lazy.cpt4lazyserver.configs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cpt4lazy.cpt4lazyserver.service.CustomUserDetailService;

import org.springframework.util.StringUtils;

public class AuthTokenFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private CustomUserDetailService userService;
	
	/**
	 * This will perform filtering of the request. Validate the access token and perform authentication
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = parseJwt(request);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String email = jwtUtils.getUserEmailFromJwtToken(jwt);

				UserDetails userDetails = userService.loadUserByUsername(email);				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null,
			            userDetails.getAuthorities());
			        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
						
			}
		} 
		catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
		
	}
	
	/**
	 * This will perform parsing of the value of the Authorization header
	 * @param request
	 * @return
	 * @author Carl Mapada
	 */ 
	private String parseJwt(HttpServletRequest request) {
	    String headerAuth = request.getHeader("Authorization");

	    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
	      return headerAuth.substring(7, headerAuth.length());
	    }

	    return null;
	}
}
