/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author pc-dell
 */
public class Fournisseurs {
    private int id;
    private String nom;
    private String adresse;
    private int tel;
    private String email;

    public Fournisseurs(int id, String nom, String adresse, int tel, String email) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }

    public Fournisseurs(String nom, String adresse, int tel, String email) {
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }
     public Fournisseurs() {
        
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", tel=" + tel + ", email=" + email + '}';
    }
   
    
    
}
