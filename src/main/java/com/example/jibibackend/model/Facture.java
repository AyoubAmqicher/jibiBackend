package com.example.jibibackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    private Boolean paid = false;

    @ManyToOne
    @JoinColumn(name = "operateur_id")
    @JsonBackReference
    private Operateur operateur;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
}
