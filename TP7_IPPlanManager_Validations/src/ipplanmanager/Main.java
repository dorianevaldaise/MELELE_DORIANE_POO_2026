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
        System.out.println("     IPPLAN-MANAGER - VALIDATIONS AVANCÉES");
        System.out.println("═".repeat(60));
        
        // ========= TEST 1: Cas normal =========
        testCasNormal();
        
        // ========= TEST 2: Cas avec conflit VLAN =========
        testConflitVLAN();
        
        // ========= TEST 3: Adresse de départ invalide (Travail demandé) =========
        testAdresseInvalide();
        
        // ========= TEST 4: Chevauchement de réseaux (Travail demandé) =========
        testChevauchement();
        
        // ========= TEST 5: Réseau insuffisant (Travail supplémentaire) =========
        testReseauInsuffisant();
    }
  
    // TEST 1: Cas normal - tout est valide
    public static void testCasNormal() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        TEST 1: CAS NORMAL - PLAN VALIDE");
        System.out.println("█".repeat(60));
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        
        System.out.println("\n BESOINS EXPRIMÉS :");
        System.out.println("─".repeat(40));
        for (BesoinReseau besoin : besoins) {
            besoin.afficher();
        }
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);
        
        System.out.println("\n PLAN GÉNÉRÉ :");
        System.out.println("─".repeat(40));
        for (ResultatVLSM resultat : resultats) {
            resultat.afficher();
        }
        
        ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();
        
        try {
            System.out.println("\n VALIDATION DU PLAN :");
            System.out.println("─".repeat(40));
            validateur.validerPlanComplet(resultats);
            validateur.afficherValidationReussie();
        } catch (AdresseIPInvalideException | ChevauchementReseauException e) {
            System.out.println(" Erreur de validation : " + e.getMessage());
        }
    }
    
    // TEST 2: Conflit VLAN (deux VLANs avec même ID)
    public static void testConflitVLAN() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        TEST 2: CONFLIT VLAN");
        System.out.println("█".repeat(60));
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);
        
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        
        System.out.println("\n TENTATIVE D'AJOUT DE VLANS :");
        System.out.println("─".repeat(40));
        
        try {
            VLAN vlan1 = new VLAN(10, "ADMINISTRATION", resultats.get(0), "VLAN Administration");
            VLAN vlan2 = new VLAN(20, "TECHNIQUE", resultats.get(1), "VLAN Technique");
            VLAN vlan3 = new VLAN(10, "WIFI", resultats.get(2), "VLAN WiFi (ID déjà utilisé)");
            
            gestionnaire.ajouterVLAN(vlan1);
            gestionnaire.ajouterVLAN(vlan2);
            gestionnaire.ajouterVLAN(vlan3); // Devrait générer une exception
            
            gestionnaire.afficherTousLesVLANs();
            
        } catch (ConflitVLANException e) {
            System.out.println("\n ERREUR DÉTECTÉE : " + e.getMessage());
        }
    }
    
    // TEST 3: Adresse de départ invalide (Travail demandé)
    public static void testAdresseInvalide() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        TEST 3: ADRESSE DE DÉPART INVALIDE");
        System.out.println("█".repeat(60));
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("TEST", 10));
        
        // Adresse invalide avec octet > 255
        String adresseInvalide = "192.168.300.0";
        
        System.out.println("\n ADRESSE DE DÉPART : " + adresseInvalide);
        
        try {
            // Vérification de l'adresse avant de générer le plan
            CalculateurReseau.verifierAdresseIP(adresseInvalide);
            
            MoteurVLSM moteur = new MoteurVLSM();
            ArrayList<ResultatVLSM> resultats = moteur.genererPlan(adresseInvalide, besoins);
            
        } catch (AdresseIPInvalideException e) {
            System.out.println("\n ERREUR DÉTECTÉE : " + e.getMessage());
            System.out.println(" L'application a bien détecté l'adresse IP invalide !");
        }
        
        // Test avec une adresse bien formée mais avec un octet invalide
        System.out.println("\n TEST AVEC '256.168.1.0' :");
        try {
            CalculateurReseau.verifierAdresseIP("256.168.1.0");
        } catch (AdresseIPInvalideException e) {
            System.out.println(" ERREUR DÉTECTÉE : " + e.getMessage());
        }
        
        // Test avec une adresse avec trop de parties
        System.out.println("\n TEST AVEC '192.168.1.0.1' :");
        try {
            CalculateurReseau.verifierAdresseIP("192.168.1.0.1");
        } catch (AdresseIPInvalideException e) {
            System.out.println(" ERREUR DÉTECTÉE : " + e.getMessage());
        }
    }
    
    // TEST 4: Chevauchement de réseaux (Travail demandé)
    public static void testChevauchement() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        TEST 4: CHEVAUCHEMENT DE RÉSEAUX");
        System.out.println("█".repeat(60));
        
        // Création de deux résultats qui se chevauchent volontairement
        // 192.168.1.0/25 couvre les adresses 192.168.1.0 à 192.168.1.127
        // 192.168.1.64/26 couvre les adresses 192.168.1.64 à 192.168.1.127
        // Ces deux réseaux se chevauchent
        
        ResultatVLSM r1 = new ResultatVLSM("RESEAU_A", "192.168.1.0", 25, "255.255.255.128", 126);
        ResultatVLSM r2 = new ResultatVLSM("RESEAU_B", "192.168.1.64", 26, "255.255.255.192", 62);
        
        // Configuration des plages pour l'affichage
        r1.setPremiereAdresseUtilisable("192.168.1.1");
        r1.setDerniereAdresseUtilisable("192.168.1.126");
        r2.setPremiereAdresseUtilisable("192.168.1.65");
        r2.setDerniereAdresseUtilisable("192.168.1.126");
        
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        resultats.add(r1);
        resultats.add(r2);
        
        System.out.println("\n RÉSEAUX TESTÉS :");
        System.out.println("─".repeat(40));
        r1.afficher();
        System.out.println();
        r2.afficher();
        
        System.out.println("\n DÉTECTION DE CHEVAUCHEMENT :");
        System.out.println("─".repeat(40));
        
        ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();
        
        try {
            validateur.validerPlanComplet(resultats);
            validateur.afficherValidationReussie();
        } catch (AdresseIPInvalideException | ChevauchementReseauException e) {
            System.out.println(" ERREUR DÉTECTÉE : " + e.getMessage());
            System.out.println(" Le chevauchement a bien été détecté !");
        }
        
        // Test avec des réseaux non chevauchants
        System.out.println("\n TEST RÉSEAUX NON CHEVAUCHANTS :");
        ResultatVLSM r3 = new ResultatVLSM("RESEAU_C", "192.168.1.0", 26, "255.255.255.192", 62);
        ResultatVLSM r4 = new ResultatVLSM("RESEAU_D", "192.168.1.64", 26, "255.255.255.192", 62);
        
        r3.setPremiereAdresseUtilisable("192.168.1.1");
        r3.setDerniereAdresseUtilisable("192.168.1.62");
        r4.setPremiereAdresseUtilisable("192.168.1.65");
        r4.setDerniereAdresseUtilisable("192.168.1.126");
        
        ArrayList<ResultatVLSM> resultats2 = new ArrayList<>();
        resultats2.add(r3);
        resultats2.add(r4);
        
        try {
            validateur.validerPlanComplet(resultats2);
            System.out.println(" Plan valide - aucun chevauchement détecté");
            validateur.afficherValidationReussie();
        } catch (AdresseIPInvalideException | ChevauchementReseauException e) {
            System.out.println(" ERREUR : " + e.getMessage());
        }
    }
    
    // TEST 5: Réseau insuffisant (Travail supplémentaire)
    public static void testReseauInsuffisant() {
        System.out.println("\n\n" + "█".repeat(60));
        System.out.println("        TEST 5: RÉSEAU INSUFFISANT");
        System.out.println("█".repeat(60));
        
        // Besoins très grands pour un petit réseau de départ
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("SERVICE1", 500));
        besoins.add(new BesoinReseau("SERVICE2", 300));
        besoins.add(new BesoinReseau("SERVICE3", 200));
        
        String adresseDepart = "192.168.1.0";
        
        System.out.println("\n RÉSEAU DE DÉPART : " + adresseDepart + "/24");
        System.out.println(" BESOINS (très grands) :");
        for (BesoinReseau besoin : besoins) {
            besoin.afficher();
        }
        
        // Calcul de la capacité nécessaire
        int capaciteNecessaire = 0;
        for (BesoinReseau besoin : besoins) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            capaciteNecessaire += CalculateurReseau.calculerTailleBloc(cidr);
        }
        
        System.out.println("\n Capacité nécessaire : " + capaciteNecessaire + " adresses");
        System.out.println(" Capacité d'un /24 : 256 adresses");
        
        if (capaciteNecessaire > 256) {
            System.out.println("\n️ ATTENTION : Besoin supérieur à la capacité disponible !");
            System.out.println(" L'application devrait déclencher une ReseauInsuffisantException");
        }
        
        // Tester la génération du plan (peut planter ou donner des résultats incorrects)
        MoteurVLSM moteur = new MoteurVLSM();
        try {
            ArrayList<ResultatVLSM> resultats = moteur.genererPlan(adresseDepart, besoins);
            System.out.println("\n️ Attention : Le plan a été généré mais peut contenir des erreurs !");
            for (ResultatVLSM r : resultats) {
                r.afficher();
            }
        } catch (Exception e) {
            System.out.println("\n ERREUR : " + e.getMessage());
        }
    }
}
