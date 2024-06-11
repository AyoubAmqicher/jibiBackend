package com.example.jibibackend.controller;

import com.example.jibibackend.model.Facture;
import com.example.jibibackend.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
@CrossOrigin(origins = "http://localhost:4200")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @GetMapping("/unpaidFactures")
    public ResponseEntity<List<Facture>> getUnpaidFacturesByClientIdAndOperateurId(
            @RequestParam String clientId,
            @RequestParam Long operateurId) {
        List<Facture> factures = factureService.findUnpaidByClientIdAndOperateurId(clientId, operateurId);
        return ResponseEntity.ok(factures);
    }


    @GetMapping("/unpaid/{clientId}")
    public List<Facture> getUnpaidFactures(@PathVariable String clientId) {
        return factureService.getUnpaidFacturesByClientId(clientId);
    }

    @PostMapping("/pay/{factureId}")
    public Facture payFacture(@PathVariable Long factureId) {
        return factureService.payFacture(factureId);
    }
}
