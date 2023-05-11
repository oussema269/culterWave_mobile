/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.culturewave.reclamation.entities;

/**
 *
 * @author sarra
 */
public class user {

    private int id;
    private String email;
    private String nom;
    private String prenom;
    private String motdepasse;
    private String type;

    public user(String email, String nom, String prenom, String motdepasse) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
    }

    public user() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

  

    public user(String email, String motdepasse) {
        this.email = email;
        this.motdepasse = motdepasse;
    }

    public user(int id, String email, String nom, String prenom) {
        this.id = id;

        this.email = email;

        this.nom = nom;
        this.prenom = prenom;
    }

    public user(String email, String nom, String prenom) {

        this.email = email;

        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", motdepasse=" + motdepasse + '}';
    }

    public user(int id, String email, String nom, String prenom, String motdepasse, String type) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
}
