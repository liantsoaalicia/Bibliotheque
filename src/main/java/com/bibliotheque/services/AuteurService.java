package com.bibliotheque.services;

import com.bibliotheque.models.Auteur;
import com.bibliotheque.repositories.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuteurService {

    @Autowired
    private AuteurRepository auteurRepository;

    public List<Auteur> findAll() {
        return auteurRepository.findAll();
    }

    public Auteur findById(Integer id) {
        return auteurRepository.findById(id).orElse(null);
    }

    public Auteur save(Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    public void deleteById(Integer id) {
        auteurRepository.deleteById(id);
    }

    

}