package com.bibliotheque.repositories;

import com.bibliotheque.models.ProlongementPret;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.bibliotheque.models.Pret;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProlongementPretRepository extends JpaRepository<ProlongementPret, Integer> {
    @Query("SELECT p FROM ProlongementPret p WHERE p.pret = :pret ORDER BY p.dateFin DESC")
    Optional<ProlongementPret> findLastByPret(@Param("pret") Pret pret);

    @Query("SELECT COUNT(p) FROM ProlongementPret p WHERE p.pret.adherant.id = :adherantId AND p.statut.statut = :statut")
    int countByPretAdherantIdAndStatutStatut(@Param("adherantId") Integer adherantId, @Param("statut") String statut);
}
