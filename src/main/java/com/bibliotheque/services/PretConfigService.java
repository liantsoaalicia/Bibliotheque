package com.bibliotheque.services;

import com.bibliotheque.models.PretConfig;
import com.bibliotheque.models.Profil;
import com.bibliotheque.repositories.PretConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PretConfigService {
    @Autowired
    private PretConfigRepository pretConfigRepository;

    public PretConfig findByProfil(Profil profil) {
        // On suppose qu'il n'y a qu'un PretConfig actif par profil (sinon, il faut filtrer par dateChangement)
        return pretConfigRepository.findAll().stream()
            .filter(pc -> pc.getProfil().getId().equals(profil.getId()))
            .findFirst().orElse(null);
    }
}
