/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.model;

/**
 *
 * @author Mahone
 */
public class AdresseIP {
  private String adresse;
    
    public AdresseIP(String adresse) {
        setAdresse(adresse);
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        if (adresse == null || adresse.isEmpty()) {
            this.adresse = "0.0.0.0";
        } else {
            this.adresse = adresse;
        }
    }
    
    public void afficher() {
        System.out.println("Adresse IP: " + adresse);
    }   
}
