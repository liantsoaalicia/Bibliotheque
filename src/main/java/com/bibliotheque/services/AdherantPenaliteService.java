package com.bibliotheque.services;

import com.bibliotheque.models.AdherantPenalite;
import com.bibliotheque.repositories.AdherantPenaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdherantPenaliteService {
    @Autowired
    private AdherantPenaliteRepository adherantPenaliteRepository;

    public AdherantPenalite save(AdherantPenalite penalite) {
        return adherantPenaliteRepository.save(penalite);
    }

    public List<AdherantPenalite> findAll() {
        return adherantPenaliteRepository.findAll();
    }
}
