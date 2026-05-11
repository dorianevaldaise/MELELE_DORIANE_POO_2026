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
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.exception.ConflitVLANException;
import java.util.ArrayList;

public class GestionnaireVLAN {
 private ArrayList<VLAN> vlans;
    
    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }
    
    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {
        if (vlan == null) return;
        for (VLAN v : vlans) {
            if (v.getId() == vlan.getId()) {
                throw new ConflitVLANException("Conflit VLAN: l'identifiant " + vlan.getId() + " est déjà utilisé.");
            }
        }
        vlans.add(vlan);
        System.out.println("✅ VLAN " + vlan.getId() + " (" + vlan.getNom() + ") ajouté");
    }
    
    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) return vlan;
        }
        return null;
    }
    
    public ArrayList<VLAN> getVlans() {
        return vlans;
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
    
    public int obtenirNombreVLANs() {
        return vlans.size();
    }   
}
