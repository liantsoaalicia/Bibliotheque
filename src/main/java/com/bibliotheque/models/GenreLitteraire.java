package com.bibliotheque.models;

import jakarta.persistence.*;

@Entity
public class GenreLitteraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String libelle;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}
