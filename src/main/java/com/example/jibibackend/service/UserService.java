package com.example.jibibackend.service;


import com.example.jibibackend.model.Agent;
import com.example.jibibackend.model.Role;
import com.example.jibibackend.model.User;
import com.example.jibibackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    public User findUserByName(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Agence not found with id: " + username));
    }

    public void createUserAgent(Agent agent){
        User agentUser = new User();
        agentUser.setUsername(agent.getId());
        agentUser.setPassword(agent.getPassword());
        agentUser.setFirstName(agent.getFirstName());
        agentUser.setLastName(agent.getLastName());
        agentUser.setRoles(Collections.singleton(roleService.findRoleByname("ROLE_AGENT")));
        userRepository.save(agentUser);
    }
}
