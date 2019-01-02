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
public class Equipements {
    private int id;
    private String libelle;
    private String reference;
    private String domaine;
    private String salle;
    private String date;
    private String etat;
    private String fournisseur;

    public Equipements(int id, String libelle,String reference,  String domaine, String salle, String date, String etat, String fournisseur) {
        this.id = id;
        this.libelle = libelle;
        this.reference = reference;
        this.domaine = domaine;
        this.salle = salle;
        this.date = date;
        this.etat = etat;
        this.fournisseur = fournisseur;
    }

    public Equipements() {
    }
     public Equipements(String libelle,String reference, String domaine, String salle, String etat, String fournisseur) {
        this.libelle = libelle;
        this.reference = reference;
        this.domaine = domaine;
        this.salle = salle;
        this.etat = etat;
        this.fournisseur = fournisseur;
    }

    public Equipements(String libelle,String reference, String domaine, String salle, String date, String etat, String fournisseur) {
        this.libelle = libelle;
        this.reference = reference;
        this.domaine = domaine;
        this.salle = salle;
        this.date = date;
        this.etat = etat;
        this.fournisseur = fournisseur;
    }

    public int getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public String toString() {
        return "Equipements{" + "id=" + id + ", libelle=" + libelle + ", reference=" + reference + ", domaine=" + domaine + ", salle=" + salle + ", date=" + date + ", etat=" + etat + ", fournisseur=" + fournisseur + '}';
    }

    
      
    
}
