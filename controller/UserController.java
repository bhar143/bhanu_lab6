package com.grl.springMVC.College.controller;

import com.grl.springMVC.College.entity.User;
import com.grl.springMVC.College.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> saveUser(User user){
        User user1= userRepository.save(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
}
