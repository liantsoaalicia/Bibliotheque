package com.bibliotheque.repositories;

import com.bibliotheque.models.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;

public interface AdherantRepository extends JpaRepository<Adherant, Integer> {
    Adherant findByEmailAndMdp(String email, String mdp);

    @Query("SELECT q.nbExemplaire FROM QuotaExemplaire q WHERE q.profil.id = (SELECT a.profil.id FROM Adherant a WHERE a.id = :adherantId)")
    Integer findNbExemplaireByAdherantId(@Param("adherantId") Integer adherantId);

    // pret en cours (a domicile ihany)
    @Query("SELECT COUNT(p) FROM Pret p WHERE p.adherant.id = :adherantId AND p.dateRetour IS NULL AND p.typePret.id = 1")
    Integer countPretsEnCoursByAdherantId(@Param("adherantId") Integer adherantId);

    @Query("SELECT COUNT(a) > 0 FROM AdherantAbonnement a WHERE a.adherant.id = :adherantId AND :dateToCheck BETWEEN a.dateDebut AND a.dateFin")
    boolean isAbonneByAdherantIdAndDate(@Param("adherantId") Integer adherantId, @Param("dateToCheck") Date dateToCheck);
    
    @Query("SELECT COUNT(p) > 0 FROM AdherantPenalite p WHERE p.adherant.id = :adherantId AND :dateToCheck BETWEEN p.dateDebut AND p.dateFin")
    boolean hasPenaliteAtDate(@Param("adherantId") Integer adherantId, @Param("dateToCheck") Date dateToCheck);
}
