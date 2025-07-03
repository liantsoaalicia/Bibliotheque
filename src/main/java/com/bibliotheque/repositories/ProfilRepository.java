package com.bibliotheque.repositories;

import com.bibliotheque.models.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilRepository extends JpaRepository<Profil, Integer> {
}
