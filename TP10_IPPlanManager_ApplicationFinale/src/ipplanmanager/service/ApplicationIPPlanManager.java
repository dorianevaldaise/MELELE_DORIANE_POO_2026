/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.service;

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
import ipplanmanager.console.ConsoleService;
import ipplanmanager.exception.*;
import java.io.IOException;
import java.util.ArrayList;
public class ApplicationIPPlanManager {
  
    private ConsoleService console;
    private MoteurVLSM moteurVLSM;
    private GestionnaireVLAN gestionnaireVLAN;
    private ValidateurPlanAdressage validateur;
    private MoteurRecommandation moteurRecommandation;
    private FichierPlanRepository fichierRepository;
    private BesoinRepository besoinRepository;
    private RapportService rapportService;
    
    public ApplicationIPPlanManager() {
        console = new ConsoleService();
        moteurVLSM = new MoteurVLSM();
        gestionnaireVLAN = new GestionnaireVLAN();
        validateur = new ValidateurPlanAdressage();
        moteurRecommandation = new MoteurRecommandation();
        fichierRepository = new FichierPlanRepository();
        besoinRepository = new BesoinRepository();
        rapportService = new RapportService();
        
        // Initialiser les règles de recommandation
        moteurRecommandation.ajouterRegle(new RecommendationWifiInvite());
        moteurRecommandation.ajouterRegle(new RecommendationServeurs());
        moteurRecommandation.ajouterRegle(new RecommendationGrandVLAN());
        moteurRecommandation.ajouterRegle(new RecommendationAdministration());
        moteurRecommandation.ajouterRegle(new RecommendationMargeAdresse());
    }
    
    public void demarrer() {
        boolean continuer = true;
        
        while (continuer) {
            console.afficherMenu();
            int choix = console.saisirEntier("");
            
            switch (choix) {
                case 1:
                    executerGenerationManuelle();
                    break;
                case 2:
                    executerGenerationDepuisFichier();
                    break;
                case 3:
                    continuer = false;
                    System.out.println("\n  👋 Au revoir !");
                    break;
                default:
                    System.out.println("  ❌ Option invalide. Veuillez choisir 1, 2 ou 3.");
            }
        }
        console.fermer();
    }
    
    private void executerGenerationManuelle() {
        console.afficherEnTete("GÉNÉRATION DE PLAN - SAISIE MANUELLE");
        
        try {
            // Saisie des informations
            String nomProjet = console.saisirTexte("  Nom du projet réseau : ");
            String adresseDepart = console.saisirTexte("  Adresse réseau de départ (ex: 10.10.0.0) : ");
            
            // Valider l'adresse de départ
            CalculateurReseau.verifierAdresseIP(adresseDepart);
            
            // Saisie des besoins
            ArrayList<BesoinReseau> besoins = console.saisirBesoins();
            
            // Génération complète
            executerGenerationComplete(nomProjet, adresseDepart, besoins);
            
        } catch (AdresseIPInvalideException e) {
            System.out.println("\n  ❌ Erreur d'adresse IP : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n  ❌ Erreur inattendue : " + e.getMessage());
        }
        
        console.pause();
    }
    
    private void executerGenerationDepuisFichier() {
        console.afficherEnTete("GÉNÉRATION DE PLAN - DEPUIS FICHIER CSV");
        
        try {
            String nomProjet = console.saisirTexte("  Nom du projet réseau : ");
            String cheminFichier = console.saisirTexte("  Chemin du fichier CSV (ex: exports/besoins.csv) : ");
            
            // Charger les besoins depuis le fichier
            ArrayList<BesoinReseau> besoins = besoinRepository.chargerBesoins(cheminFichier);
            
            System.out.println("\n  📋 Besoins chargés :");
            for (BesoinReseau besoin : besoins) {
                System.out.println("     • " + besoin.getNom() + " : " + besoin.getNombreHotes() + " hôtes");
            }
            
            String adresseDepart = console.saisirTexte("\n  Adresse réseau de départ (ex: 10.10.0.0) : ");
            CalculateurReseau.verifierAdresseIP(adresseDepart);
            
            // Génération complète
            executerGenerationComplete(nomProjet, adresseDepart, besoins);
            
        } catch (AdresseIPInvalideException e) {
            System.out.println("\n  ❌ Erreur d'adresse IP : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("\n  ❌ Erreur de lecture du fichier : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n  ❌ Erreur inattendue : " + e.getMessage());
        }
        
        console.pause();
    }
    
    private void executerGenerationComplete(String nomProjet, String adresseDepart, 
                                             ArrayList<BesoinReseau> besoins) 
            throws AdresseIPInvalideException, ChevauchementReseauException, 
                   ConflitVLANException, IOException {
        
        System.out.println("\n  🔄 Génération du plan en cours...\n");
        
        // 1. Génération du plan VLSM
        ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan(adresseDepart, besoins);
        
        // 2. Validation du plan
        validateur.validerPlanComplet(resultats);
        
        // 3. Création des VLANs
        genererVLANs(resultats);
        
        // 4. Génération des recommandations
        ArrayList<Recommandation> recommandations = moteurRecommandation.analyserVLANs(gestionnaireVLAN.getVlans());
        
        // 5. Affichage des résultats
        afficherResultats(resultats, gestionnaireVLAN.getVlans(), recommandations);
        validateur.afficherValidationReussie();
        
        // 6. Sauvegarde des résultats
        sauvegarderResultats(nomProjet, besoins, resultats, recommandations);
        
        System.out.println("\n  ✅ Traitement terminé avec succès !");
    }
    
    private void genererVLANs(ArrayList<ResultatVLSM> resultats) throws ConflitVLANException {
        int numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats) {
            VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, 
                                "VLAN pour " + resultat.getNomBesoin());
            gestionnaireVLAN.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
    }
    
    private void afficherResultats(ArrayList<ResultatVLSM> resultats, 
                                   ArrayList<VLAN> vlans,
                                   ArrayList<Recommandation> recommandations) {
        
        console.afficherSousTitre("PLAN D'ADRESSAGE PROPOSÉ");
        System.out.println("  ┌─────────────────────────────────────────────────────────────────┐");
        for (ResultatVLSM resultat : resultats) {
            System.out.printf("  │ %-15s -> %-18s/%-3d │ Demandés: %4d │ Capacité: %4d │ Marge: %3d │%n",
                resultat.getNomBesoin(), resultat.getAdresseReseau(), resultat.getCidr(),
                resultat.getHotesDemandes(), resultat.getCapacite(), resultat.getMarge());
        }
        System.out.println("  └─────────────────────────────────────────────────────────────────┘");
        
        console.afficherSousTitre("VLANS GÉNÉRÉS");
        if (vlans.isEmpty()) {
            System.out.println("  Aucun VLAN généré");
        } else {
            System.out.println("  ┌─────────────────────────────────────────────────────────────────┐");
            for (VLAN vlan : vlans) {
                System.out.printf("  │ VLAN %-3d : %-15s → %-18s/%-3d │ Capacité: %4d hôtes │%n",
                    vlan.getId(), vlan.getNom(),
                    vlan.getReseauAssocie().getAdresseReseau(),
                    vlan.getReseauAssocie().getCidr(),
                    vlan.getCapacite());
            }
            System.out.println("  └─────────────────────────────────────────────────────────────────┘");
        }
        
        console.afficherSousTitre("RECOMMANDATIONS");
        moteurRecommandation.afficherRecommandations(recommandations);
    }
    
    private void sauvegarderResultats(String nomProjet,
                                      ArrayList<BesoinReseau> besoins,
                                      ArrayList<ResultatVLSM> resultats,
                                      ArrayList<Recommandation> recommandations) throws IOException {
        
        // Nettoyer le nom du projet pour l'utiliser dans les noms de fichiers
        String nomFichier = nomProjet.replaceAll(" ", "_").replaceAll("[^a-zA-Z0-9_-]", "");
        
        String cheminPlan = "exports/" + nomFichier + "_plan.csv";
        String cheminVlans = "exports/" + nomFichier + "_vlans.csv";
        String cheminRecommandations = "exports/" + nomFichier + "_recommandations.txt";
        String cheminRapport = "exports/" + nomFichier + "_rapport.txt";
        
        fichierRepository.sauvegarderPlanCSV(resultats, cheminPlan);
        fichierRepository.sauvegarderVLANsCSV(gestionnaireVLAN.getVlans(), cheminVlans);
        fichierRepository.sauvegarderRecommandations(recommandations, cheminRecommandations);
        rapportService.genererRapportComplet(nomProjet, besoins, resultats, 
                                             gestionnaireVLAN.getVlans(), 
                                             recommandations, cheminRapport);
        
        System.out.println("\n  📁 Fichiers générés dans le dossier 'exports/' :");
        System.out.println("     • " + cheminPlan);
        System.out.println("     • " + cheminVlans);
        System.out.println("     • " + cheminRecommandations);
        System.out.println("     • " + cheminRapport);
    }  
}
