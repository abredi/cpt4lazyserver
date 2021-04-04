package com.cpt4lazy.springboot.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.cpt4lazy.springboot.configs.JwtUtils;
import com.cpt4lazy.springboot.entity.User;

@Component
public class CPT4LazyUtility {

	@Autowired
	private JwtUtils jwtUtils;

	public String getEmailFromToken(String token) {
		String headerAuth = token;
		String jwt = "";
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			jwt = headerAuth.substring(7, headerAuth.length());
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				return jwtUtils.getUserEmailFromJwtToken(jwt);
			}
		}

		return null;
	}

	public boolean isALUMNI(User user) {
		return user.getRole().getRoleName().equals("ALUMNI") ? true : false;
	}

}
