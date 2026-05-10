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
public class Equipement {
 private String nom;
    private String type;
    private ArrayList<InterfaceReseau> interfaces;
    
    public Equipement(String nom, String type) {
        setNom(nom);
        setType(type);
        interfaces = new ArrayList<>();
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "equipement_inconnu";
        } else {
            this.nom = nom;
        }
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            this.type = "type_inconnu";
        } else {
            this.type = type;
        }
    }
    
    public void ajouterInterface(InterfaceReseau interfaceReseau) {
        interfaces.add(interfaceReseau);
    }
    
    public void afficherInterfaces() {
        if (interfaces.isEmpty()) {
            System.out.println("Aucune interface configurée");
        } else {
            for (InterfaceReseau interfaceReseau : interfaces) {
                interfaceReseau.afficher();
                System.out.println();
            }
        }
    }
    
    public void afficher() {
        System.out.println("=== Équipement ===");
        System.out.println("Nom: " + nom);
        System.out.println("Type: " + type);
        System.out.println("Nombre d'interfaces: " + interfaces.size());
        System.out.println("--- Interfaces ---");
        afficherInterfaces();
    }   
}
