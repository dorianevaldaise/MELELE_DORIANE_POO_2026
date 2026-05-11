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

public class RecommendationGrandVLAN implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null && vlan.getCapacite() > 200) {
            return new Recommandation(
                "VLAN de grande taille",
                "MOYENNE",
                "Le VLAN " + vlan.getNom() + " a une capacité de " + vlan.getCapacite() + " hôtes. Surveiller les broadcasts."
            );
        }
        return null;
    }
    
}
