/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author wajihbenhmida
 */
public class Evennement {
    
        private int id;
    private String nom ;
    private String description;
    private String lieu;
    private String date;
    private String prix;
    private String nb_participants;
    private String type_evennement;

    public Evennement() {
    }

    public Evennement( String nom, String description, String lieu,  String prix,String date, String nb_participants, String type_evennement) {
       
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
        this.prix = prix;
        this.date = date;
        
        this.nb_participants = nb_participants;
        this.type_evennement = type_evennement;
    }
    public Evennement(int id, String nom, String description,  String lieu,String prix, String date, String nb_participants, String type_evennement) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
         this.prix = prix;
        this.date = date;
       
        this.nb_participants = nb_participants;
        this.type_evennement = type_evennement;
    }
   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(String nb_participants) {
        this.nb_participants = nb_participants;
    }

    public String getType_evennement() {
        return type_evennement;
    }

    public void setType_evennement(String type_evennement) {
        this.type_evennement = type_evennement;
    }


    
}
