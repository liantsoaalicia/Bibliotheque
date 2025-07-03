package com.bibliotheque.repositories;

import com.bibliotheque.models.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {
}
