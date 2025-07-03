package com.bibliotheque.repositories;

import com.bibliotheque.models.QuotaReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotaReservationRepository extends JpaRepository<QuotaReservation, Integer> {
}
