package com.bibliotheque.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class AdherantAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @OneToOne
    @JoinColumn(name = "id_adherant")
    private Adherant adherant;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }
}
