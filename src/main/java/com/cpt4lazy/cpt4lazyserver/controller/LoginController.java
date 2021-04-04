package com.cpt4lazy.cpt4lazyserver.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpt4lazy.cpt4lazyserver.configs.JwtUtils;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.service.CustomUserDetailService;

@RestController
public class LoginController {

    @Autowired
    private CustomUserDetailService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<?> authenticateUser(@RequestBody String json) {
        JSONObject jsonObj = new JSONObject(json);
        User user = userService.findUserByEmail(jsonObj.getString("email"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jsonObj.get("email"), jsonObj.get("password")));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("jwt: " + jwt);

        return ResponseEntity.ok(new JWTResponse(user, jwt));

    }


}
