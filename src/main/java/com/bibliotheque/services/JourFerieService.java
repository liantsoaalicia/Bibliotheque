package com.bibliotheque.services;

import com.bibliotheque.models.JourFerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bibliotheque.repositories.JourFerieRepository;
import java.util.List;

@Service
public class JourFerieService {
    
    @Autowired
    private JourFerieRepository jourFerieRepository;

    public List<JourFerie> findAll() {
        return jourFerieRepository.findAll();
    }
}
