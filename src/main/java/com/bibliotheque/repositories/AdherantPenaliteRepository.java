package com.bibliotheque.repositories;

import com.bibliotheque.models.AdherantPenalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface AdherantPenaliteRepository extends JpaRepository<AdherantPenalite, Integer> {

    @Query("SELECT ap FROM AdherantPenalite ap " +
           "WHERE ap.adherant.id = :adherantId " +
           "AND :dateToCheck >= ap.dateDebut " +
           "AND :dateToCheck <= ap.dateFin")
    List<AdherantPenalite> findPenalitesAtDate(@Param("adherantId") Integer adherantId, @Param("dateToCheck") Date dateToCheck);
    
}
