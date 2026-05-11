/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Trésor
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
    
    // Gestion des équipements
    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement);
        System.out.println("Équipement " + equipement.getNom() + " ajouté");
    }
    
    public void supprimerEquipement(String nomEquipement) {
        for (int i = 0; i < equipements.size(); i++) {
            if (equipements.get(i).getNom().equals(nomEquipement)) {
                equipements.remove(i);
                System.out.println("Équipement " + nomEquipement + " supprimé");
                return;
            }
        }
        System.out.println("Équipement " + nomEquipement + " non trouvé");
    }
    
    // Gestion des sous-réseaux
    public void ajouterSousReseau(SousReseau sousReseau) {
        sousReseaux.add(sousReseau);  // ← CORRIGÉ: sousReseaux (pas sousReseau)
        System.out.println("Sous-réseau " + sousReseau.getNom() + " ajouté");
    }
    
    public void supprimerSousReseau(String nomSousReseau) {
        for (int i = 0; i < sousReseaux.size(); i++) {
            if (sousReseaux.get(i).getNom().equals(nomSousReseau)) {
                sousReseaux.remove(i);
                System.out.println("Sous-réseau " + nomSousReseau + " supprimé");
                return;
            }
        }
        System.out.println("Sous-réseau " + nomSousReseau + " non trouvé");
    }
    
    // Affichage des équipements
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
    
    // Affichage des sous-réseaux
    public void afficherSousReseaux() {  // ← CORRIGÉ: nom de méthode
        if (sousReseaux.isEmpty()) {
            System.out.println("Aucun sous-réseau dans l'infrastructure");
        } else {
            System.out.println("\n--- Liste des sous-réseaux ---");
            for (SousReseau sousReseau : sousReseaux) {  // ← CORRIGÉ: sousReseaux
                sousReseau.afficher();
                System.out.println();
            }
        }
    }
    
    // Recherche d'un équipement
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
    
    // Affichage complet
    public void afficher() {
        System.out.println("\n=========================================");
        System.out.println("Infrastructure: " + nom);
        System.out.println("=========================================");
        afficherSousReseaux();
        afficherEquipements();
    }
}