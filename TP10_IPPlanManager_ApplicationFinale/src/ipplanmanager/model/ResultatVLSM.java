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
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;
    private int hotesDemandes;
    private String premiereAdresseUtilisable;
    private String derniereAdresseUtilisable;
    
    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr, 
                        String masqueDecimal, int capacite, int hotesDemandes) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.hotesDemandes = hotesDemandes;
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
    
    public int getHotesDemandes() {
        return hotesDemandes;
    }
    
    public int getMarge() {
        return capacite - hotesDemandes;
    }
    
    public String getPremiereAdresseUtilisable() {
        return premiereAdresseUtilisable;
    }
    
    public String getDerniereAdresseUtilisable() {
        return derniereAdresseUtilisable;
    }
    
    public void afficher() {
        System.out.printf("  • %-15s -> %-18s/%-3d | Masque: %-16s | Demandés: %4d | Capacité: %4d | Marge: %3d%n",
            nomBesoin, adresseReseau, cidr, masqueDecimal, hotesDemandes, capacite, getMarge());
    }  
}
