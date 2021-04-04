package com.cpt4lazy.cpt4lazyserver.controller;

import com.cpt4lazy.cpt4lazyserver.configs.JwtUtils;
import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.service.CustomUserDetailService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private CustomUserDetailService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody String json) {
        JSONObject jsonObj = new JSONObject(json);
        User user = userService.findUserByEmail(jsonObj.getString("email"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jsonObj.get("email"), jsonObj.get("password")));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        user.setPassword(null);
        return ResponseEntity.ok(new JWTResponse(user, jwt));
    }


}
