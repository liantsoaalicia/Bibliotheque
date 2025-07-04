package com.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bibliotheque.services.JourFerieService;

@Controller 
@RequestMapping("/jourferie")
public class JourFerieController {

    private final JourFerieService jourFerieService;

    public JourFerieController(JourFerieService jourFerieService) {
        this.jourFerieService = jourFerieService;
    }
    
    @GetMapping("/list")
    public String listJourFeries(Model model) {
        model.addAttribute("jourferies", jourFerieService.findAll());
        model.addAttribute("contentPage", "jour-ferie-list");
        return "back-office/template"; 
    }
}
