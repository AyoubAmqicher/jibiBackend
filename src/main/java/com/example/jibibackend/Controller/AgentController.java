package com.example.jibibackend.Controller;

import com.example.jibibackend.model.Agent;
import com.example.jibibackend.service.AgentService;
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
@RequestMapping("/api/agents")
@CrossOrigin(origins = "http://localhost:4200")
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
            @RequestParam("cinBack") MultipartFile cinBack) throws IOException {
        Agent agent = new Agent();
        agent.setLastName(lastName);
        agent.setFirstName(firstName);
        agent.setEmail(email);
        agent.setPhone(phone);
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
