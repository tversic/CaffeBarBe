package com.caffeBar.caffe.controllers;

import com.caffeBar.caffe.model.CaffeBarAdmin;
import com.caffeBar.caffe.repo.UserRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Transactional
    @GetMapping("/hello")
    public String helloApi() {
        CaffeBarAdmin admin = new CaffeBarAdmin();
        userRepository.findByUsername("admin");
        return "hello";
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestBody) {
        Optional<CaffeBarAdmin> admin = userRepository.findByUsername(requestBody.get("username"));
        JSONObject response = new JSONObject();
        if (admin.isEmpty()) {
            return new ResponseEntity<>(response.put("response", "User not found").toString(), HttpStatus.NOT_FOUND);
        }

        if (BCrypt.checkpw(requestBody.get("password"), admin.get().getPassword())) {
            response.put("username", requestBody.get("username"));
            return ResponseEntity.ok().body(response.toString());
        } else {
            return new ResponseEntity<>(response.put("response", "User not found").toString(), HttpStatus.NOT_FOUND);
        }
    }
}
