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
import com.bibliotheque.services.PretConfigService;
import com.bibliotheque.services.PenaliteConfigService;
import com.bibliotheque.services.AdherantPenaliteService;
import com.bibliotheque.models.PretConfig;
import com.bibliotheque.models.PenaliteConfig;
import com.bibliotheque.models.AdherantPenalite;
import com.bibliotheque.models.Statut;
import com.bibliotheque.models.ProlongementPret;
import com.bibliotheque.services.StatutService;
import com.bibliotheque.services.ProlongementPretService;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bibliotheque.services.JourFerieService;
import com.bibliotheque.models.JourFerie;

@Controller
@RequestMapping("/pret")
public class PretController {

    private final AdherantService adherantService;
    private final ExemplaireService exemplaireService;
    private final TypePretService typePretService;
    private final PretService pretService;
    private final PretConfigService pretConfigService;
    private final PenaliteConfigService penaliteConfigService;
    private final AdherantPenaliteService adherantPenaliteService;
    private final StatutService statutService;
    private final ProlongementPretService prolongementPretService;
    private final JourFerieService jourFerieService;

    public PretController(AdherantService adherantService, ExemplaireService exemplaireService, TypePretService typePretService, PretService pretService, PretConfigService pretConfigService, PenaliteConfigService penaliteConfigService, AdherantPenaliteService adherantPenaliteService, StatutService statutService, ProlongementPretService prolongementPretService, JourFerieService jourFerieService) {
        this.adherantService = adherantService;
        this.exemplaireService = exemplaireService;
        this.typePretService = typePretService;
        this.pretService = pretService;
        this.pretConfigService = pretConfigService;
        this.penaliteConfigService = penaliteConfigService;
        this.adherantPenaliteService = adherantPenaliteService;
        this.statutService = statutService;
        this.prolongementPretService = prolongementPretService;
        this.jourFerieService = jourFerieService;
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
    public String returnPret(@RequestParam("dateRetour") @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRetour,
                             @PathVariable Integer id,
                             @RequestParam(value = "choixJourFerie", required = false) String choixJourFerie,
                             Model model) {
        Pret pret = pretService.findById(id).orElse(null);
        if (pret == null) {
            model.addAttribute("error", "Pret pas trouve");
            return "redirect:/pret/list";
        }

        boolean isJourFerie = jourFerieService.findAll().stream()
                .anyMatch(jf -> jf.getDateFerie() != null && jf.getDateFerie().equals(dateRetour));

        if (isJourFerie && choixJourFerie == null) {
            model.addAttribute("pret", pret);
            model.addAttribute("dateRetour", dateRetour);
            model.addAttribute("isJourFerie", true);
            model.addAttribute("contentPage", "pret-choix-jourferie");
            return "back-office/template";
        }

        Date dateRetourEffective = dateRetour;
        if (isJourFerie && choixJourFerie != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateRetour);
            if ("lendemain".equals(choixJourFerie)) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            } else if ("veille".equals(choixJourFerie)) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }
            dateRetourEffective = cal.getTime();
        }

        pret.setDateRetour(dateRetourEffective);
        pretService.save(pret);
        Date datePrevue = pretService.getDateRetourPrevue(pret);
        if (datePrevue != null && dateRetourEffective.after(datePrevue)) {
            PenaliteConfig penaliteConfig = penaliteConfigService.findByProfil(pret.getAdherant().getProfil());
            int nbJourPenalite = penaliteConfig != null ? penaliteConfig.getNbJourPenalite() : 7; // défaut 7j
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateRetourEffective);
            cal.add(Calendar.DAY_OF_MONTH, nbJourPenalite);
            AdherantPenalite penalite = new AdherantPenalite();
            penalite.setAdherant(pret.getAdherant());
            penalite.setDateDebut(dateRetourEffective);
            penalite.setDateFin(cal.getTime());
            adherantPenaliteService.save(penalite);
            model.addAttribute("message", "Pénalité appliquée à l'adhérent.");
        } else {
            model.addAttribute("message", "Pret retourne avec succes");
        }
        return "redirect:/pret/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Pret pret, Model model) {
        Adherant adherant = adherantService.findById(pret.getAdherant().getId());
        Exemplaire exemplaire = exemplaireService.findById(pret.getExemplaire().getId());
        TypePret typePret = typePretService.findById(pret.getTypePret().getId()).orElse(null);

        System.out.println("Date d'emprunt : " + pret.getDateEmprunt());

        boolean quotaPasEncrAtteint = adherantService.quotaPasAtteint(adherant.getId());
        boolean exemplaireReserve = exemplaireService.isExemplaireReserve(exemplaire.getId(), pret.getDateEmprunt());
        boolean reservationRefusee = exemplaireService.isLastReservationRefusee(exemplaire.getId());
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

        PretConfig pretconfig = pretConfigService.findByProfil(adherant.getProfil());
        int nbJourPret = pretconfig.getNbJourPret();
        Calendar cal = Calendar.getInstance();
        cal.setTime(pret.getDateEmprunt());
        cal.add(Calendar.DAY_OF_MONTH, nbJourPret);
        Date nouvelleDateRetourPrevue = cal.getTime();

        pret.setDateRetourPrevue(nouvelleDateRetourPrevue);
        pret.setAdherant(adherant);
        pret.setExemplaire(exemplaire);
        pret.setTypePret(typePret);
        pretService.save(pret);
        return "redirect:/pret/list?pret=Emprunt enregistre avec succes";
    }

    @GetMapping("/en-cours")
    public String listPretsByAdherant(Model model, HttpSession session) {
        Adherant adherant = (Adherant) session.getAttribute("adherant_connecte");
        List<Pret> prets = pretService.findPretsEnCoursByAdherant(adherant);
        model.addAttribute("prets", prets);
        model.addAttribute("adherant", adherant);
        model.addAttribute("contentPage", "pret-en-cours");
        return "front-office/template";
    }

    @PostMapping("/prolonger/{id}")
    public String prolongerPret(@PathVariable Integer id, 
    RedirectAttributes redirectAttributes, Model model) {
        Pret pret = pretService.findById(id).orElse(null);
        if (pret == null) {
            model.addAttribute("error", "Pret non trouvé");
            return "redirect:/pret/en-cours";
        }

        PretConfig pretconfig = pretConfigService.findByProfil(pret.getAdherant().getProfil());
        int nbJourPret = pretconfig.getNbJourPret();
        Date dateFinPret = pret.getDateRetourPrevue();

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFinPret);
        cal.add(Calendar.DAY_OF_MONTH, nbJourPret);
        Date nouvelleDateRetourPrevue = cal.getTime();

        Statut statut = statutService.findByStatut("En cours");
        String message = "Prolongement en cours de validation";

        ProlongementPret prolongement = new ProlongementPret();
        prolongement.setPret(pret);
        prolongement.setDateDebut(dateFinPret);
        prolongement.setDateFin(nouvelleDateRetourPrevue);
        prolongement.setDateStatut(new Date());
        prolongement.setStatut(statut);
        prolongementPretService.save(prolongement);

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/pret/en-cours";
    }

}