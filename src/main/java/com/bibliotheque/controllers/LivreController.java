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

@Controller
@RequestMapping("/livre")
public class LivreController {

    private final LivreService livreService;
    private final AuteurService auteurService;
    private final MaisonEditionService maisonEditionService;
    private final GenreLitteraireService genreLitteraireService;
   
    public LivreController(LivreService livreService, AuteurService auteurService, 
                           MaisonEditionService maisonEditionService, GenreLitteraireService genreLitteraireService) {
        this.livreService = livreService;
        this.auteurService = auteurService;
        this.maisonEditionService = maisonEditionService;
        this.genreLitteraireService = genreLitteraireService;
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

}
