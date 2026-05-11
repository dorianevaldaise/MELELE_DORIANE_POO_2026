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

public class MoteurRecommandation {
   private ArrayList<RegleRecommandation> regles;
    
    public MoteurRecommandation() {
        regles = new ArrayList<>();
    }
    
    public void ajouterRegle(RegleRecommandation regle) {
        regles.add(regle);
        System.out.println("📋 Règle ajoutée : " + regle.getClass().getSimpleName());
    }
    
    public ArrayList<Recommandation> analyserVLANs(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> recommandations = new ArrayList<>();
        
        for (VLAN vlan : vlans) {
            for (RegleRecommandation regle : regles) {
                Recommandation recommandation = regle.analyser(vlan);
                if (recommandation != null) {
                    recommandations.add(recommandation);
                }
            }
        }
        
        return recommandations;
    }
    
    public void afficherRecommandations(ArrayList<Recommandation> recommandations) {
        if (recommandations.isEmpty()) {
            System.out.println("\n✅ Aucune recommandation particulière. Votre plan est conforme aux bonnes pratiques.");
            return;
        }
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           RECOMMANDATIONS");
        System.out.println("═".repeat(60));
        
        // Trier par priorité (ÉLEVÉE d'abord)
        recommandations.sort((r1, r2) -> {
            if (r1.getPriorite().equals("ÉLEVÉE") && !r2.getPriorite().equals("ÉLEVÉE")) return -1;
            if (!r1.getPriorite().equals("ÉLEVÉE") && r2.getPriorite().equals("ÉLEVÉE")) return 1;
            return 0;
        });
        
        for (Recommandation recommandation : recommandations) {
            recommandation.afficher();
        }
    } 
}
