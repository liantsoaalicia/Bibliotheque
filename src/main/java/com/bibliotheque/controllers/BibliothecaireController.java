package com.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import com.bibliotheque.models.Bibliothecaire;
import com.bibliotheque.services.BibliothecaireService;

@Controller
@RequestMapping("/bibliothecaire")
public class BibliothecaireController {

    private final BibliothecaireService bibliothecaireService;

    public BibliothecaireController(BibliothecaireService bibliothecaireService) {
        this.bibliothecaireService = bibliothecaireService;
    }
    
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("bibliothecaire", new Bibliothecaire());
        // model.addAttribute("contentPage", "");
        return "back-office/bibliothecaire-form";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Bibliothecaire bibliothecaire, Model model, HttpSession httpSession) {
        Bibliothecaire loggedInBibliothecaire = bibliothecaireService.findByEmailAndMdp(bibliothecaire.getEmail(), bibliothecaire.getMdp());
        if(loggedInBibliothecaire != null) {
            httpSession.setAttribute("bibliothecaire_connecte", loggedInBibliothecaire);
            model.addAttribute("contentPage", "accueil");
            return "back-office/template";
        }
        return "redirect:/bibliothecaire/form";
    }

    @GetMapping("/accueil") 
    public String redirectAccueil(Model model) {
        model.addAttribute("contentPage", "accueil");
        return "back-office/template";
    }
    


}
