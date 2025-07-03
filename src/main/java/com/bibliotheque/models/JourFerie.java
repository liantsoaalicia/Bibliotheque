package com.bibliotheque.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class JourFerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date dateFerie;
    private String libelle;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getDateFerie() { return dateFerie; }
    public void setDateFerie(Date dateFerie) { this.dateFerie = dateFerie; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}
