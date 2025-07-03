package com.bibliotheque.repositories;

import com.bibliotheque.models.GenreLitteraire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreLitteraireRepository extends JpaRepository<GenreLitteraire, Integer> {
}
