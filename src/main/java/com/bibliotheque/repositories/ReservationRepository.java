package com.bibliotheque.repositories;

import com.bibliotheque.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    
    @Query("SELECT r FROM Reservation r WHERE r.statut.statut = :statut")
    List<Reservation> findByStatut(@Param("statut") String statut);
}
