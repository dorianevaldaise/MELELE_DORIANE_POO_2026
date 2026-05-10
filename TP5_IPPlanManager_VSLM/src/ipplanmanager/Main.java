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
public class Main {
  public static void main(String[] args) {
        
        System.out.println("=========================================");
        System.out.println("    IPPLAN-MANAGER - MOTEUR VLSM");
        System.out.println("=========================================\n");
        
        // ========= SCÉNARIO 1: Entreprise standard =========
        System.out.println("=== SCÉNARIO 1: ENTREPRISE STANDARD ===\n");
        
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("TECHNIQUE", 120));
        besoins1.add(new BesoinReseau("WIFI", 80));
        besoins1.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins1.add(new BesoinReseau("SERVEURS", 20));
        besoins1.add(new BesoinReseau("DIRECTION", 10));
        
        System.out.println("Besoins exprimés par l'utilisateur:");
        for (BesoinReseau besoin : besoins1) {
            besoin.afficher();
        }
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats1 = moteur.genererPlan("192.168.1.0", besoins1);
        
        System.out.println("\nPlan d'adressage proposé:");
        System.out.println("----------------------------------------");
        for (ResultatVLSM resultat : resultats1) {
            resultat.afficher();
        }
        
        // ========= SCÉNARIO 2: Petite entreprise =========
        System.out.println("\n\n=== SCÉNARIO 2: PETITE ENTREPRISE ===\n");
        
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("COMMERCIAL", 30));
        besoins2.add(new BesoinReseau("COMPTABILITE", 15));
        besoins2.add(new BesoinReseau("RH", 10));
        besoins2.add(new BesoinReseau("DIRECTION", 5));
        
        System.out.println("Besoins exprimés par l'utilisateur:");
        for (BesoinReseau besoin : besoins2) {
            besoin.afficher();
        }
        
        ArrayList<ResultatVLSM> resultats2 = moteur.genererPlan("10.0.0.0", besoins2);
        
        System.out.println("\nPlan d'adressage proposé:");
        System.out.println("----------------------------------------");
        for (ResultatVLSM resultat : resultats2) {
            resultat.afficher();
        }
        
        // ========= SCÉNARIO 3: Grande infrastructure =========
        System.out.println("\n\n=== SCÉNARIO 3: GRANDE INFRASTRUCTURE ===\n");
        
        ArrayList<BesoinReseau> besoins3 = new ArrayList<>();
        besoins3.add(new BesoinReseau("DATACENTER", 500));
        besoins3.add(new BesoinReseau("BACKUP", 200));
        besoins3.add(new BesoinReseau("DMZ", 100));
        besoins3.add(new BesoinReseau("DEVELOPPEMENT", 60));
        besoins3.add(new BesoinReseau("TEST", 30));
        besoins3.add(new BesoinReseau("MANAGEMENT", 10));
        
        System.out.println("Besoins exprimés par l'utilisateur:");
        for (BesoinReseau besoin : besoins3) {
            besoin.afficher();
        }
        
        ArrayList<ResultatVLSM> resultats3 = moteur.genererPlan("172.16.0.0", besoins3);
        
        System.out.println("\nPlan d'adressage proposé:");
        System.out.println("----------------------------------------");
        for (ResultatVLSM resultat : resultats3) {
            resultat.afficher();
        }
        
        // ========= Test de la table CIDR =========
        System.out.println("\n\n=== TEST DE LA TABLE CIDR ===\n");
        
        System.out.println("Table de correspondance: Nombre d'hôtes → CIDR");
        System.out.println("----------------------------------------");
        int[] testsHotes = {1, 2, 5, 10, 20, 30, 50, 100, 200, 500, 1000};
        
        for (int nbHotes : testsHotes) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(nbHotes);
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            System.out.printf("%5d hôtes -> /%-3d (capacité: %5d hôtes)%n", 
                              nbHotes, cidr, capacite);
        }
        
        System.out.println("\n=========================================");
        System.out.println("           FIN DES TESTS");
        System.out.println("=========================================");
    }
}
