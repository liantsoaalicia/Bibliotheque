package com.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import com.bibliotheque.models.Reservation;
import com.bibliotheque.services.ReservationService;
import com.bibliotheque.services.StatutService;
import com.bibliotheque.models.Statut;
import com.bibliotheque.models.TypePret;
import com.bibliotheque.models.Exemplaire;
import com.bibliotheque.models.Pret;
import com.bibliotheque.models.PretConfig;
import com.bibliotheque.services.ExemplaireService;
import com.bibliotheque.services.PretConfigService;
import com.bibliotheque.services.PretService;
import com.bibliotheque.models.Adherant;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final StatutService statutService;
    private final ExemplaireService exemplaireService;
    private final PretConfigService pretConfigService;
    private final PretService pretService;

    public ReservationController(ReservationService reservationService, StatutService statutService, 
    ExemplaireService exemplaireService, PretConfigService pretConfigService, PretService pretService) {
        this.reservationService = reservationService;
        this.statutService = statutService;
        this.exemplaireService = exemplaireService;
        this.pretConfigService = pretConfigService;
        this.pretService = pretService;
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

        model.addAttribute("message", "Reservation effectuee , en attente de validation");
        model.addAttribute("contentPage", "reservation-form");
        return "front-office/template";
    }


    @GetMapping("/list-en-cours")
    public String list(Model model, @RequestParam(value = "message", required = false) String message, @RequestParam(value = "error", required = false) String error) {
        List<Reservation> reservations = reservationService.findByStatut("En cours");
        model.addAttribute("reservations", reservations);
        if (message != null) model.addAttribute("message", message);
        if(error != null) model.addAttribute("error", error);
        model.addAttribute("contentPage", "reservation-list");
        return "back-office/template";
    }

    // qd on valide, tode mivadika pret
    @GetMapping("/valider/{id}")
    public String validerReservation(@PathVariable("id") Integer id, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationService.findById(id);
        if (reservation != null) {
            Statut statut = statutService.findByStatut("Accepte");
            reservation.setStatut(statut);
            reservation.setDateStatut(new Date());
            reservationService.save(reservation);
            redirectAttributes.addFlashAttribute("message", "Reservation validee");

            Pret pret = new Pret();
            pret.setAdherant(reservation.getAdherant());
            pret.setDateEmprunt(new Date());
            pret.setExemplaire(reservation.getExemplaire());
            
            TypePret typePret = new TypePret();
            typePret.setId(1);
            pret.setTypePret(typePret);

            PretConfig pretconfig = pretConfigService.findByProfil(reservation.getAdherant().getProfil());
            int nbJourPret = pretconfig.getNbJourPret();
            Calendar cal = Calendar.getInstance();
            cal.setTime(pret.getDateEmprunt());
            cal.add(Calendar.DAY_OF_MONTH, nbJourPret);
            Date nouvelleDateRetourPrevue = cal.getTime();

            pret.setDateRetourPrevue(nouvelleDateRetourPrevue);

            pretService.save(pret);
        } 

        return "redirect:/reservation/list-en-cours";
    }

    @GetMapping("/refuser/{id}")
    public String refuserReservation(@PathVariable("id") Integer id, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationService.findById(id);
        if (reservation != null) {
            Statut statut = statutService.findByStatut("Refuse");
            reservation.setStatut(statut);
            reservation.setDateStatut(new Date());
            reservationService.save(reservation);
            redirectAttributes.addFlashAttribute("error", "Reservation refusee");
        } 
        return "redirect:/reservation/list-en-cours";
    }

}