package com.bibliotheque.services;

import com.bibliotheque.models.Pret;
import com.bibliotheque.repositories.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PretService {
    @Autowired
    private PretRepository pretRepository;

    public List<Pret> findAll() {
        return pretRepository.findAll();
    }

    public List<Pret> findPretsEnCours() {
        return pretRepository.findPretsEnCours();
    }

    public Optional<Pret> findById(Integer id) {
        return pretRepository.findById(id);
    }

    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    public void deleteById(Integer id) {
        pretRepository.deleteById(id);
    }

}
