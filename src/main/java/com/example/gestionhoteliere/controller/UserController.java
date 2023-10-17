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

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            // Gérer le cas où l'utilisateur n'existe pas
            return null;
        }

        // Mettre à jour les champs de l'utilisateur existant avec les données de "updatedUser"
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setNumPhone(updatedUser.getNumPhone());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            userRepository.delete(existingUser);
        }
    }


}
