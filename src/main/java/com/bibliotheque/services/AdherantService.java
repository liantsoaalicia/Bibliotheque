package com.bibliotheque.services;

import com.bibliotheque.models.Adherant;
import com.bibliotheque.repositories.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class AdherantService {
    @Autowired
    private AdherantRepository adherantRepository;

    public Adherant findByEmailAndMdp(String email, String mdp) {
        return adherantRepository.findByEmailAndMdp(email, mdp);
    }

    public Integer findNbExemplaireByAdherantId(Integer adherantId) {
        return adherantRepository.findNbExemplaireByAdherantId(adherantId);
    }

    public Integer countPretsEnCoursByAdherantId(Integer adherantId) {
        return adherantRepository.countPretsEnCoursByAdherantId(adherantId);
    }

    public boolean quotaPasAtteint(Integer adherantId) {
        Integer nbExemplaireAutorise = this.findNbExemplaireByAdherantId(adherantId); 
        Integer nbExemplaireEmpruntes = this.countPretsEnCoursByAdherantId(adherantId);

        if(nbExemplaireEmpruntes.intValue() <= nbExemplaireAutorise.intValue()) {
            return true; 
        } 
        return false;
    }

    public Integer getAgeByAdherantIdAndDate(Integer adherantId, Date date) {
        Adherant adherant = adherantRepository.findById(adherantId).orElse(null);
        if (adherant == null || adherant.getDateNaissance() == null || date == null) return null;
        java.util.Calendar birth = java.util.Calendar.getInstance();
        birth.setTime(adherant.getDateNaissance());
        java.util.Calendar ref = java.util.Calendar.getInstance();
        ref.setTime(date);
        int age = ref.get(java.util.Calendar.YEAR) - birth.get(java.util.Calendar.YEAR);
        if (ref.get(java.util.Calendar.MONTH) < birth.get(java.util.Calendar.MONTH) ||
            (ref.get(java.util.Calendar.MONTH) == birth.get(java.util.Calendar.MONTH) && ref.get(java.util.Calendar.DAY_OF_MONTH) < birth.get(java.util.Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }

    public boolean isAbonneByAdherantIdAndDate(Integer adherantId, Date dateToCheck) {
        return adherantRepository.isAbonneByAdherantIdAndDate(adherantId, dateToCheck);
    }

    public boolean hasPenaliteAtDate(Integer adherantId, Date dateToCheck) {
        return adherantRepository.hasPenaliteAtDate(adherantId, dateToCheck);
    }

    public List<Adherant> findAll() {
        return adherantRepository.findAll();
    }

    public Adherant findById(Integer id) {
        return adherantRepository.findById(id).orElse(null);
    }

}
