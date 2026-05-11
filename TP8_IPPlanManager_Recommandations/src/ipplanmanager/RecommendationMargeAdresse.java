/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */

public class RecommendationMargeAdresse implements RegleRecommandation {
   
    private static final int MARGE_MINIMALE_RECOMMANDEE = 10;
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null) {
            int hotesDemandes = vlan.getReseauAssocie().getNombreHotesDemandes();
            int capacite = vlan.getReseauAssocie().getCapacite();
            int marge = capacite - hotesDemandes;
            
            if (marge < MARGE_MINIMALE_RECOMMANDEE && marge >= 0) {
                return new Recommandation(
                    "Marge d'adresses insuffisante",
                    "MOYENNE",
                    "Le sous-réseau " + vlan.getNom() + " a une marge de seulement " + marge +
                    " adresses. Prévoir une marge plus confortable pour l'évolution future."
                );
            }
        }
        return null;
    }
}
