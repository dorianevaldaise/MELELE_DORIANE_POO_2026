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
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {
   
    /**
     * Génère un plan d'adressage VLSM à partir d'une adresse de départ
     * et d'une liste de besoins.
     * 
     * @param adresseDepart Adresse réseau de départ (ex: "192.168.1.0")
     * @param besoins Liste des besoins réseau
     * @return Liste des résultats VLSM
     */
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, 
                                                ArrayList<BesoinReseau> besoins) {
        
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        
        // Trier les besoins du plus grand au plus petit nombre d'hôtes
        Collections.sort(besoins, new Comparator<BesoinReseau>() {
            @Override
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });
        
        // Convertir l'adresse de départ en entier pour les calculs
        int adresseCourante = CalculateurReseau.convertirIPEnEntier(adresseDepart);
        
        // Traitement de chaque besoin
        for (BesoinReseau besoin : besoins) {
            // Trouver le CIDR adapté au besoin
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String adresseReseau = CalculateurReseau.convertirEntierEnIP(adresseCourante);
            
            // Créer le résultat
            ResultatVLSM resultat = new ResultatVLSM(besoin.getNom(), adresseReseau, 
                                                      cidr, masque, capacite);
            
            // Calculer les adresses utilisables (Travail supplémentaire)
            String premiereUtilisable = CalculateurReseau.calculerPremiereAdresseUtilisable(adresseReseau);
            String derniereUtilisable = CalculateurReseau.calculerDerniereAdresseUtilisable(adresseReseau, cidr);
            resultat.setPremiereAdresseUtilisable(premiereUtilisable);
            resultat.setDerniereAdresseUtilisable(derniereUtilisable);
            
            resultats.add(resultat);
            
            // Calculer la prochaine adresse disponible
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            adresseCourante = adresseCourante + tailleBloc;
        }
        
        return resultats;
    }  
}
