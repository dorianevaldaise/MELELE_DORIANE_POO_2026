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

/**
 * Classe de service métier chargée de valider la cohérence d'un plan d'adressage
 */
public class ValidateurPlanAdressage {
    /**
     * Vérifie que toutes les adresses IP du plan sont valides
     */
    public void verifierAdresses(ArrayList<ResultatVLSM> resultats) 
            throws AdresseIPInvalideException {
        for (ResultatVLSM resultat : resultats) {
            CalculateurReseau.verifierAdresseIP(resultat.getAdresseReseau());
        }
    }
    
    /**
     * Vérifie les chevauchements entre sous-réseaux
     */
    public void verifierChevauchements(ArrayList<ResultatVLSM> resultats) 
            throws ChevauchementReseauException {
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r1 = resultats.get(i);
            for (int j = i + 1; j < resultats.size(); j++) {
                ResultatVLSM r2 = resultats.get(j);
                boolean conflit = CalculateurReseau.reseauxSeChevauchent(
                    r1.getAdresseReseau(), r1.getCidr(),
                    r2.getAdresseReseau(), r2.getCidr()
                );
                if (conflit) {
                    throw new ChevauchementReseauException(
                        "Chevauchement détecté entre " + r1.getNomBesoin() + 
                        " (" + r1.getAdresseReseau() + "/" + r1.getCidr() + 
                        ") et " + r2.getNomBesoin() + 
                        " (" + r2.getAdresseReseau() + "/" + r2.getCidr() + ")"
                    );
                }
            }
        }
    }
    
    /**
     * Vérifie la capacité du réseau de départ (Travail supplémentaire)
     */
    public void verifierCapacite(String adresseDepart, ArrayList<BesoinReseau> besoins) 
            throws ReseauInsuffisantException {
        
        // Calculer la capacité totale nécessaire
        int capaciteTotaleDemandee = 0;
        for (BesoinReseau besoin : besoins) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            capaciteTotaleDemandee += tailleBloc;
        }
        
        // Déterminer le CIDR par défaut (24) ou extraire de l'adresse
        int cidrDepart = 24; // Par défaut /24
        // Vérifier si l'adresse contient un CIDR
        if (adresseDepart.contains("/")) {
            String[] parties = adresseDepart.split("/");
            cidrDepart = Integer.parseInt(parties[1]);
        }
        
        int capaciteDisponible = CalculateurReseau.calculerTailleBloc(cidrDepart);
        
        if (capaciteTotaleDemandee > capaciteDisponible) {
            throw new ReseauInsuffisantException(
                "Capacité insuffisante ! Besoin: " + capaciteTotaleDemandee + 
                " adresses, Disponible: " + capaciteDisponible + " adresses"
            );
        }
    }
    
    /**
     * Valide complètement un plan d'adressage
     */
    public void validerPlanComplet(ArrayList<ResultatVLSM> resultats) 
            throws AdresseIPInvalideException, ChevauchementReseauException {
        
        verifierAdresses(resultats);
        verifierChevauchements(resultats);
    }
    
    /**
     * Affiche un message de validation réussie
     */
    public void afficherValidationReussie() {
        System.out.println("✅ Validation terminée : aucun conflit critique détecté.");
    }
}
