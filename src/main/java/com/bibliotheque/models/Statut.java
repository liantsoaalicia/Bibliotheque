package com.bibliotheque.models;

import jakarta.persistence.*;

@Entity
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String statut;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
