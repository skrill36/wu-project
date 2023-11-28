package com.wu.userservice.service;

import com.wu.userservice.entity.User;
import com.wu.userservice.payload.ApiResponse;

public interface UserRegiService {
     //register user
     ApiResponse saveUser(User user) ;

     //login User
     ApiResponse login(User user) ;

     ApiResponse updateUser(String userId,User user);

     
}
