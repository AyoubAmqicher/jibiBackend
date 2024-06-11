package com.example.jibibackend.Controller;

import com.example.jibibackend.model.Client;
import com.example.jibibackend.model.Facture;
import com.example.jibibackend.service.ClientService;
import com.example.jibibackend.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
@CrossOrigin("*")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @Autowired
    private ClientService clientService;


    @GetMapping("/unpaidFactures")
    public ResponseEntity<List<Facture>> getUnpaidFacturesByClientIdAndOperateurId(
            @RequestParam String clientId,
            @RequestParam Long operateurId) {
        List<Facture> factures = factureService.findUnpaidByClientIdAndOperateurId(clientId, operateurId);
        return ResponseEntity.ok(factures);
    }

    @PostMapping("/effectuerPaiement")
    public ResponseEntity<?> effectuerPaiement(@RequestParam String clientId, @RequestParam Long factureId) {
        Client client = clientService.getClientById(clientId);
        Facture facture = factureService.getFactureById(factureId);

        if (client.getBalance() >= facture.getAmount()) {
            client.setBalance(client.getBalance() - facture.getAmount());
            clientService.updateClient(client);

            facture.setPaid(true);
            factureService.updateFacture(facture);

            return ResponseEntity.ok("Payment successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating client: ");
        }
    }

    @GetMapping("/unpaid/{clientId}")
    public List<Facture> getUnpaidFactures(@PathVariable String clientId) {
        return factureService.getUnpaidFacturesByClientId(clientId);
    }

    @PostMapping("/pay/{factureId}")
    public Facture payFacture(@PathVariable Long factureId) {
        return factureService.payFacture(factureId);
    }

    @GetMapping("/{factureId}")
    public ResponseEntity<Facture> getFactureById(@PathVariable Long factureId) {
        Facture facture = factureService.getFactureById(factureId);
        return ResponseEntity.ok(facture);
    }
}
