package com.example.jibibackend.repository;

import com.example.jibibackend.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, String> {
}
