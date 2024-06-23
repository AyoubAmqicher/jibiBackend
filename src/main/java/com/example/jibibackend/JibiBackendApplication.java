package com.example.jibibackend;

import com.example.jibibackend.model.User;
import com.example.jibibackend.repository.RoleRepository;
import com.example.jibibackend.repository.UserRepository;
import com.example.jibibackend.service.RoleService;
import com.example.jibibackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class JibiBackendApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(JibiBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.findByUsername("admin").isEmpty()) {
            // Create a new admin user
            User admin = userService.findUserByName("admin");
            admin.setFirstName("Ayoub");
            admin.setLastName("Admin");
            userRepository.save(admin);
            System.out.println("Admin user created with username 'admin' and password 'admin'");
        } else {
            System.out.println("Admin user already exists");
        }
    }
}
