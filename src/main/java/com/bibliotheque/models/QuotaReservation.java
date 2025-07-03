package com.bibliotheque.models;

import java.util.Date;
import jakarta.persistence.*;

@Entity
public class QuotaReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int nbReservation;
    @Temporal(TemporalType.DATE)
    private Date dateChangement;

    @ManyToOne
    @JoinColumn(name = "id_profil")
    private Profil profil;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public int getNbReservation() { return nbReservation; }
    public void setNbReservation(int nbReservation) { this.nbReservation = nbReservation; }
    public Date getDateChangement() { return dateChangement; }
    public void setDateChangement(Date dateChangement) { this.dateChangement = dateChangement; }
    public Profil getProfil() { return profil; }
    public void setProfil(Profil profil) { this.profil = profil; }
}
