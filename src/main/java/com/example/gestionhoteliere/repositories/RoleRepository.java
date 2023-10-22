package com.example.gestionhoteliere.repositories;

import java.util.Optional;

import com.example.gestionhoteliere.models.ERole;
import com.example.gestionhoteliere.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}