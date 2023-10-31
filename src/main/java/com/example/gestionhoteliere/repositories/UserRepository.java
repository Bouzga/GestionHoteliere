package com.example.gestionhoteliere.repositories;


import java.util.List;
import java.util.Optional;


import com.example.gestionhoteliere.models.Booking;
import com.example.gestionhoteliere.models.Role;
import com.example.gestionhoteliere.models.User;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    void deleteByUsername(String username);
    Role findByRoles(String roleName);


}