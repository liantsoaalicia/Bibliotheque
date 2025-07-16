package com.bibliotheque.controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import com.bibliotheque.models.Adherant;
import com.bibliotheque.models.AdherantPenalite;
import com.bibliotheque.services.AdherantPenaliteService;
import com.bibliotheque.services.AdherantService;

@Controller
@RequestMapping("/adherant")
public class AdherantController {

    private final AdherantService adherantService;
    private final AdherantPenaliteService adherantPenaliteService;

    public AdherantController(AdherantService adherantService, AdherantPenaliteService adherantPenaliteService) {
        this.adherantService = adherantService;
        this.adherantPenaliteService = adherantPenaliteService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("adherant", new Adherant());
        // model.addAttribute("contentPage", "front-office/adherant-form");
        return "front-office/adherant-form";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Adherant adherant, Model model, HttpSession httpSession) {
        Adherant loggedInAdherant = adherantService.findByEmailAndMdp(adherant.getEmail(), adherant.getMdp());
        if(loggedInAdherant != null) {
            httpSession.setAttribute("adherant_connecte", loggedInAdherant);
            model.addAttribute("contentPage", "accueil");
            return "front-office/template";
        }
        return "redirect:/adherant/form";
    }

    @GetMapping("/accueil") 
    public String redirectAccueil(Model model) {
        model.addAttribute("contentPage", "accueil");
        return "front-office/template";
    }

    // abonne ou pas
    // quota ambiny
    // ses penalites par rapport a la date actuel
    // http://localhost:8080/adherant/info/1?date=2025-07-20
    @GetMapping("/info/{id}")
    @ResponseBody
    public Map<String, Object> checkSituationAdherent(
        @PathVariable Integer id,
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        Map <String, Object> response = new HashMap<>();
        Adherant adherant = adherantService.findById(id);
        if(adherant == null) {
            response.put("error", "Adherent non trouve");
            return response;
        }

        boolean abonneOuPas = adherantService.isAbonneByAdherantIdAndDate(adherant.getId(), date);
        Integer nbExemplairePermis = adherantService.findNbExemplaireByAdherantId(adherant.getId());
        Integer pretEnCours = adherantService.countPretsEnCoursByAdherantId(adherant.getId());
        Integer resteQuota = nbExemplairePermis.intValue() - pretEnCours.intValue();

        Map<String, Object> adherantInfo = new HashMap<>();
        adherantInfo.put("id", adherant.getId());
        adherantInfo.put("nom", adherant.getNom());
        adherantInfo.put("email", adherant.getEmail());
        adherantInfo.put("date_naissance", adherant.getDateNaissance());
        adherantInfo.put("profil", adherant.getProfil().getLibelle());
        adherantInfo.put("abonne", abonneOuPas);
        adherantInfo.put("reste_quota", resteQuota);

        List<AdherantPenalite> penalites = adherantPenaliteService.findPenalitesAtDate(adherant.getId(), date);
        List<Map<String, Object>> penalitesInfo = new ArrayList<>();

        for(AdherantPenalite adherantPenalite : penalites) {
            Map<String, Object> penaliteInfo = new HashMap<>();
            penaliteInfo.put("id", adherantPenalite.getId());
            penaliteInfo.put("date_debut", adherantPenalite.getDateDebut());
            penaliteInfo.put("date_fin", adherantPenalite.getDateFin());

            penalitesInfo.add(penaliteInfo);
        }

        response.put("info_adherent", adherantInfo);
        response.put("penalites", penalitesInfo);
        return response;
    }

}