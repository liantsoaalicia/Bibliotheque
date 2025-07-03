package com.bibliotheque.models;

import jakarta.persistence.*;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titre;
    private String edition;
    private String tag;
    private int ageRequis;

    @ManyToOne
    @JoinColumn(name = "id_maison")
    private MaisonEdition maisonEdition;

    @ManyToOne
    @JoinColumn(name = "id_genre")
    private GenreLitteraire genreLitteraire;

    @ManyToOne
    @JoinColumn(name = "id_auteur")
    private Auteur auteur;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getEdition() { return edition; }
    public void setEdition(String edition) { this.edition = edition; }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public int getAgeRequis() { return ageRequis; }
    public void setAgeRequis(int ageRequis) { this.ageRequis = ageRequis; }
    public MaisonEdition getMaisonEdition() { return maisonEdition; }
    public void setMaisonEdition(MaisonEdition maisonEdition) { this.maisonEdition = maisonEdition; }
    public GenreLitteraire getGenreLitteraire() { return genreLitteraire; }
    public void setGenreLitteraire(GenreLitteraire genreLitteraire) { this.genreLitteraire = genreLitteraire; }
    public Auteur getAuteur() { return auteur; }
    public void setAuteur(Auteur auteur) { this.auteur = auteur; }
}
