package com.wu.userservice.service.impl;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wu.userservice.entity.User;
import com.wu.userservice.exception.AlreadyExistsException;
import com.wu.userservice.exception.InvalidCredentialsException;
import com.wu.userservice.exception.ResourceNotFoundException;
import com.wu.userservice.payload.ApiResponse;
import com.wu.userservice.repository.UserRepository;
import com.wu.userservice.service.UserRegiService;

@Service
public class UserServiceImpl implements UserRegiService {
    @Autowired
    private UserRepository userRepository;

   
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
      
    @Autowired
    private PasswordEncoder passwordEncoder;

    //save - register user
    @Override
    public ApiResponse saveUser(User user){
        logger.info("Received registration request for emailId: {}", user.getEmailId());
        
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        String pass=user.getPassword();
        String encode=this.passwordEncoder.encode(pass);
        user.setPassword(encode);
        String tempEmailId=user.getEmailId();
        if(tempEmailId!=null && !"".equals(tempEmailId)){
           User userObj= userRepository.findByEmailId(tempEmailId);
           if(userObj !=null){
            logger.error("Error during user registration");
            throw new AlreadyExistsException("user with "+tempEmailId+" is already exist");
           }
        }
        logger.info("User registered successfully: {}", user.getEmailId());
       
        userRepository.save(user);
        return new ApiResponse("User registered successfully",true);
    }

    //login user
    @Override
    public ApiResponse login(User user) { 
      User userObj=userRepository.findByEmailId(user.getEmailId());
      logger.info("Received login request for emailId: {}",userObj);

      User userOut=null;
      if(userObj!=null){
        String pass=user.getPassword();
        String encodedPass=userObj.getPassword();
        Boolean match=passwordEncoder.matches(pass, encodedPass);
        if (Boolean.TRUE.equals(match)) {
        userRepository.findByEmailIdAndPassword(user.getEmailId(), encodedPass);
        }
        else{
          logger.error("Authentication failed for user: {}");
          throw new InvalidCredentialsException("Invalid emailId or Password");
        }
      }
      else{
        logger.error("Authentication failed for user: {}");
        throw new InvalidCredentialsException("Invalid emailId or password");
      }
      logger.info("User logged in successfully: {}", userOut);
     return new ApiResponse("User logged in successfully",true);
    }

    //update user
    @Override
    public ApiResponse updateUser(String userId,User user) {
      logger.info("Received Updation Request for user :{}",userId);
      User existingUser=userRepository.findByUserId(userId);
      if(existingUser==null){
        logger.error("User not found with given user id :{}",userId);
        throw new ResourceNotFoundException("User not found");
      }
      existingUser.setMobileNumber(user.getMobileNumber());
      existingUser.setDob(user.getDob());
      existingUser.setAddress1(user.getAddress1());
      existingUser.setAddress2(user.getAddress2());
      existingUser.setCity(user.getCity());
      existingUser.setState(user.getState());
      existingUser.setZip(user.getZip());

      userRepository.save(existingUser);
      logger.info("Updated Successfully:{}");

      return new ApiResponse("User detailed Updated Successfully!",true);
      
  }

    
   
  
  
   
}
