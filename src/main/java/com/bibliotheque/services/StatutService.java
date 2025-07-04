package com.bibliotheque.services;

import com.bibliotheque.models.Statut;
import com.bibliotheque.repositories.StatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class StatutService {

    @Autowired
    private StatutRepository statutRepository;

    public Statut findByStatut(String statut) {
        return statutRepository.findByStatut(statut);
    }
  
}