package com.bibliotheque.services;

import com.bibliotheque.models.PenaliteConfig;
import com.bibliotheque.models.Profil;
import com.bibliotheque.repositories.PenaliteConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenaliteConfigService {
    @Autowired
    private PenaliteConfigRepository penaliteConfigRepository;

    public PenaliteConfig findByProfil(Profil profil) {
        // On suppose qu'il n'y a qu'un PenaliteConfig actif par profil (sinon, il faut filtrer par dateChangement)
        return penaliteConfigRepository.findAll().stream()
            .filter(pc -> pc.getProfil().getId().equals(profil.getId()))
            .findFirst().orElse(null);
    }
}
