package com.bibliotheque.repositories;

import com.bibliotheque.models.Pret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PretRepository extends JpaRepository<Pret, Integer> {
}
