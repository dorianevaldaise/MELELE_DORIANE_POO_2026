/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.model;

/**
 *
 * @author Mahone
 */
public class VLAN {
 private int id;
    private String nom;
    private ResultatVLSM reseauAssocie;
    private String description;
    
    public VLAN(int id, String nom, ResultatVLSM reseauAssocie, String description) {
        setId(id);
        setNom(nom);
        this.reseauAssocie = reseauAssocie;
        setDescription(description);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        if (id <= 0) {
            this.id = 1;
        } else {
            this.id = id;
        }
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            this.nom = "VLAN_INCONNU";
        } else {
            this.nom = nom;
        }
    }
    
    public ResultatVLSM getReseauAssocie() {
        return reseauAssocie;
    }
    
    public void setReseauAssocie(ResultatVLSM reseauAssocie) {
        this.reseauAssocie = reseauAssocie;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "Aucune description";
        } else {
            this.description = description;
        }
    }
    
    public int getCapacite() {
        if (reseauAssocie != null) {
            return reseauAssocie.getCapacite();
        }
        return 0;
    }
    
    public int getNombreHotesDemandes() {
        if (reseauAssocie != null) {
            return reseauAssocie.getHotesDemandes();
        }
        return 0;
    }
    
    public void afficher() {
        System.out.printf("  │ VLAN %-3d : %-15s → %-18s/%-3d │ Capacité: %4d hôtes │%n",
            id, nom, 
            reseauAssocie != null ? reseauAssocie.getAdresseReseau() : "N/A",
            reseauAssocie != null ? reseauAssocie.getCidr() : 0,
            getCapacite());
    }   
}
