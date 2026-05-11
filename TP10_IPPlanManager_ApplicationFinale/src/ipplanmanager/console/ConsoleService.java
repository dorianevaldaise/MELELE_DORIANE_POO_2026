/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.console;

/**
 *
 * @author Mahone
 */

import ipplanmanager.model.BesoinReseau;
import java.util.ArrayList;
import java.util.Scanner;
public class ConsoleService {
  private Scanner scanner;
    
    public ConsoleService() {
        scanner = new Scanner(System.in);
    }
    
    public String saisirTexte(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
    
    public int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  ❌ Valeur invalide, entrez un nombre entier.");
            }
        }
    }
    
    public ArrayList<BesoinReseau> saisirBesoins() {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        int nbBesoins = saisirEntier("  Nombre de besoins réseau à saisir : ");
        
        for (int i = 1; i <= nbBesoins; i++) {
            System.out.println("\n  --- Besoin " + i + " ---");
            String nom = saisirTexte("  Nom du service/département : ");
            int hotes = saisirEntier("  Nombre d'hôtes demandés : ");
            besoins.add(new BesoinReseau(nom, hotes));
        }
        return besoins;
    }
    
    public void afficherMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    MENU IPPLAN-MANAGER                      ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Saisir les besoins et générer un plan complet          ║");
        System.out.println("║  2. Charger les besoins depuis un fichier CSV              ║");
        System.out.println("║  3. Quitter                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.print("  Choix : ");
    }
    
    public void afficherEnTete(String titre) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.printf("║%40s%20s║%n", titre, "");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    public void afficherSousTitre(String titre) {
        System.out.println("\n  ┌─────────────────────────────────────────────────────────┐");
        System.out.printf("  │%40s%11s│%n", titre, "");
        System.out.println("  └─────────────────────────────────────────────────────────┘");
    }
    
    public void pause() {
        System.out.print("\n  Appuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
    
    public void fermer() {
        scanner.close();
    }  
}
