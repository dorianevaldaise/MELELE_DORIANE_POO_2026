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
            
            ResultatVLSM resultat = new ResultatVLSM(
                besoin.getNom(), 
                besoin.getNombreHotes(),
                adresseReseau, 
                cidr, 
                masque, 
                capacite
            );
            
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
}
