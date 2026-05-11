/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */
public class SousReseau {
  private String nom;
    private ReseauIP reseau;
    
    public SousReseau(String nom, ReseauIP reseau) {
        setNom(nom);
        this.reseau = reseau;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "Sous-reseau_inconnu";
        } else {
            this.nom = nom;
        }
    }
    
    public ReseauIP getReseau() {
        return reseau;
    }
    
    public void setReseau(ReseauIP reseau) {
        this.reseau = reseau;
    }
    
    public void afficher() {
        System.out.println("=== Sous-réseau ===");
        System.out.println("Nom: " + nom);
        if (reseau != null) {
            reseau.afficher();
        }
    }
    
}
