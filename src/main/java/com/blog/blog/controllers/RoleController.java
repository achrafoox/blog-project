package com.blog.blog.controllers;

import com.blog.blog.exceptions.CategorynotFoundException;
import com.blog.blog.models.Role;
import com.blog.blog.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;


    // Add a role :
    @PostMapping(value = "/roles/")
    public ResponseEntity<Object> addRole(@RequestBody Role role){
        Role savedRole = roleService.save(role);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedRole.getName()).toUri();

        return ResponseEntity.created(location).build();
    }


    // update a role :
    @PutMapping("/roles/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Integer id, @RequestBody Role role){
        Optional<Role> newrole = roleService.findById(id);
        if (!newrole.isPresent())
            return ResponseEntity.notFound().build();
        role.setId(id);
        roleService.save(role);
        return ResponseEntity.noContent().build();
    }


    // delete a role :
    @DeleteMapping(value = "/roles/{id}")
    public void deleteRole(@PathVariable Integer id){
        roleService.deleteById(id);
    }


    // get all roles :
    @GetMapping(value = "/roles/")
    public List<Role> getRoles(){
        return roleService.findAll();
    }


    // get roles by id :
    @GetMapping(value="/roles/{id}")
    public Optional<Role> getRoleById(@PathVariable Integer id) throws CategorynotFoundException {
        Optional<Role> role = roleService.findById(id) ;
        if(role==null) throw new CategorynotFoundException("le role  avec l'id = " + id + " est INTROUVABLE !");
        return role;
    }


}
