package com.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import com.bibliotheque.models.AdherantPenalite;
import com.bibliotheque.services.AdherantPenaliteService;
import java.util.List;

@Controller
@RequestMapping("/adherantpenalite")
public class AdherantPenaliteController {

    private final AdherantPenaliteService adherantPenaliteService;
    public AdherantPenaliteController(AdherantPenaliteService adherantPenaliteService) {
        this.adherantPenaliteService = adherantPenaliteService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<AdherantPenalite> penalites = adherantPenaliteService.findAll();
        model.addAttribute("adherentpenalite", penalites);
        model.addAttribute("contentPage", "penalite-list");
        return "back-office/template";
    }
}