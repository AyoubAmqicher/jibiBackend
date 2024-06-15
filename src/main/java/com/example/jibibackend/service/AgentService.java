package com.example.jibibackend.service;

import com.example.jibibackend.model.Agent;
import com.example.jibibackend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private EmailService emailService;

    public Agent createAgent(Agent agent) {
        agent.setPassword(generateTemporaryPassword());
        Agent savedAgent = agentRepository.save(agent);
        sendWelcomeEmail(savedAgent);
        return savedAgent;
    }

    private void sendWelcomeEmail(Agent agent) {
        String subject = "Welcome to Jibi";
        String text = String.format("Hello %s,\n\nYour account has been created.\nYour ID: %s\nYour temporary password: %s\n\nPlease change your password after logging in.",
                agent.getFirstName(), agent.getId(), agent.getPassword());
        emailService.sendEmail(agent.getEmail(), subject, text);
    }

    private String generateTemporaryPassword() {
        // Generate a temporary password (e.g., random UUID)
        return UUID.randomUUID().toString();
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public void deleteAgentById(String id) {
        agentRepository.deleteById(id);
    }

    public Agent updateAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public Agent getAgentById(String id) {
        return agentRepository.findById(id).orElseThrow(() -> new RuntimeException("Agent not found"));
    }

}
