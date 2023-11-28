package com.wu.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.userservice.entity.User;
import com.wu.userservice.payload.ApiResponse;
import com.wu.userservice.service.UserRegiService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {
    
    @Autowired
    private UserRegiService userRegiService;

    //register user
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse>registerUser(@Valid @RequestBody User user) {
        ApiResponse apiResponse=userRegiService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    
    //login user
    @PostMapping("/signin")
    public ResponseEntity<ApiResponse>loginUser(@RequestBody User user) {
        ApiResponse apiResponse=userRegiService.login(user);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        
    }

   @PatchMapping("/update/{userId}")
   public ResponseEntity<ApiResponse>updateUser(@PathVariable String userId, @RequestBody User user){
    ApiResponse apiResponse=userRegiService.updateUser(userId, user);
    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
   }

}
