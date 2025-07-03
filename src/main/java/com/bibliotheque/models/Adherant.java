package com.bibliotheque.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Adherant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String mdp;

    @ManyToOne
    @JoinColumn(name = "id_profil")
    private Profil profil;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getMdp() { return mdp; }
    public void setMdp(String mdp) { this.mdp = mdp; }
    public Profil getProfil() { return profil; }
    public void setProfil(Profil profil) { this.profil = profil; }
}
