package com.blog.blog.controllers;

import com.blog.blog.exceptions.UsernotFoundException;
import com.blog.blog.models.User;
import com.blog.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {


    @Autowired
    private UserRepository userRepo;


    // Add a user :
    @PostMapping(value = "/users/")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        User savedEmployee = userRepo.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    // update a user :
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody User user){
        Optional<User> newuser = userRepo.findById(id);
        if (!newuser.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        userRepo.save(user);
        return ResponseEntity.noContent().build();
    }

    // delete a user :
    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userRepo.deleteById(id);
    }


    // get all users :
    @GetMapping(value = "/users/")
    public List<User> getUsers(){
        return userRepo.findAll();
    }


    // get users by id :
    @GetMapping(value="/users/{id}")
    public Optional<User> getUsersById(@PathVariable Integer id) throws UsernotFoundException {
        Optional<User> user = userRepo.findById(id) ;
        if(user==null) throw new UsernotFoundException("L'utilisateur  avec l'id = " + id + " est INTROUVABLE !");
        return user;
    }




}
