package com.example.jibibackend.repository;

import com.example.jibibackend.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByClientIdAndPaidFalse(String clientId);
    @Query("SELECT f FROM Facture f WHERE f.client.id = :clientId AND f.operateur.id = :operateurId AND f.paid = false")
    List<Facture> findUnpaidFacturesByClientIdAndOperateurId(@Param("clientId") String clientId, @Param("operateurId") Long operateurId);
}
