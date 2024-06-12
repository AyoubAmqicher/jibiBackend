package com.example.jibibackend.Controller;

import com.example.jibibackend.model.Client;
import com.example.jibibackend.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/createClient")
    public ResponseEntity<?> createClient(@RequestParam("phone") String phone,
                                          @RequestParam("firstName") String firstName,
                                          @RequestParam("lastName") String lastName,
                                          @RequestParam("cinFront") MultipartFile cinFront,
                                          @RequestParam("cinBack") MultipartFile cinBack,
                                          @RequestParam("balance") double balance) {
        try {
            Client client = clientService.createClient(phone,lastName,firstName, cinFront, cinBack,balance);
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating client: " + e.getMessage());
        }
    }
}