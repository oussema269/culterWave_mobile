/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.culturewave.reclamation.entities;

/**
 *
 * @author azizt
 */
public class produit {
    
     private int id, stock, id_cat,id_owner;
    private float prix;
    private String lib;

    public produit(int id, String lib,float prix) {
        this.id = id;
        this.prix = prix;
        this.lib = lib;
    }
   
    /**
     *
     */
    public produit() {
    }

    /**
     *
     * @param lib
     * @param stock
     * @param prix
     */
    public produit(String lib, int stock, float prix) {
        this.stock = stock;
        this.prix = prix;
        this.lib = lib;
    }

    /**
     *
     * @param id_cat
     * @param lib
     * @param stock
     * @param prix
     */
    public produit(int id_cat,String lib, int stock, float prix) {
          
        this.id_cat = id_cat;
        this.lib = lib;
        this.stock = stock;
        this.prix = prix;
       
    }

    /**
     *
     * @param lib
     * @param stock
     * @param prix
     * @param id_cat
     * @param id_owner
     */
    public produit( String lib, int stock, float prix, int id_cat,int id_owner) {
        
        this.lib = lib;
        this.stock = stock;
        this.prix = prix;
        this.id_cat = id_cat;
        this.id_owner=id_owner;
    }

    /**
     *
     * @param id
     * @param lib
     * @param stock
     * @param prix
     * @param id_cat
     */
    public produit(int id, String lib, int stock, float prix, int id_cat) {
        
        this.id = id;
        this.lib = lib;
        this.stock = stock;
        this.prix = prix;
        this.id_cat = id_cat;
      
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public float getPrix() {
        return prix;
    }

    /**
     *
     * @return
     */
    public int getId_owner()
    {
        return id_owner;
    }

    /**
     *
     * @param prix
     */
    public void setPrix(float prix) {
        this.prix = prix;
    }

    /**
     *
     * @return
     */
    public String getLib() {
        return lib;
    }

    /**
     *
     * @param lib
     */
    public void setLib(String lib) {
        this.lib = lib;
    }

    /**
     *
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * @return
     */
    public int getId_cat() {
        return id_cat;
    }

    /**
     *
     * @param id_cat
     */
    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    /**
     *
     */
    public void setId_owner()
    {
        this.id_owner=id_owner;
    }

    

    @Override
    public String toString() {
        return "Produit [id=" + id + ", lib=" + lib + ", prix=" + prix + ", stock=" + stock + ", id_cat=" + id_cat + "]";
    }
    
}
