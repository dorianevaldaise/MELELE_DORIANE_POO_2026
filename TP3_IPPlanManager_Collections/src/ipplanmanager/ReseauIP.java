/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */
public class ReseauIP {
   private String adresseReseau;
    private int cidr;
    private String nom;
    
    public ReseauIP(String adresseReseau, int cidr, String nom) {
        setAdresseReseau(adresseReseau);
        setCidr(cidr);
        setNom(nom);
    }
    
    public String getAdresseReseau() {
        return adresseReseau;
    }
    
    public void setAdresseReseau(String adresseReseau) {
        if (adresseReseau == null || adresseReseau.isEmpty()) {
            this.adresseReseau = "192.168.0.0";
        } else {
            this.adresseReseau = adresseReseau;
        }
    }
    
    public int getCidr() {
        return cidr;
    }
    
    public void setCidr(int cidr) {
        if (cidr < 0 || cidr > 32) {
            this.cidr = 24;
        } else {
            this.cidr = cidr;
        }
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "Reseau_inconnu";
        } else {
            this.nom = nom;
        }
    }
    
    public void afficher() {
        System.out.println("Reseau: " + nom);
        System.out.println("Adresse: " + adresseReseau + "/" + cidr);
    }  
}
