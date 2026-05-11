/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.model;

/**
 *
 * @author Mahone
 */
public class ResultatVLSM {
 private String nomBesoin;
    private int nombreHotesDemandes;
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;
    private String premiereAdresseUtilisable;
    private String derniereAdresseUtilisable;
    
    public ResultatVLSM(String nomBesoin, int nombreHotesDemandes, String adresseReseau, 
                        int cidr, String masqueDecimal, int capacite) {
        this.nomBesoin = nomBesoin;
        this.nombreHotesDemandes = nombreHotesDemandes;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.premiereAdresseUtilisable = "";
        this.derniereAdresseUtilisable = "";
    }
    
    public void setPremiereAdresseUtilisable(String adresse) {
        this.premiereAdresseUtilisable = adresse;
    }
    
    public void setDerniereAdresseUtilisable(String adresse) {
        this.derniereAdresseUtilisable = adresse;
    }
    
    public String getNomBesoin() {
        return nomBesoin;
    }
    
    public int getNombreHotesDemandes() {
        return nombreHotesDemandes;
    }
    
    public String getAdresseReseau() {
        return adresseReseau;
    }
    
    public int getCidr() {
        return cidr;
    }
    
    public String getMasqueDecimal() {
        return masqueDecimal;
    }
    
    public int getCapacite() {
        return capacite;
    }
    
    public String getPremiereAdresseUtilisable() {
        return premiereAdresseUtilisable;
    }
    
    public String getDerniereAdresseUtilisable() {
        return derniereAdresseUtilisable;
    }
    
    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr + 
                         " | Capacité: " + capacite + " hôtes");
    }   
}
