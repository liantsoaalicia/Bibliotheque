package com.bibliotheque.repositories;

import com.bibliotheque.models.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherantRepository extends JpaRepository<Adherant, Integer> {
    Adherant findByEmailAndMdp(String email, String mdp);
}
