package com.example.gestionhoteliere.controllers;


import com.example.gestionhoteliere.models.ERole;
import com.example.gestionhoteliere.models.Role;
import com.example.gestionhoteliere.models.User;
import com.example.gestionhoteliere.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> Users = new ArrayList<User>();


        var users = userRepository.findAll();


                for (User user : users) {
                   for(Role role:user.getRoles()){
                       if(role.getName() == ERole.ROLE_USER) Users.add(user);
                   }

                }


        return ResponseEntity.ok(Users);
    }
    @GetMapping("/admins")
    public ResponseEntity<?> getAllAdmins() {
        List<User> admins = new ArrayList<User>();


        var users = userRepository.findAll();


        for (User user : users) {
            for(Role role:user.getRoles()){
                if(role.getName() == ERole.ROLE_ADMIN) admins.add(user);
            }

        }


        return ResponseEntity.ok(admins);
    }
    @GetMapping("/mods")
    public ResponseEntity<?> getAllMods() {
        List<User> mods = new ArrayList<User>();


        var users = userRepository.findAll();


        for (User user : users) {
            for(Role role:user.getRoles()){
                if(role.getName() == ERole.ROLE_MODERATOR) mods.add(user);
            }

        }


        return ResponseEntity.ok(mods);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<User> Users=userRepository.findAll();
        return  ResponseEntity.ok(Users);



    }


    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<?> delete(@PathVariable String username) {
    // Call the custom repository method to delete the user by username
    userRepository.deleteByUsername(username);

    // You don't need to check if the user was deleted or not, as Spring Data handles it.
    return ResponseEntity.ok("User deleted successfully");
}

    @PutMapping("/updateUser/{username}")
    public ResponseEntity<?> update(@PathVariable String username, @RequestBody User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findByUsername(username);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();


            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setUsername(updatedUser.getUsername());



                userRepository.save(existingUser);

                return ResponseEntity.ok(existingUser);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }


        }

            }
