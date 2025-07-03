package com.bibliotheque.services;

import com.bibliotheque.models.Adherant;
import com.bibliotheque.repositories.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherantService {
    @Autowired
    private AdherantRepository adherantRepository;

    public Adherant findByEmailAndMdp(String email, String mdp) {
        return adherantRepository.findByEmailAndMdp(email, mdp);
    }
}
