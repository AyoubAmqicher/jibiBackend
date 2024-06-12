package com.example.jibibackend.Controller;

import com.example.jibibackend.model.Agent;
import com.example.jibibackend.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/agents")
public class AgentController {
    @Autowired
    private AgentService agentService;
    @PostMapping("/create")
    public Agent createAgent(
            @RequestParam("lastName") String lastName,
            @RequestParam("firstName") String firstName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("cinFront") MultipartFile cinFront,
            @RequestParam("cinBack") MultipartFile cinBack,
            @RequestParam("agence") String agence) throws IOException {
        Agent agent = new Agent();
        agent.setLastName(lastName);
        agent.setFirstName(firstName);
        agent.setEmail(email);
        agent.setPhone(phone);
//        agent.setAgence(agence); // Set the agence field
        //Save files
        String cinFrontPath = saveFile(cinFront);
        String cinBackPath = saveFile(cinBack);
        agent.setCinFront(cinFrontPath);
        agent.setCinBack(cinBackPath);
        return agentService.createAgent(agent);
    }

    @GetMapping("/all")
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    private String saveFile(MultipartFile file) throws IOException {
        String folder = "uploads/";
        Path uploadPath = Paths.get(folder);

        // Create the directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file
        String filePath = folder + UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());
        return filePath;
    }
}
