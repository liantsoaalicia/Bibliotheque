package com.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import com.bibliotheque.models.Reservation;
import com.bibliotheque.services.ReservationService;
import com.bibliotheque.services.StatutService;
import com.bibliotheque.models.Statut;
import com.bibliotheque.models.Exemplaire;
import com.bibliotheque.services.ExemplaireService;
import com.bibliotheque.models.Adherant;
import java.util.List;
import java.util.Date;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final StatutService statutService;
    private final ExemplaireService exemplaireService;

    public ReservationController(ReservationService reservationService, StatutService statutService, ExemplaireService exemplaireService) {
        this.reservationService = reservationService;
        this.statutService = statutService;
        this.exemplaireService = exemplaireService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        List<Exemplaire> exemplaires = exemplaireService.findDisponibles();
        model.addAttribute("exemplaires", exemplaires);
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("contentPage", "reservation-form");
        return "front-office/template";
    }

    @PostMapping("/save") 
    public String save(@ModelAttribute Reservation reservation, Model model, HttpSession session) {
        Statut statut = statutService.findByStatut("En cours");
        reservation.setStatut(statut);
        reservation.setDateStatut(new java.util.Date());
        Adherant adherant = (Adherant) session.getAttribute("adherant_connecte");
        reservation.setAdherant(adherant);
        Reservation savedReservation = reservationService.save(reservation);

        model.addAttribute("message", "Reservation effectuee avec succes !");
        model.addAttribute("contentPage", "reservation-form");
        return "front-office/template";
    }
}