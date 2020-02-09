package com.blog.blog.controllers;

import com.blog.blog.exceptions.UsernotFoundException;
import com.blog.blog.models.User;
import com.blog.blog.repositories.UserRepository;
import com.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/")
@CrossOrigin(value="*")
@RestController
public class UserController {


    @Autowired
    private UserService userService;


    // Add a user :
    @PostMapping(value = "/users/")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        User savedEmployee = userService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    // update a user :
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody User user){
        Optional<User> newuser = userService.findById(id);
        if (!newuser.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        userService.save(user);
        return ResponseEntity.noContent().build();
    }

    // delete a user :
    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
    }


    // get all users :
    @GetMapping(value = "/users/")
    public List<User> getUsers(){
        return userService.findAll();
    }


    // get users by id :
    @GetMapping(value="/users/{id}")
    public Optional<User> getUsersById(@PathVariable Integer id) throws UsernotFoundException {
        Optional<User> user = userService.findById(id) ;
        if(user==null) throw new UsernotFoundException("L'utilisateur  avec l'id = " + id + " est INTROUVABLE !");
        return user;
    }


}
