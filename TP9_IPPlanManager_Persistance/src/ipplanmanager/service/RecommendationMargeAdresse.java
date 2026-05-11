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

public class RecommendationMargeAdresse implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null) {
            int marge = vlan.getCapacite() - vlan.getNombreHotesDemandes();
            if (marge < 10 && marge >= 0) {
                return new Recommandation(
                    "Marge d'adresses insuffisante",
                    "MOYENNE",
                    "Le VLAN " + vlan.getNom() + " a une marge de seulement " + marge + " adresses."
                );
            }
        }
        return null;
    }
    
}
