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
public class GestionnaireVLAN {
private ArrayList<VLAN> vlans;
    
    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }
    
    // Version avec validation de conflit (lance une exception si ID existe déjà)
    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {
        if (vlan == null) {
            return;
        }
        
        for (VLAN v : vlans) {
            if (v.getId() == vlan.getId()) {
                throw new ConflitVLANException("Conflit VLAN : l'identifiant " + vlan.getId() + 
                                               " est déjà utilisé par le VLAN " + v.getNom());
            }
        }
        vlans.add(vlan);
        System.out.println("✅ VLAN " + vlan.getId() + " (" + vlan.getNom() + ") ajouté");
    }
    
    public void supprimerVLAN(int id) {
        for (int i = 0; i < vlans.size(); i++) {
            if (vlans.get(i).getId() == id) {
                vlans.remove(i);
                System.out.println("❌ VLAN " + id + " supprimé");
                return;
            }
        }
        System.out.println("VLAN " + id + " non trouvé");
    }
    
    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) {
                return vlan;
            }
        }
        return null;
    }
    
    public void afficherTousLesVLANs() {
        if (vlans.isEmpty()) {
            System.out.println("Aucun VLAN configuré");
        } else {
            System.out.println("\n" + "═".repeat(60));
            System.out.println("           LISTE DES VLANS");
            System.out.println("═".repeat(60));
            for (VLAN vlan : vlans) {
                vlan.afficher();
                System.out.println();
            }
        }
    }
    
    public void afficherVLANsCritiques() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("        VLANS CRITIQUES (Capacité > 100 hôtes)");
        System.out.println("═".repeat(60));
        
        boolean trouve = false;
        for (VLAN vlan : vlans) {
            if (vlan.getCapacite() > 100) {
                System.out.println("⚠️  VLAN " + vlan.getId() + " - " + vlan.getNom() + 
                                 " - " + vlan.getCapacite() + " hôtes");
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun VLAN critique détecté");
        }
    }
    
    public void afficherVLANMaxCapacite() {
        if (vlans.isEmpty()) {
            System.out.println("Aucun VLAN configuré");
            return;
        }
        
        VLAN vlanMax = vlans.get(0);
        for (VLAN vlan : vlans) {
            if (vlan.getCapacite() > vlanMax.getCapacite()) {
                vlanMax = vlan;
            }
        }
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("     VLAN AVEC LA PLUS GRANDE CAPACITÉ");
        System.out.println("═".repeat(60));
        System.out.println("🏆 VLAN " + vlanMax.getId() + " - " + vlanMax.getNom() + 
                         " - " + vlanMax.getCapacite() + " hôtes");
    }
    
    public int obtenirNombreVLANs() {
        return vlans.size();
    }
    
    public int obtenirCapaciteTotale() {
        int total = 0;
        for (VLAN vlan : vlans) {
            total += vlan.getCapacite();
        }
        return total;
    }
    
    public void afficherStatistiques() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           STATISTIQUES DES VLANS");
        System.out.println("═".repeat(60));
        System.out.println("📊 Nombre total de VLANs : " + obtenirNombreVLANs());
        System.out.println("📊 Capacité totale : " + obtenirCapaciteTotale() + " hôtes");
        if (obtenirNombreVLANs() > 0) {
            System.out.println("📊 Capacité moyenne : " + 
                              (obtenirCapaciteTotale() / obtenirNombreVLANs()) + " hôtes");
        }
    }    
}
