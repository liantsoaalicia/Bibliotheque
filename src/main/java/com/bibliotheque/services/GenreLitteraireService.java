package com.bibliotheque.services;

import com.bibliotheque.models.GenreLitteraire;
import com.bibliotheque.repositories.GenreLitteraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GenreLitteraireService {
    @Autowired
    private GenreLitteraireRepository genreLitteraireRepository;

    public List<GenreLitteraire> findAll() {
        return genreLitteraireRepository.findAll();
    }

    public GenreLitteraire findById(Integer id) {
        return genreLitteraireRepository.findById(id).orElse(null);
    }

   
}