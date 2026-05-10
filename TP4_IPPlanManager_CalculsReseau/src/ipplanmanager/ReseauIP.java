/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */
public class ReseauIP {
  private String adresseReseau;
    private int masqueCidr;
    private String description;
    
    public ReseauIP(String adresseReseau, int masqueCidr, String description) {
        setAdresseReseau(adresseReseau);
        setMasqueCidr(masqueCidr);
        setDescription(description);
    }
    
    // Constructeur avec description par défaut
    public ReseauIP(String adresseReseau, int masqueCidr) {
        this(adresseReseau, masqueCidr, "Sans description");
    }
    
    public String getAdresseReseau() {
        return adresseReseau;
    }
    
    public void setAdresseReseau(String adresseReseau) {
        if (adresseReseau == null || adresseReseau.isEmpty()) {
            this.adresseReseau = "192.168.0.0";
        } else {
            this.adresseReseau = adresseReseau;
        }
    }
    
    public int getMasqueCidr() {
        return masqueCidr;
    }
    
    public void setMasqueCidr(int masqueCidr) {
        if (masqueCidr < 0 || masqueCidr > 32) {
            this.masqueCidr = 24;
        } else {
            this.masqueCidr = masqueCidr;
        }
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "Réseau IP";
        } else {
            this.description = description;
        }
    }
    
    // MÉTHODE AFFICHER AMÉLIORÉE AVEC CALCULS AUTOMATIQUES
    public void afficher() {
        System.out.println("----------------------------------------");
        System.out.println("Réseau: " + adresseReseau + "/" + masqueCidr);
        System.out.println("Description: " + description);
        System.out.println("Classe réseau: " + CalculateurReseau.obtenirClasseReseau(adresseReseau));
        System.out.println("Masque décimal: " + CalculateurReseau.obtenirMasqueDecimal(masqueCidr));
        System.out.println("Capacité maximale: " + CalculateurReseau.calculerNombreHotes(masqueCidr) + " hôtes");
        
        // Affichage supplémentaire (Travail supplémentaire)
        String statutPrive = CalculateurReseau.estAdressePrivee(adresseReseau) ? "Oui" : "Non";
        System.out.println("Adresse privée: " + statutPrive);
        
        if (CalculateurReseau.calculerNombreHotes(masqueCidr) > 0) {
            System.out.println("Plage utilisable: " + CalculateurReseau.obtenirPlageUtilisable(adresseReseau, masqueCidr));
        }
        System.out.println("----------------------------------------");
    }   
}
