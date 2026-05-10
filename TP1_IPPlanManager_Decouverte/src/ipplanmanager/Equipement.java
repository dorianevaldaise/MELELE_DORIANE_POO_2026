/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */
public class Equipement {
     String nom;
    String type;
    InterfaceReseau interfacePrincipale;

    public Equipement(String nom, String type, InterfaceReseau interfacePrincipale) {
        this.nom = nom;
        this.type = type;
        this.interfacePrincipale = interfacePrincipale;
    }

    public void afficher() {
        System.out.println("Nom de l'équipement : " + nom);
        System.out.println("Type d'équipement : " + type);
        if (interfacePrincipale != null) {
            interfacePrincipale.afficher();
        } else {
            System.out.println("Aucune interface réseau configurée.");
        }
    }
}
