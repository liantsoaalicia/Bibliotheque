package com.bibliotheque.repositories;

import com.bibliotheque.models.Pret;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PretRepository extends JpaRepository<Pret, Integer> {
    @Query("SELECT p FROM Pret p WHERE p.dateRetour IS NULL")
    List<Pret> findPretsEnCours();

    @Query("SELECT p FROM Pret p WHERE p.dateRetour IS NULL AND p.adherant = :adherant")
    List<Pret> findPretsEnCoursByAdherant(@Param("adherant") com.bibliotheque.models.Adherant adherant);

    List<Pret> findByExemplaireId(Integer exemplaireId);
}
