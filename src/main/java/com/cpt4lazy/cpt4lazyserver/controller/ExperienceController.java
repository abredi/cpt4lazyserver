package com.cpt4lazy.cpt4lazyserver.controller;

import com.cpt4lazy.cpt4lazyserver.entity.User;
import com.cpt4lazy.cpt4lazyserver.service.CustomUserDetailService;
import com.cpt4lazy.cpt4lazyserver.service.ExperienceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private CustomUserDetailService userService;

    @Autowired
    private ExperienceService expService;

    @PostMapping("/edit")
    public ResponseEntity<String> createExpereince(@RequestBody String json) throws JsonMappingException, JsonProcessingException {

        boolean success = false;
        JSONObject jsonObj = new JSONObject(json);
        User user = userService.findUserByEmail(jsonObj.getString("email"));

        if (user != null) {
            success = expService.createExperience(user, jsonObj.getJSONArray("experience").toString());
        }

        return success ? ResponseEntity.ok("Successfully added experience/s") : ResponseEntity.badRequest().body("Error adding your experience/s");

    }

    @PutMapping("/edit")
    public ResponseEntity<String> updateExpereince(@RequestBody String json) throws JsonMappingException, JsonProcessingException {

        boolean success = expService.updateExperience(json);
        return success ? ResponseEntity.ok("Successfully added experience/s") : ResponseEntity.badRequest().body("Error adding your experience/s");

    }

    @DeleteMapping("/edit")
    public ResponseEntity<String> deleteExpereince(@RequestBody String json) throws JsonMappingException, JsonProcessingException {

        boolean success = expService.deleteExperience(json);
        return success ? ResponseEntity.ok("Successfully added experience/s") : ResponseEntity.badRequest().body("Error adding your experience/s");

    }
}
