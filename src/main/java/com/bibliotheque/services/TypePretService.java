package com.bibliotheque.services;

import com.bibliotheque.models.TypePret;
import com.bibliotheque.repositories.TypePretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TypePretService {

    @Autowired
    private TypePretRepository typePretRepository;

    public List<TypePret> findAll() {
        return typePretRepository.findAll();
    }

    public Optional<TypePret> findById(Integer id) {
        return typePretRepository.findById(id);
    }


}