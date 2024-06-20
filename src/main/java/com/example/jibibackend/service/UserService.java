package com.example.jibibackend.service;


import com.example.jibibackend.model.Role;
import com.example.jibibackend.model.User;
import com.example.jibibackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserByName(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Agence not found with id: " + username));
    }
}
