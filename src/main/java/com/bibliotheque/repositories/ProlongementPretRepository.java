package com.bibliotheque.repositories;

import com.bibliotheque.models.ProlongementPret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProlongementPretRepository extends JpaRepository<ProlongementPret, Integer> {
}
