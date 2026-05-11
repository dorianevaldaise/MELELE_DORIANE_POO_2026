/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.service;

/**
 *
 * @author Mahone
 */

import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ChevauchementReseauException;
import java.util.ArrayList;

public class ValidateurPlanAdressage {
   
    public void verifierAdresses(ArrayList<ResultatVLSM> resultats) 
            throws AdresseIPInvalideException {
        for (ResultatVLSM resultat : resultats) {
            CalculateurReseau.verifierAdresseIP(resultat.getAdresseReseau());
        }
    }
    
    public void verifierChevauchements(ArrayList<ResultatVLSM> resultats) 
            throws ChevauchementReseauException {
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r1 = resultats.get(i);
            for (int j = i + 1; j < resultats.size(); j++) {
                ResultatVLSM r2 = resultats.get(j);
                if (CalculateurReseau.reseauxSeChevauchent(
                        r1.getAdresseReseau(), r1.getCidr(),
                        r2.getAdresseReseau(), r2.getCidr())) {
                    throw new ChevauchementReseauException(
                        "Chevauchement entre " + r1.getNomBesoin() + " et " + r2.getNomBesoin());
                }
            }
        }
    }
    
    public void validerPlanComplet(ArrayList<ResultatVLSM> resultats) 
            throws AdresseIPInvalideException, ChevauchementReseauException {
        verifierAdresses(resultats);
        verifierChevauchements(resultats);
    }
    
    public void afficherValidationReussie() {
        System.out.println("  ✅ Validation terminée : aucun conflit détecté.");
    }  
}
