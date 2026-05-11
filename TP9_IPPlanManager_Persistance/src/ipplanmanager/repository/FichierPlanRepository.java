/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.repository;

/**
 *
 * @author Mahone
 */

import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FichierPlanRepository {
  
    public void sauvegarderPlanCSV(ArrayList<ResultatVLSM> resultats, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("Nom;AdresseReseau;CIDR;Masque;Capacite;PremiereAdresse;DerniereAdresse\n");
        
        for (ResultatVLSM resultat : resultats) {
            writer.write(resultat.getNomBesoin() + ";" +
                        resultat.getAdresseReseau() + ";" +
                        resultat.getCidr() + ";" +
                        resultat.getMasqueDecimal() + ";" +
                        resultat.getCapacite() + ";" +
                        resultat.getPremiereAdresseUtilisable() + ";" +
                        resultat.getDerniereAdresseUtilisable() + "\n");
        }
        writer.close();
    }
    
    public void sauvegarderVLANsCSV(ArrayList<VLAN> vlans, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("ID;Nom;AdresseReseau;CIDR;Capacite;Description\n");
        
        for (VLAN vlan : vlans) {
            if (vlan.getReseauAssocie() != null) {
                writer.write(vlan.getId() + ";" +
                            vlan.getNom() + ";" +
                            vlan.getReseauAssocie().getAdresseReseau() + ";" +
                            vlan.getReseauAssocie().getCidr() + ";" +
                            vlan.getReseauAssocie().getCapacite() + ";" +
                            vlan.getDescription() + "\n");
            }
        }
        writer.close();
    }
    
    public void sauvegarderRecommandations(ArrayList<Recommandation> recommandations, String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("═══════════════════════════════════════════════════════════════\n");
        writer.write("            RECOMMANDATIONS IPPLAN-MANAGER\n");
        writer.write("═══════════════════════════════════════════════════════════════\n\n");
        
        if (recommandations.isEmpty()) {
            writer.write("✅ Aucune recommandation particulière.\n");
        } else {
            for (Recommandation rec : recommandations) {
                writer.write("[" + rec.getPriorite() + "] " + rec.getTitre() + "\n");
                writer.write("    → " + rec.getMessage() + "\n\n");
            }
        }
        writer.close();
    }  
}
