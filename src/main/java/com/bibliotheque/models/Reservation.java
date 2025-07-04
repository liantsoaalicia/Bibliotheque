package com.bibliotheque.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Temporal(TemporalType.DATE)
    private Date dateStatut;

    @ManyToOne
    @JoinColumn(name = "id_statut")
    private Statut statut;

    @OneToOne
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_adherant")
    private Adherant adherant;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public Date getDateStatut() { return dateStatut; }
    public void setDateStatut(Date dateStatut) { this.dateStatut = dateStatut; }
    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }
    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }
    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }
}
