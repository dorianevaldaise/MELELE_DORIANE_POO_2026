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

public class RecommendationServeurs implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getNom().toUpperCase().contains("SERVEUR")) {
            return new Recommandation(
                "Protection du VLAN Serveurs",
                "ÉLEVÉE",
                "Le VLAN " + vlan.getNom() + " doit être protégé par des ACL et surveillé."
            );
        }
        return null;
    }
    
}
