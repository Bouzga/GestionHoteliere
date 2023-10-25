package com.example.gestionhoteliere.controllers;


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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/AllClients/{num}")
    public ResponseEntity<?> getAll(@PathVariable int num) {
        List<User> Clients = new ArrayList<User>();


        var users = userRepository.findAll();

        switch (num) {
            case 1:
                for (User user : users) {
                    Set<Role> userRoles = user.getRoles();
                    for (Role role : userRoles) {
                        if (role.getName().equals("ROLE_MODERATOR")) {
                            Clients = (List<User>) new User();
                            Clients.add(user);

                        }
                    }
                    break; // Add this break to exit the switch statement after case 1
                }

            case 2:
                for (User user : users) {
                    Set<Role> userRoles = user.getRoles();
                    for (Role role : userRoles) {
                        role.getName();
                        if (role.getName().equals("ROLE_ADMIN")) {
                            Clients = (List<User>) new User();
                            Clients.add(user);

                        }
                    }
                    break; // Add this break to exit the switch statement after case 2
                }

            case 3:
                for (User user : users) {
                    Set<Role> userRoles = user.getRoles();
                    for (Role role : userRoles) {
                        if (role.getName().equals("ROLE_USER")) {
                            Clients = (List<User>) new User();
                            Clients.add(user);

                        }
                    }
                    break; // Add this break to exit the switch statement after case 3
                }
        }
        return ResponseEntity.ok(Clients);
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

            // Update the existing user with new data
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setUsername(updatedUser.getUsername());


            // Save the updated user
            userRepository.save(existingUser);

            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}