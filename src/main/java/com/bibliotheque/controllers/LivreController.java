package com.bibliotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.bibliotheque.models.Livre;
import com.bibliotheque.services.LivreService;
import org.springframework.web.bind.annotation.PathVariable;
import com.bibliotheque.models.Auteur;
import com.bibliotheque.services.AuteurService;
import com.bibliotheque.models.MaisonEdition;
import com.bibliotheque.services.MaisonEditionService;
import com.bibliotheque.models.GenreLitteraire;
import com.bibliotheque.services.GenreLitteraireService;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import com.bibliotheque.models.Exemplaire;
import com.bibliotheque.models.Pret;
import com.bibliotheque.services.ExemplaireService;
import com.bibliotheque.services.PretService;

@Controller
@RequestMapping("/livre")
public class LivreController {

    private final LivreService livreService;
    private final AuteurService auteurService;
    private final MaisonEditionService maisonEditionService;
    private final GenreLitteraireService genreLitteraireService;
    private final ExemplaireService exemplaireService;
    private final PretService pretService;
   
    public LivreController(LivreService livreService, AuteurService auteurService, 
                           MaisonEditionService maisonEditionService, GenreLitteraireService genreLitteraireService,
                           ExemplaireService exemplaireService, PretService pretService) {
        this.livreService = livreService;
        this.auteurService = auteurService;
        this.maisonEditionService = maisonEditionService;
        this.genreLitteraireService = genreLitteraireService;
        this.exemplaireService = exemplaireService;
        this.pretService = pretService;
    }

    @GetMapping("/list")
    public String listLivres(Model model) {
        List<Livre> livres = livreService.findAll();
        model.addAttribute("livres", livres);
        model.addAttribute("contentPage", "livre-list");
        return "back-office/template";
    }

    @GetMapping("/add") 
    public String addLivre(Model model) {
        List<MaisonEdition> maisonsEdition = maisonEditionService.findAll();
        List<Auteur> auteurs = auteurService.findAll();
        List<GenreLitteraire> genresLitteraires = genreLitteraireService.findAll();

        model.addAttribute("livre", new Livre());
        model.addAttribute("maisonsEdition", maisonsEdition);
        model.addAttribute("auteurs", auteurs);
        model.addAttribute("genresLitteraires", genresLitteraires);
        model.addAttribute("contentPage", "livre-form");
        return "back-office/template";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Livre livre = livreService.findById(id);
        if (livre == null) {
            throw new RuntimeException("Livre not found");
        }
        List<MaisonEdition> maisonsEdition = maisonEditionService.findAll();
        List<Auteur> auteurs = auteurService.findAll();
        List<GenreLitteraire> genresLitteraires = genreLitteraireService.findAll();
        model.addAttribute("livre", livre);
        model.addAttribute("maisonsEdition", maisonsEdition);
        model.addAttribute("auteurs", auteurs);
        model.addAttribute("genresLitteraires", genresLitteraires);
        model.addAttribute("contentPage", "livre-form");
        return "back-office/template";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        livreService.deleteById(id);
        return "redirect:/livre/list";
    }

    @PostMapping("/save") 
    public String save(@ModelAttribute Livre livre) {
        Auteur auteur = auteurService.findById(livre.getAuteur().getId());
        GenreLitteraire genre = genreLitteraireService.findById(livre.getGenreLitteraire().getId());
        MaisonEdition maisonEdition = maisonEditionService.findById(livre.getMaisonEdition().getId());

        livre.setAuteur(auteur);
        livre.setGenreLitteraire(genre);
        livre.setMaisonEdition(maisonEdition);

        // ca fait un update si l'id de livre existe
        livreService.save(livre); 
        return "redirect:/livre/list";
    }

    private boolean isExemplaireDisponibleADate(Exemplaire exemplaire, Date date) {
        List<Pret> prets = pretService.findByExemplaireId(exemplaire.getId());
        for (Pret pret : prets) {
            if (pret.getDateRetour() == null) {
                Date dateEmprunt = pret.getDateEmprunt();
                Date dateRetourPrevue = pret.getDateRetourPrevue();
                
                if (dateEmprunt != null && dateRetourPrevue != null) {
                    if ((date.equals(dateEmprunt) || date.after(dateEmprunt)) && 
                        (date.equals(dateRetourPrevue) || date.before(dateRetourPrevue))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // http://localhost:8080/livre/availability/1?date=2025-07-20
    @GetMapping("/availability/{id}")
    @ResponseBody
    public Map<String, Object> checkLivreAvailability(
            @PathVariable Integer id,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        
        Map<String, Object> response = new HashMap<>();
        
        Livre livre = livreService.findById(id);
        if (livre == null) {
            response.put("error", "Livre non trouvé");
            return response;
        }
        
        Map<String, Object> livreInfo = new HashMap<>();
        livreInfo.put("id", livre.getId());
        livreInfo.put("titre", livre.getTitre());
        livreInfo.put("tag", livre.getTag());
        livreInfo.put("edition", livre.getEdition());
        livreInfo.put("ageRequis", livre.getAgeRequis());
        livreInfo.put("auteur", livre.getAuteur().getNom());
        livreInfo.put("genre", livre.getGenreLitteraire().getLibelle());
        livreInfo.put("maisonEdition", livre.getMaisonEdition().getNom());
        
        List<Exemplaire> exemplaires = exemplaireService.findByLivreId(id);
        List<Map<String, Object>> exemplairesInfo = new ArrayList<>();
        
        for(Exemplaire exemplaire : exemplaires) {
            Map<String, Object> exemplaireInfo = new HashMap<>();
            exemplaireInfo.put("id", exemplaire.getId());
            exemplaireInfo.put("num_exemplaire", exemplaire.getNumExemplaire());
            
            boolean disponible = isExemplaireDisponibleADate(exemplaire, date);
            exemplaireInfo.put("disponible", disponible);
            exemplairesInfo.add(exemplaireInfo);
        }
        
        response.put("livre", livreInfo);
        response.put("exemplaires", exemplairesInfo);
        return response;
    }
    
   

    
}
