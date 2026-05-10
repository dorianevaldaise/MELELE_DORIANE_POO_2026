/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */
import java.util.ArrayList;
public class InfrastructureReseau {
   private String nom;
    private ArrayList<Equipement> equipements;
    private ArrayList<SousReseau> sousReseaux;
    
    public InfrastructureReseau(String nom) {
        this.nom = nom;
        this.equipements = new ArrayList<>();
        this.sousReseaux = new ArrayList<>();
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement);
        System.out.println("Équipement " + equipement.getNom() + " ajouté");
    }
    
    public void ajouterSousReseau(SousReseau sousReseau) {
        sousReseaux.add(sousReseau);
        System.out.println("Sous-réseau " + sousReseau.getNom() + " ajouté");
    }
    
    public void afficherEquipements() {
        if (equipements.isEmpty()) {
            System.out.println("Aucun équipement dans l'infrastructure");
        } else {
            System.out.println("\n--- Liste des équipements ---");
            for (Equipement equipement : equipements) {
                equipement.afficher();
                System.out.println();
            }
        }
    }
    
    public void afficherSousReseaux() {
        if (sousReseaux.isEmpty()) {
            System.out.println("Aucun sous-réseau dans l'infrastructure");
        } else {
            System.out.println("\n--- Liste des sous-réseaux ---");
            for (SousReseau sousReseau : sousReseaux) {
                sousReseau.afficher();
                System.out.println();
            }
        }
    }
    
    public void rechercherEquipement(String nom) {
        for (Equipement equipement : equipements) {
            if (equipement.getNom().equals(nom)) {
                System.out.println("Équipement trouvé !");
                equipement.afficher();
                return;
            }
        }
        System.out.println("Équipement " + nom + " introuvable");
    }
    
    public void afficher() {
        System.out.println("\n=========================================");
        System.out.println("Infrastructure: " + nom);
        System.out.println("=========================================");
        afficherSousReseaux();
        afficherEquipements();
    }  
}
