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
        System.out.println("     IPPLAN-MANAGER - MOTEUR DE RECOMMANDATIONS");
        System.out.println("═".repeat(60));
        
        // ========= SCÉNARIO 1: Université avec WiFi, Serveurs =========
        testScenarioUniversite();
        
        // ========= SCÉNARIO 2: Scénario avec Administration (Travail demandé) =========
        testScenarioAdministration();
        
        // ========= SCÉNARIO 3: Petite entreprise =========
        testScenarioPetiteEntreprise();
    }
    
    // SCÉNARIO 1: Université
    public static void testScenarioUniversite() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        SCÉNARIO 1: UNIVERSITÉ");
        System.out.println("█".repeat(60));
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ETUDIANTS", 500));
        besoins.add(new BesoinReseau("WIFI_INVITES", 200));
        besoins.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoins.add(new BesoinReseau("LABORATOIRES", 60));
        besoins.add(new BesoinReseau("SERVEURS", 30));
        
        afficherPlanEtRecommandations(besoins, "10.10.0.0", 100);
    }
    
    // SCÉNARIO 2: Administration (Travail demandé)
    public static void testScenarioAdministration() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        SCÉNARIO 2: ENTREPRISE AVEC ADMINISTRATION");
        System.out.println("█".repeat(60));
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("WIFI_INVITES", 120));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        besoins.add(new BesoinReseau("CAMERAS", 80));
        besoins.add(new BesoinReseau("VOIP", 60));
        
        afficherPlanEtRecommandations(besoins, "192.168.1.0", 200);
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
        
        afficherPlanEtRecommandations(besoins, "172.16.0.0", 300);
    }
    
    // Méthode commune pour générer le plan, créer les VLANs et afficher les recommandations
    public static void afficherPlanEtRecommandations(ArrayList<BesoinReseau> besoins, 
                                                      String adresseDepart, 
                                                      int vlanIdDepart) {
        System.out.println("\n📋 BESOINS EXPRIMÉS :");
        System.out.println("────────────────────────────────────────");
        for (BesoinReseau besoin : besoins) {
            besoin.afficher();
        }
        
        MoteurVLSM moteurVLSM = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan(adresseDepart, besoins);
        
        GestionnaireVLAN gestionnaireVLAN = new GestionnaireVLAN();
        int numeroVLAN = vlanIdDepart;
        
        System.out.println("\n✅ CRÉATION DES VLANS :");
        System.out.println("────────────────────────────────────────");
        for (ResultatVLSM resultat : resultats) {
            try {
                VLAN vlan = new VLAN(
                    numeroVLAN,
                    resultat.getNomBesoin(),
                    resultat,
                    "VLAN " + resultat.getNomBesoin()
                );
                gestionnaireVLAN.ajouterVLAN(vlan);
                numeroVLAN += 10;
            } catch (ConflitVLANException e) {
                System.out.println("Erreur VLAN : " + e.getMessage());
            }
        }
        
        gestionnaireVLAN.afficherTousLesVLANs();
        
        // Configuration du moteur de recommandations
        MoteurRecommandation moteurRecommandation = new MoteurRecommandation();
        moteurRecommandation.ajouterRegle(new RecommendationWifiInvite());
        moteurRecommandation.ajouterRegle(new RecommendationServeurs());
        moteurRecommandation.ajouterRegle(new RecommendationGrandVLAN());
        moteurRecommandation.ajouterRegle(new RecommendationAdministration());
        moteurRecommandation.ajouterRegle(new RecommendationMargeAdresse());
        
        // Génération des recommandations
        ArrayList<Recommandation> recommandations = moteurRecommandation.analyserVLANs(gestionnaireVLAN.getVlans());
        moteurRecommandation.afficherRecommandations(recommandations);
        
        // Statistiques
        gestionnaireVLAN.afficherStatistiques();
    }  
}
