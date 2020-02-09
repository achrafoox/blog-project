package com.blog.blog.services;

import com.blog.blog.models.Role;
import com.blog.blog.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {


    @Autowired
    RoleRepository roleRepository;

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
