/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;
import java.util.Date;

/**
 *
 * @author dell
 */
public class Reclamation {
    

    private int idReclamation;
    private String typeReclamation;
    private String contenu;
    private String datepro;
    private int idReclamateur;
    private int idCibleReclamation;
    
    public Reclamation(int idReclamation,String typeReclamation, String contenu, String datepro, int idReclamateur,int idCibleReclamation ) {
        this.idReclamation = idReclamation;
        this.typeReclamation = typeReclamation;
        this.contenu = contenu;
        this.datepro = datepro;
        this.idReclamateur = idReclamateur;
        this.idCibleReclamation = idCibleReclamation;
    }
    
    public Reclamation(String typeReclamation, String contenu, String datepro, int idReclamateur,int idCibleReclamation ) {
        
        this.typeReclamation = typeReclamation;
        this.contenu = contenu;
        this.datepro = datepro;
        this.idReclamateur = idReclamateur;
        this.idCibleReclamation = idCibleReclamation;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDatepro() {
        return datepro;
    }

    public void setDatepro(String datepro) {
        this.datepro = datepro;
    }

    public int getIdReclamateur() {
        return idReclamateur;
    }

    public void setIdReclamateur(int idReclamateur) {
        this.idReclamateur = idReclamateur;
    }

    public int getIdCibleReclamation() {
        return idCibleReclamation;
    }

    public void setIdCibleReclamation(int idCibleReclamation) {
        this.idCibleReclamation = idCibleReclamation;
    }

    


    
}
