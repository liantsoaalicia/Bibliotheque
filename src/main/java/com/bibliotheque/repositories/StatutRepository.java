package com.bibliotheque.repositories;

import com.bibliotheque.models.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutRepository extends JpaRepository<Statut, Integer> {

    Statut findByStatut(String statut);
}
