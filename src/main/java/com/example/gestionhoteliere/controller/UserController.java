package com.example.gestionhoteliere.controller;

import com.example.gestionhoteliere.entities.User;
import com.example.gestionhoteliere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //Endpoint pour ajouter un user
    @PostMapping("/add")
    public User adduser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/All")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
