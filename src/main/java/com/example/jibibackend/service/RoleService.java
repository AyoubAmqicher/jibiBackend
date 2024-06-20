package com.example.jibibackend.service;

import com.example.jibibackend.model.Role;
import com.example.jibibackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findRoleByname(String role){
        return roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Agence not found with id: " + role));
    }
}
