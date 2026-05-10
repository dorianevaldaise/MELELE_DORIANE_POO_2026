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
    
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, 
                                                ArrayList<BesoinReseau> besoins) {
        
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        
        // Trier les besoins du plus grand au plus petit
        Collections.sort(besoins, new Comparator<BesoinReseau>() {
            @Override
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });
        
        int adresseCourante = CalculateurReseau.convertirIPEnEntier(adresseDepart);
        
        for (BesoinReseau besoin : besoins) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String adresseReseau = CalculateurReseau.convertirEntierEnIP(adresseCourante);
            
            ResultatVLSM resultat = new ResultatVLSM(besoin.getNom(), adresseReseau, 
                                                      cidr, masque, capacite);
            
            String premiereUtilisable = CalculateurReseau.calculerPremiereAdresseUtilisable(adresseReseau);
            String derniereUtilisable = CalculateurReseau.calculerDerniereAdresseUtilisable(adresseReseau, cidr);
            resultat.setPremiereAdresseUtilisable(premiereUtilisable);
            resultat.setDerniereAdresseUtilisable(derniereUtilisable);
            
            resultats.add(resultat);
            
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            adresseCourante = adresseCourante + tailleBloc;
        }
        
        return resultats;
    }
    
    // TRAVAIL SUPPLÉMENTAIRE: Vérifier si le réseau de départ est suffisant
    public boolean verifierCapaciteSuffisante(String adresseDepart, int cidrDepart,
                                               ArrayList<BesoinReseau> besoins) throws ReseauInsuffisantException {
        
        int tailleBlocDepart = CalculateurReseau.calculerTailleBloc(cidrDepart);
        int capaciteTotale = tailleBlocDepart - 2;
        
        int besoinsTotaux = 0;
        for (BesoinReseau besoin : besoins) {
            besoinsTotaux += besoin.getNombreHotes();
        }
        
        if (besoinsTotaux > capaciteTotale) {
            throw new ReseauInsuffisantException(
                "Réseau " + adresseDepart + "/" + cidrDepart + 
                " insuffisant: besoin de " + besoinsTotaux + 
                " hôtes mais capacité de " + capaciteTotale
            );
        }
        
        // Vérification plus précise avec placement VLSM
        ArrayList<ResultatVLSM> resultats = genererPlan(adresseDepart, besoins);
        int adresseFin = CalculateurReseau.calculerAdresseFin(adresseDepart, cidrDepart);
        int derniereAdresseUtilisee = 0;
        
        for (ResultatVLSM r : resultats) {
            int debut = CalculateurReseau.convertirIPEnEntier(r.getAdresseReseau());
            int tailleBloc = CalculateurReseau.calculerTailleBloc(r.getCidr());
            if (debut + tailleBloc - 1 > derniereAdresseUtilisee) {
                derniereAdresseUtilisee = debut + tailleBloc - 1;
            }
        }
        
        if (derniereAdresseUtilisee > adresseFin) {
            throw new ReseauInsuffisantException(
                "Le plan d'adressage dépasse la capacité du réseau " + adresseDepart + "/" + cidrDepart
            );
        }
        
        return true;
    }  
}
