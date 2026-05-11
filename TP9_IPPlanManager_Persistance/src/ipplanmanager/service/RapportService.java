/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.service;

/**
 *
 * @author Mahone
 */
import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RapportService {
  
    public void genererRapportComplet(ArrayList<BesoinReseau> besoins,
                                       ArrayList<ResultatVLSM> resultats,
                                       ArrayList<VLAN> vlans,
                                       ArrayList<Recommandation> recommandations,
                                       String cheminFichier) throws IOException {
        
        FileWriter writer = new FileWriter(cheminFichier);
        
        writer.write("╔══════════════════════════════════════════════════════════════╗\n");
        writer.write("║              RAPPORT TECHNIQUE IPPLAN-MANAGER                 ║\n");
        writer.write("╚══════════════════════════════════════════════════════════════╝\n\n");
        
        // 1. Besoins
        writer.write("┌─────────────────────────────────────────────────────────────────┐\n");
        writer.write("│ 1. BESOINS EXPRIMÉS PAR L'UTILISATEUR                            │\n");
        writer.write("├─────────────────────────────────────────────────────────────────┤\n");
        for (BesoinReseau besoin : besoins) {
            writer.write(String.format("│ • %-20s : %5d hôtes%37s│\n", 
                besoin.getNom(), besoin.getNombreHotes(), ""));
        }
        writer.write("└─────────────────────────────────────────────────────────────────┘\n\n");
        
        // 2. Plan d'adressage
        writer.write("┌─────────────────────────────────────────────────────────────────┐\n");
        writer.write("│ 2. PLAN D'ADRESSAGE VLSM PROPOSÉ                                 │\n");
        writer.write("├─────────────────────────────────────────────────────────────────┤\n");
        for (ResultatVLSM resultat : resultats) {
            writer.write(String.format("│ • %-20s : %-18s/%-3d │ Capacité: %5d hôtes │\n",
                resultat.getNomBesoin(), resultat.getAdresseReseau(), 
                resultat.getCidr(), resultat.getCapacite()));
        }
        writer.write("└─────────────────────────────────────────────────────────────────┘\n\n");
        
        // 3. VLANs
        writer.write("┌─────────────────────────────────────────────────────────────────┐\n");
        writer.write("│ 3. VLANS PROPOSÉS                                                │\n");
        writer.write("├─────────────────────────────────────────────────────────────────┤\n");
        for (VLAN vlan : vlans) {
            String reseau = "";
            if (vlan.getReseauAssocie() != null) {
                reseau = vlan.getReseauAssocie().getAdresseReseau() + "/" + vlan.getReseauAssocie().getCidr();
            }
            writer.write(String.format("│ • VLAN %-3d : %-20s → %-22s │\n",
                vlan.getId(), vlan.getNom(), reseau));
        }
        writer.write("└─────────────────────────────────────────────────────────────────┘\n\n");
        
        // 4. Recommandations
        writer.write("┌─────────────────────────────────────────────────────────────────┐\n");
        writer.write("│ 4. RECOMMANDATIONS TECHNIQUES                                     │\n");
        writer.write("├─────────────────────────────────────────────────────────────────┤\n");
        if (recommandations.isEmpty()) {
            writer.write("│ ✅ Aucune recommandation particulière.                           │\n");
        } else {
            for (Recommandation rec : recommandations) {
                writer.write(String.format("│ [%s] %-50s │\n", rec.getPriorite(), rec.getTitre()));
                writer.write(String.format("│     → %-57s│\n", rec.getMessage()));
            }
        }
        writer.write("└─────────────────────────────────────────────────────────────────┘\n\n");
        
        writer.write("╔══════════════════════════════════════════════════════════════╗\n");
        writer.write("║                     FIN DU RAPPORT                            ║\n");
        writer.write("╚══════════════════════════════════════════════════════════════╝\n");
        
        writer.close();
    }   
}
