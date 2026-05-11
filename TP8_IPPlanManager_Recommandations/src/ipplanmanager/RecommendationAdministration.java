/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */
public class RecommendationAdministration implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom().toUpperCase();
        if (nom.contains("ADMIN") || nom.contains("ADMINISTRATION")) {
            return new Recommandation(
                "Sécurisation du VLAN Administration",
                "ÉLEVÉE",
                "Le VLAN " + vlan.getNom() + " doit être accessible uniquement aux administrateurs réseau."
            );
        }
        return null;
    }
}
