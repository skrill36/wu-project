package com.wu.userservice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_details")
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",message = "Please provide a valid email address")
    @NotEmpty(message = "Email is required")
    @Column(unique = true)
    private String emailId;
    @Column(unique = true)
    @Pattern(regexp = "\\d{10}", message = "Please provide a valid 10-digit mobile number")
    private String mobileNumber;
    private Date dob;
    private String address1;
    private String address2;
    private String zip;
    private String city;
    private String state;
    private String password;
     
}
