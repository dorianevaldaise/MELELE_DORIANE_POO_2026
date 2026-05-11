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
public class RecommendationWifiInvite implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getNom().toUpperCase().contains("WIFI")) {
            return new Recommandation(
                "Isolation du WiFi",
                "ÉLEVÉE",
                "Le VLAN " + vlan.getNom() + " (WiFi) doit être isolé des VLANs internes sensibles."
            );
        }
        return null;
    }
    
}
