/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.main;

/**
 *
 * @author Mahone
 */

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import ipplanmanager.repository.BesoinRepository;
import ipplanmanager.repository.FichierPlanRepository;
import ipplanmanager.service.*;
import ipplanmanager.exception.ConflitVLANException;
import java.io.IOException;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("     IPPLAN-MANAGER - PERSISTANCE ET ORGANISATION");
        System.out.println("═══════════════════════════════════════════════════════════════\n");
        
        // Définition des chemins de fichiers
        String fichierBesoins = "exports/besoins.csv";
        String fichierPlan = "exports/plan_adressage.csv";
        String fichierVlans = "exports/vlans.csv";
        String fichierRecommandations = "exports/recommandations.txt";
        String fichierRapport = "exports/rapport_complet.txt";
        
        // Fichier PME (Travail demandé)
        String fichierBesoinsPME = "exports/besoins_pme.csv";
        String fichierRapportPME = "exports/rapport_pme.txt";
        
        BesoinRepository besoinRepository = new BesoinRepository();
        FichierPlanRepository fichierPlanRepository = new FichierPlanRepository();
        RapportService rapportService = new RapportService();
        
        try {
            // ========= SCÉNARIO 1: Université =========
            System.out.println("📌 SCÉNARIO 1: Université");
            System.out.println("─".repeat(50));
            
            ArrayList<BesoinReseau> besoins = besoinRepository.chargerBesoins(fichierBesoins);
            
            System.out.println("Besoins chargés depuis " + fichierBesoins + ":");
            for (BesoinReseau besoin : besoins) {
                System.out.println("  • " + besoin.getNom() + " : " + besoin.getNombreHotes() + " hôtes");
            }
            
            MoteurVLSM moteurVLSM = new MoteurVLSM();
            ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan("10.10.0.0", besoins);
            
            GestionnaireVLAN gestionnaireVLAN = new GestionnaireVLAN();
            int numeroVLAN = 10;
            for (ResultatVLSM resultat : resultats) {
                VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, 
                                    "VLAN " + resultat.getNomBesoin());
                gestionnaireVLAN.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
            
            MoteurRecommandation moteurRecommandation = new MoteurRecommandation();
            moteurRecommandation.ajouterRegle(new RecommendationWifiInvite());
            moteurRecommandation.ajouterRegle(new RecommendationServeurs());
            moteurRecommandation.ajouterRegle(new RecommendationGrandVLAN());
            moteurRecommandation.ajouterRegle(new RecommendationAdministration());
            moteurRecommandation.ajouterRegle(new RecommendationMargeAdresse());
            
            ArrayList<Recommandation> recommandations = moteurRecommandation.analyserVLANs(gestionnaireVLAN.getVlans());
            
            // Sauvegarde des fichiers
            fichierPlanRepository.sauvegarderPlanCSV(resultats, fichierPlan);
            fichierPlanRepository.sauvegarderVLANsCSV(gestionnaireVLAN.getVlans(), fichierVlans);
            fichierPlanRepository.sauvegarderRecommandations(recommandations, fichierRecommandations);
            rapportService.genererRapportComplet(besoins, resultats, gestionnaireVLAN.getVlans(), 
                                                 recommandations, fichierRapport);
            
            System.out.println("\n✅ Fichiers générés pour le scénario Université:");
            System.out.println("   📄 " + fichierPlan);
            System.out.println("   📄 " + fichierVlans);
            System.out.println("   📄 " + fichierRecommandations);
            System.out.println("   📄 " + fichierRapport);
            
            // ========= SCÉNARIO 2: PME (Travail demandé) =========
            System.out.println("\n\n📌 SCÉNARIO 2: PME (Petite et Moyenne Entreprise)");
            System.out.println("─".repeat(50));
            
            ArrayList<BesoinReseau> besoinsPME = besoinRepository.chargerBesoins(fichierBesoinsPME);
            
            System.out.println("Besoins chargés depuis " + fichierBesoinsPME + ":");
            for (BesoinReseau besoin : besoinsPME) {
                System.out.println("  • " + besoin.getNom() + " : " + besoin.getNombreHotes() + " hôtes");
            }
            
            ArrayList<ResultatVLSM> resultatsPME = moteurVLSM.genererPlan("192.168.1.0", besoinsPME);
            
            GestionnaireVLAN gestionnaireVLANPME = new GestionnaireVLAN();
            int numeroVLANPME = 10;
            for (ResultatVLSM resultat : resultatsPME) {
                VLAN vlan = new VLAN(numeroVLANPME, resultat.getNomBesoin(), resultat, 
                                    "VLAN " + resultat.getNomBesoin());
                gestionnaireVLANPME.ajouterVLAN(vlan);
                numeroVLANPME += 10;
            }
            
            ArrayList<Recommandation> recommandationsPME = moteurRecommandation.analyserVLANs(gestionnaireVLANPME.getVlans());
            
            rapportService.genererRapportComplet(besoinsPME, resultatsPME, gestionnaireVLANPME.getVlans(), 
                                                 recommandationsPME, fichierRapportPME);
            
            System.out.println("\n✅ Fichiers générés pour le scénario PME:");
            System.out.println("   📄 " + fichierRapportPME);
            
            System.out.println("\n" + "═".repeat(60));
            System.out.println("              TRAITEMENT TERMINÉ AVEC SUCCÈS");
            System.out.println("═".repeat(60));
            System.out.println("📁 Tous les fichiers ont été générés dans le dossier 'exports/'");
            
        } catch (IOException e) {
            System.out.println("❌ Erreur de fichier : " + e.getMessage());
            e.printStackTrace();
        } catch (ConflitVLANException e) {
            System.out.println("❌ Erreur VLAN : " + e.getMessage());
        }
    }  
}
