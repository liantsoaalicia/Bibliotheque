package com.bibliotheque.models;

import jakarta.persistence.*;

@Entity
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numExemplaire;

    @ManyToOne
    @JoinColumn(name = "id_livre")
    private Livre livre;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNumExemplaire() { return numExemplaire; }
    public void setNumExemplaire(String numExemplaire) { this.numExemplaire = numExemplaire; }
    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
}
