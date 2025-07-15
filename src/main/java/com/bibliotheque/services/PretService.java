package com.bibliotheque.services;

import com.bibliotheque.models.Pret;
import com.bibliotheque.models.PretConfig;
import com.bibliotheque.models.Profil;
import com.bibliotheque.repositories.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bibliotheque.models.Adherant;
import com.bibliotheque.services.PretConfigService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {
    @Autowired
    private PretRepository pretRepository;
    @Autowired
    private PretConfigService pretConfigService;

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

    @Autowired
    private ProlongementPretService prolongementPretService;

    public Date getDateRetourPrevue(Pret pret) {
        if (pret == null || pret.getAdherant() == null || pret.getAdherant().getProfil() == null || pret.getDateEmprunt() == null) return null;
        // Vérifier s'il y a un prolongement pour ce prêt dcp tode iny ny date de retour estimee
        com.bibliotheque.models.ProlongementPret prolongement = prolongementPretService.findLastByPret(pret);
        if (prolongement != null) {
            return prolongement.getDateFin();
        }
        Profil profil = pret.getAdherant().getProfil();
        PretConfig config = pretConfigService.findByProfil(profil);
        if (config == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(pret.getDateEmprunt());
        cal.add(Calendar.DAY_OF_MONTH, config.getNbJourPret());
        return cal.getTime();
    }

    public List<Pret> findPretsEnCoursByAdherant(Adherant adherant) {
        return pretRepository.findPretsEnCoursByAdherant(adherant);
    }

    public List<Pret> findByExemplaireId(Integer exemplaireId) {
        return pretRepository.findByExemplaireId(exemplaireId);
    }

}
