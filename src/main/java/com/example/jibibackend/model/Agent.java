package com.example.jibibackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Agent {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false, unique = true)
    private String id;

    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String cinFront;
    private String cinBack;
    private String password;

    @ManyToOne
    @JoinColumn(name = "agence_id")
    @JsonManagedReference
    private Agence agence;
}
