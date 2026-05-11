/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */
public class RecommendationGrandVLAN implements RegleRecommandation {
   @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null && vlan.getCapacite() > 200) {
            return new Recommandation(
                "VLAN de grande taille",
                "MOYENNE",
                "Le VLAN " + vlan.getNom() + " possède une grande capacité (" + vlan.getCapacite() + " hôtes). Il faut surveiller les broadcasts."
            );
        }
        return null;
    }  
}
