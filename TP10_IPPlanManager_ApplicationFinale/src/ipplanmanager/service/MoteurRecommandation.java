/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.service;

/**
 *
 * @author Mahone
 */

import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import java.util.ArrayList;

public class MoteurRecommandation {
    private ArrayList<RegleRecommandation> regles;
    
    public MoteurRecommandation() {
        regles = new ArrayList<>();
    }
    
    public void ajouterRegle(RegleRecommandation regle) {
        regles.add(regle);
    }
    
    public ArrayList<Recommandation> analyserVLANs(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> recommandations = new ArrayList<>();
        for (VLAN vlan : vlans) {
            for (RegleRecommandation regle : regles) {
                Recommandation rec = regle.analyser(vlan);
                if (rec != null) recommandations.add(rec);
            }
        }
        return recommandations;
    }
    
    public void afficherRecommandations(ArrayList<Recommandation> recommandations) {
        if (recommandations.isEmpty()) {
            System.out.println("  ✅ Aucune recommandation particulière.");
        } else {
            System.out.println("  ┌─────────────────────────────────────────────────────────────────┐");
            for (Recommandation rec : recommandations) {
                System.out.print("  │ ");
                rec.afficher();
            }
            System.out.println("  └─────────────────────────────────────────────────────────────────┘");
        }
    }
    
}
