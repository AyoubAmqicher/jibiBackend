package com.example.jibibackend.service;

import com.example.jibibackend.model.Agence;
import com.example.jibibackend.repository.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenceService {

    private final AgenceRepository agenceRepository;

    @Autowired
    public AgenceService(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    public Agence getAgenceById(String id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence not found with id: " + id));
    }
}
