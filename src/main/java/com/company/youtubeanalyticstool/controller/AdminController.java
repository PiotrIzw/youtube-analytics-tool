package com.company.youtubeanalyticstool.controller;

import com.company.youtubeanalyticstool.model.UserDAO;
import com.company.youtubeanalyticstool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public List<UserDAO> getAllUsers(){
        return userRepository.findAll();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long userId){
        userRepository.deleteById(userId);
    }
}
