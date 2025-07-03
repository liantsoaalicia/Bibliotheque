package com.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;
import com.bibliotheque.models.Pret;
import com.bibliotheque.models.Adherant;
import com.bibliotheque.models.Exemplaire;
import com.bibliotheque.models.TypePret;
import com.bibliotheque.services.PretService;
import com.bibliotheque.services.ExemplaireService;
import com.bibliotheque.services.AdherantService;
import com.bibliotheque.services.TypePretService;
import java.util.List;
import java.util.Date;

@Controller
@RequestMapping("/pret")
public class PretController {

    private final AdherantService adherantService;
    private final ExemplaireService exemplaireService;
    private final TypePretService typePretService;
    private final PretService pretService;

    public PretController(AdherantService adherantService, ExemplaireService exemplaireService, TypePretService typePretService, PretService pretService) {
        this.adherantService = adherantService;
        this.exemplaireService = exemplaireService;
        this.typePretService = typePretService;
        this.pretService = pretService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        List<Adherant> adherants = adherantService.findAll();
        List<Exemplaire> exemplaires = exemplaireService.findAll();
        List<TypePret> typePrets = typePretService.findAll();

        model.addAttribute("adherants", adherants);
        model.addAttribute("exemplaires", exemplaires);
        model.addAttribute("typePrets", typePrets);
        model.addAttribute("pret", new Pret());
        model.addAttribute("contentPage", "pret-form");
        return "back-office/template";
    }

    @GetMapping("/list")
    public String listPrets(Model model, @RequestParam(value = "pret", required = false) String messagePret) {
        List<Pret> prets = pretService.findPretsEnCours();
        model.addAttribute("prets", prets);
        if(messagePret != null) {
            model.addAttribute("message", messagePret);
        }
        model.addAttribute("contentPage", "pret-en-cours");
        return "back-office/template";
    }

    @PostMapping("/return/{id}")
    public String returnPret(@RequestParam("dateRetour") @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRetour, @PathVariable Integer id, Model model) {
        Pret pret = pretService.findById(id).orElse(null);
        if (pret != null) {
            pret.setDateRetour(dateRetour);
            pretService.save(pret);
            model.addAttribute("message", "Pret retourne avec succes");
        } else {
            model.addAttribute("error", "Pret pas trouve");
        }
        return "redirect:/pret/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Pret pret, Model model) {
        Adherant adherant = adherantService.findById(pret.getAdherant().getId());
        Exemplaire exemplaire = exemplaireService.findById(pret.getExemplaire().getId());
        TypePret typePret = typePretService.findById(pret.getTypePret().getId()).orElse(null);

        boolean quotaPasEncrAtteint = adherantService.quotaPasAtteint(adherant.getId()); // donc afaka 
        boolean exemplaireReserve = exemplaireService.isExemplaireReserve(exemplaire.getId(), pret.getDateEmprunt()); // tsy afaka
        boolean reservationRefusee = exemplaireService.isLastReservationRefusee(exemplaire.getId()); // donc afaka 
        Integer ageAdherant = adherantService.getAgeByAdherantIdAndDate(adherant.getId(), pret.getDateEmprunt()); 
        boolean encrAbonne = adherantService.isAbonneByAdherantIdAndDate(adherant.getId(), pret.getDateEmprunt());
        boolean aUnePenalite = adherantService.hasPenaliteAtDate(adherant.getId(), pret.getDateEmprunt());

        if(!quotaPasEncrAtteint) {
            model.addAttribute("error", "Quota d'emprunts atteint pour cet adhérent.");
            model.addAttribute("contentPage", "pret-form");
            return "back-office/template";
        } else if(exemplaireReserve) {
            model.addAttribute("error", "L'exemplaire a deja ete reserve");
            model.addAttribute("contentPage", "pret-form");
            return "back-office/template";
        } else if(ageAdherant.intValue() < exemplaire.getLivre().getAgeRequis()) {
            model.addAttribute("error", "L'adherent n'a pas l'age requis pour emprunter cet exemplaire");
            model.addAttribute("contentPage", "pret-form");
            return "back-office/template";
        } else if(!encrAbonne) {
            model.addAttribute("error", "L'adherent n'est pas abonne");
            model.addAttribute("contentPage", "pret-form");
            return "back-office/template";
        } else if(aUnePenalite) {
            model.addAttribute("error", "L'adherent a une penalite en cours");
            model.addAttribute("contentPage", "pret-form");
            return "back-office/template";
        }

        pret.setAdherant(adherant);
        pret.setExemplaire(exemplaire);
        pret.setTypePret(typePret);
        pretService.save(pret);
        return "redirect:/pret/list?pret=Emprunt enregistre avec succes";
    }

}