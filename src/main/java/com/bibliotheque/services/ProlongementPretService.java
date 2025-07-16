package com.bibliotheque.services;

import com.bibliotheque.models.ProlongementPret;
import com.bibliotheque.repositories.ProlongementPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bibliotheque.models.Pret;

@Service
public class ProlongementPretService {
    @Autowired
    private ProlongementPretRepository prolongementPretRepository;

    public ProlongementPret save(ProlongementPret prolongementPret) {
        return prolongementPretRepository.save(prolongementPret);
    }

    public ProlongementPret findLastByPret(Pret pret) {
        return prolongementPretRepository.findLastByPret(pret).orElse(null);
    }

    public int countProlongementsEnCoursByAdherant(Integer adherantId) {
        // Compter les prolongements avec statut "En cours" pour cet adhérent
        return prolongementPretRepository.countByPretAdherantIdAndStatutStatut(adherantId, "En cours");
    }
}
