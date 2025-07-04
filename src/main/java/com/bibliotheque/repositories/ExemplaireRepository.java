package com.bibliotheque.repositories;

import com.bibliotheque.models.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {

    // true: l'exemplaire a ete reserve juste apres la date ou tu veux emprunter
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.exemplaire.id = :exemplaireId AND :dateToCheck < r.dateDebut")
    boolean isExemplaireReserveAvantDate(@Param("exemplaireId") Integer exemplaireId, @Param("dateToCheck") Date dateToCheck);

    // Vérifie si la dernière réservation de l'exemplaire a été refusée (statut=3) donc elle return true
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservation r WHERE r.exemplaire.id = :exemplaireId AND r.id = (SELECT MAX(r2.id) FROM Reservation r2 WHERE r2.exemplaire.id = :exemplaireId) AND r.statut.id = 3")
    boolean isLastReservationRefused(@Param("exemplaireId") Integer exemplaireId);
    
    @Query("SELECT e FROM Exemplaire e WHERE e.id NOT IN (SELECT r.exemplaire.id FROM Reservation r WHERE r.statut.id = 1) AND e.id NOT IN (SELECT p.exemplaire.id FROM Pret p WHERE p.dateRetour IS NULL)")
    List<Exemplaire> findExemplairesDisponibles();
}
