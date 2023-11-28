package com.wu.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wu.userservice.entity.User;


public interface UserRepository extends JpaRepository<User,String> {
    public User findByEmailId(String emailId);

    public User findByEmailIdAndPassword(String emailId,String password);

    public User findByUserId(String id);
}
