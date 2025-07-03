package com.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bibliotheque.models.Adherant;
import com.bibliotheque.services.AdherantService;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/adherant")
public class AdherantController {

    private final AdherantService adherantService;

    public AdherantController(AdherantService adherantService) {
        this.adherantService = adherantService;
    }
    
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("adherant", new Adherant()); 
        model.addAttribute("contentPage", "front-office/adherant-form");
        return "template";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute Adherant adherant, Model model) {
        Adherant loggedInAdherant = adherantService.findByEmailAndMdp(adherant.getEmail(), adherant.getMdp());
        if(loggedInAdherant != null) {
            model.addAttribute("adherant_connecte", loggedInAdherant);
            model.addAttribute("contentPage", "front-office/accueil");
            return "template";
        }
        return "redirect:/adherant/form";
    }
}
