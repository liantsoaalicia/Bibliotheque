package com.bibliotheque.services;

import com.bibliotheque.models.Adherant;
import com.bibliotheque.repositories.BibliothecaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.bibliotheque.models.Bibliothecaire;

@Service
public class BibliothecaireService {
    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;

    public Bibliothecaire findByEmailAndMdp(String email, String mdp) {
        return bibliothecaireRepository.findByEmailAndMdp(email, mdp);
    }
}
