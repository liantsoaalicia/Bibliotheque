package com.bibliotheque.services;

import com.bibliotheque.models.Livre;
import com.bibliotheque.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    public Livre findById(Integer id) {
        return livreRepository.findById(id).orElse(null);
    }

    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteById(Integer id) {
        livreRepository.deleteById(id);
    }

    

}