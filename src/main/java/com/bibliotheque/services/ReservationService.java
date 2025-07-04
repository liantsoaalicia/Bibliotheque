package com.bibliotheque.services;

import com.bibliotheque.models.Reservation;
import com.bibliotheque.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findByStatut(String libelle) {
        return reservationRepository.findByStatut(libelle);
    }

    public Reservation findById(Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }
}