package com.example.jibibackend.service;

import com.example.jibibackend.model.Facture;
import com.example.jibibackend.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    public List<Facture> getUnpaidFacturesByClientId(String clientId) {
        return factureRepository.findByClientIdAndPaidFalse(clientId);
    }

    public Facture payFacture(Long factureId) {
        Facture facture = factureRepository.findById(factureId).orElseThrow(() -> new RuntimeException("Facture not found"));
        facture.setPaid(true);
        return factureRepository.save(facture);
    }

    public List<Facture> findUnpaidByClientIdAndOperateurId(String clientId, Long operateurId) {
        return factureRepository.findUnpaidFacturesByClientIdAndOperateurId(clientId, operateurId);
    }
}
