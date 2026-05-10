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
        
        System.out.println("═".repeat(60));
        System.out.println("     IPPLAN-MANAGER - GESTION DES VLANS");
        System.out.println("═".repeat(60));
        
        // ========= SCÉNARIO 1: Entreprise standard =========
        testScenario1();
        
        // ========= SCÉNARIO 2: Université (Travail demandé) =========
        testScenarioUniversite();
        
        // ========= SCÉNARIO 3: Petite entreprise =========
        testScenarioPetiteEntreprise();
    }
    
    // SCÉNARIO 1: Entreprise standard
    public static void testScenario1() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        SCÉNARIO 1: ENTREPRISE STANDARD");
        System.out.println("█".repeat(60));
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        besoins.add(new BesoinReseau("DIRECTION", 10));
        
        System.out.println("\n📋 BESOINS EXPRIMÉS :");
        System.out.println("─".repeat(40));
        for (BesoinReseau besoin : besoins) {
            besoin.afficher();
        }
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);
        
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        int numeroVLAN = 10;
        
        for (ResultatVLSM resultat : resultats) {
            VLAN vlan = new VLAN(
                numeroVLAN,
                resultat.getNomBesoin(),
                resultat,
                "VLAN dédié au service " + resultat.getNomBesoin()
            );
            gestionnaire.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
        
        gestionnaire.afficherTousLesVLANs();
        gestionnaire.afficherVLANsCritiques();
        gestionnaire.afficherVLANMaxCapacite();
        gestionnaire.afficherStatistiques();
        
        // Test de recherche de VLAN
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           TEST DE RECHERCHE VLAN");
        System.out.println("═".repeat(60));
        VLAN vlanTrouve = gestionnaire.rechercherVLAN(20);
        if (vlanTrouve != null) {
            System.out.println("✅ VLAN trouvé :");
            vlanTrouve.afficher();
        } else {
            System.out.println("❌ VLAN non trouvé");
        }
    }
    
    // SCÉNARIO 2: Université (Travail demandé)
    // ETUDIANTS: 500 hôtes, ENSEIGNANTS: 120 hôtes, 
    // LABORATOIRES: 60 hôtes, WIFI PUBLIC: 200 hôtes, SERVEURS: 30 hôtes
    public static void testScenarioUniversite() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        SCÉNARIO 2: UNIVERSITÉ");
        System.out.println("█".repeat(60));
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ETUDIANTS", 500));
        besoins.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoins.add(new BesoinReseau("LABORATOIRES", 60));
        besoins.add(new BesoinReseau("WIFI_PUBLIC", 200));
        besoins.add(new BesoinReseau("SERVEURS", 30));
        
        System.out.println("\n📋 BESOINS EXPRIMÉS :");
        System.out.println("─".repeat(40));
        for (BesoinReseau besoin : besoins) {
            besoin.afficher();
        }
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("10.0.0.0", besoins);
        
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        int numeroVLAN = 100;
        
        for (ResultatVLSM resultat : resultats) {
            VLAN vlan = new VLAN(
                numeroVLAN,
                resultat.getNomBesoin(),
                resultat,
                "VLAN pour " + resultat.getNomBesoin() + " de l'université"
            );
            gestionnaire.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
        
        gestionnaire.afficherTousLesVLANs();
        gestionnaire.afficherVLANsCritiques();
        gestionnaire.afficherVLANMaxCapacite();
        gestionnaire.afficherStatistiques();
    }
    
    // SCÉNARIO 3: Petite entreprise
    public static void testScenarioPetiteEntreprise() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        SCÉNARIO 3: PETITE ENTREPRISE");
        System.out.println("█".repeat(60));
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("COMMERCIAL", 30));
        besoins.add(new BesoinReseau("COMPTABILITE", 15));
        besoins.add(new BesoinReseau("RH", 10));
        besoins.add(new BesoinReseau("DIRECTION", 5));
        
        System.out.println("\n📋 BESOINS EXPRIMÉS :");
        System.out.println("─".repeat(40));
        for (BesoinReseau besoin : besoins) {
            besoin.afficher();
        }
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("172.16.0.0", besoins);
        
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        int numeroVLAN = 200;
        
        for (ResultatVLSM resultat : resultats) {
            VLAN vlan = new VLAN(
                numeroVLAN,
                resultat.getNomBesoin(),
                resultat,
                "Service " + resultat.getNomBesoin()
            );
            gestionnaire.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
        
        gestionnaire.afficherTousLesVLANs();
        gestionnaire.afficherVLANsCritiques();
        gestionnaire.afficherVLANMaxCapacite();
        gestionnaire.afficherStatistiques();
    }   
}
