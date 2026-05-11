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
    
    public void genererRapportComplet(String nomProjet,
                                       ArrayList<BesoinReseau> besoins,
                                       ArrayList<ResultatVLSM> resultats,
                                       ArrayList<VLAN> vlans,
                                       ArrayList<Recommandation> recommandations,
                                       String cheminFichier) throws IOException {
        
        FileWriter writer = new FileWriter(cheminFichier);
        
        writer.write("╔════════════════════════════════════════════════════════════════════════════════╗\n");
        writer.write("║                         RAPPORT TECHNIQUE IPPLAN-MANAGER                        ║\n");
        writer.write("╚════════════════════════════════════════════════════════════════════════════════╝\n\n");
        
        writer.write("Projet: " + nomProjet + "\n\n");
        
        // 1. Besoins
        writer.write("┌─────────────────────────────────────────────────────────────────────────────────┐\n");
        writer.write("│ 1. BESOINS EXPRIMÉS PAR L'UTILISATEUR                                            │\n");
        writer.write("├─────────────────────────────────────────────────────────────────────────────────┤\n");
        for (BesoinReseau besoin : besoins) {
            writer.write(String.format("│ • %-20s : %5d hôtes%46s│\n", 
                besoin.getNom(), besoin.getNombreHotes(), ""));
        }
        writer.write("└─────────────────────────────────────────────────────────────────────────────────┘\n\n");
        
        // 2. Plan d'adressage
        writer.write("┌─────────────────────────────────────────────────────────────────────────────────┐\n");
        writer.write("│ 2. PLAN D'ADRESSAGE VLSM PROPOSÉ                                                 │\n");
        writer.write("├─────────────────────────────────────────────────────────────────────────────────┤\n");
        for (ResultatVLSM resultat : resultats) {
            writer.write(String.format("│ • %-15s : %-18s/%-3d │ Demandés: %4d │ Capacité: %4d │ Marge: %3d │\n",
                resultat.getNomBesoin(), resultat.getAdresseReseau(), resultat.getCidr(),
                resultat.getHotesDemandes(), resultat.getCapacite(), resultat.getMarge()));
        }
        writer.write("└─────────────────────────────────────────────────────────────────────────────────┘\n\n");
        
        // 3. VLANs
        writer.write("┌─────────────────────────────────────────────────────────────────────────────────┐\n");
        writer.write("│ 3. VLANS PROPOSÉS                                                                │\n");
        writer.write("├─────────────────────────────────────────────────────────────────────────────────┤\n");
        for (VLAN vlan : vlans) {
            String reseau = "";
            if (vlan.getReseauAssocie() != null) {
                reseau = vlan.getReseauAssocie().getAdresseReseau() + "/" + vlan.getReseauAssocie().getCidr();
            }
            writer.write(String.format("│ • VLAN %-3d : %-15s → %-22s │ Capacité: %4d hôtes │\n",
                vlan.getId(), vlan.getNom(), reseau, vlan.getCapacite()));
        }
        writer.write("└─────────────────────────────────────────────────────────────────────────────────┘\n\n");
        
        // 4. Recommandations
        writer.write("┌─────────────────────────────────────────────────────────────────────────────────┐\n");
        writer.write("│ 4. RECOMMANDATIONS TECHNIQUES                                                     │\n");
        writer.write("├─────────────────────────────────────────────────────────────────────────────────┤\n");
        if (recommandations.isEmpty()) {
            writer.write("│ ✅ Aucune recommandation particulière.                                           │\n");
        } else {
            for (Recommandation rec : recommandations) {
                writer.write(String.format("│ [%s] %-50s │\n", rec.getPriorite(), rec.getTitre()));
                writer.write(String.format("│     → %-65s │\n", rec.getMessage()));
            }
        }
        writer.write("└─────────────────────────────────────────────────────────────────────────────────┘\n\n");
        
        writer.write("╔════════════════════════════════════════════════════════════════════════════════╗\n");
        writer.write("║                              FIN DU RAPPORT                                     ║\n");
        writer.write("╚════════════════════════════════════════════════════════════════════════════════╝\n");
        
        writer.close();
    }  
}
