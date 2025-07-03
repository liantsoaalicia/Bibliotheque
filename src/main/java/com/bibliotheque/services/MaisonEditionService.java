package com.bibliotheque.services;

import com.bibliotheque.models.MaisonEdition;
import com.bibliotheque.repositories.MaisonEditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaisonEditionService {

    @Autowired
    private MaisonEditionRepository maisonEditionRepository;

    public List<MaisonEdition> findAll() {
        return maisonEditionRepository.findAll();
    }

    public MaisonEdition findById(Integer id) {
        return maisonEditionRepository.findById(id).orElse(null);
    }

}