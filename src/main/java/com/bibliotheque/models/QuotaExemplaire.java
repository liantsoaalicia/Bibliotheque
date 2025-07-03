package com.bibliotheque.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class QuotaExemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int nbExemplaire;
    @Temporal(TemporalType.DATE)
    private Date dateChangement;

    @ManyToOne
    @JoinColumn(name = "id_profil")
    private Profil profil;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public int getNbExemplaire() { return nbExemplaire; }
    public void setNbExemplaire(int nbExemplaire) { this.nbExemplaire = nbExemplaire; }
    public Date getDateChangement() { return dateChangement; }
    public void setDateChangement(Date dateChangement) { this.dateChangement = dateChangement; }
    public Profil getProfil() { return profil; }
    public void setProfil(Profil profil) { this.profil = profil; }
}
