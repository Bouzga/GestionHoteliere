package com.example.gestionhoteliere.models;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 20)
    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NotBlank
    @Size(max = 20)
    private String lastName;
    @NotBlank
    @Size(max = 20)
    private int age;
    @NotBlank
    @Size(max = 20)
    private String phone;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;


    @DBRef
    private Set<Role> roles = new HashSet<>();
    @JsonIgnore
    @DBRef
    private List<Booking> bookings;
    public User() {
    }

    public User(String username, String email, String password,String firstName,String lastName,int age,String phone ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.phone=phone;
    }
    public User(String username, String email, String password,Set<Role> roles,String firstName,String lastName,int age,String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles=roles;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.phone=phone;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}