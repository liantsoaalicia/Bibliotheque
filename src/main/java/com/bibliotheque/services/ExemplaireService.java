package com.bibliotheque.services;

import com.bibliotheque.models.Exemplaire;
import com.bibliotheque.repositories.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public List<Exemplaire> findAll() {
        return exemplaireRepository.findAll();
    }

    public Exemplaire findById(Integer id) {
        return exemplaireRepository.findById(id).orElse(null);
    }

    public boolean isExemplaireReserve(Integer exemplaireId, Date dateToCheck) {
        return exemplaireRepository.isExemplaireReserveAvantDate(exemplaireId, dateToCheck);
    }

    public boolean isLastReservationRefusee(Integer exemplaireId) {
        return exemplaireRepository.isLastReservationRefused(exemplaireId);
    }

    public List<Exemplaire> findDisponibles() {
        return exemplaireRepository.findExemplairesDisponibles();
    }

    public List<Exemplaire> findByLivreId(Integer livreId) {
        return exemplaireRepository.findByLivreId(livreId);
    }


}