package com.bibliotheque.repositories;

import com.bibliotheque.models.Bibliothecaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Integer> {
    Bibliothecaire findByEmailAndMdp(String email, String mdp);
}
