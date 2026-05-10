/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */
public class Main {
 public static void main(String[] args) {
        
        System.out.println("=========================================");
        System.out.println("    IPPLAN-MANAGER - CALCULS RÉSEAU");
        System.out.println("=========================================\n");
        
        // Création de l'infrastructure
        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");
        
        System.out.println("=== CRÉATION DES SOUS-RÉSEAUX AVEC CALCULS AUTO ===\n");
        
        // Réseau Administration - /24
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Réseau Administration");
        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        infrastructure.ajouterSousReseau(admin);
        
        // Réseau Technique - /25 (sous-réseau plus petit)
        ReseauIP reseauTechnique = new ReseauIP("172.16.0.0", 25, "Réseau Technique");
        SousReseau tech = new SousReseau("TECH", reseauTechnique);
        infrastructure.ajouterSousReseau(tech);
        
        // Réseau WiFi - /8 (grand réseau)
        ReseauIP reseauWifi = new ReseauIP("10.0.0.0", 8, "Réseau WiFi Entreprise");
        SousReseau wifi = new SousReseau("WIFI", reseauWifi);
        infrastructure.ajouterSousReseau(wifi);
        
        // RÉSEAUX SUPPLÉMENTAIRES (Travail demandé)
        
        // Réseau DMZ - /28 (très petit réseau)
        ReseauIP reseauDMZ = new ReseauIP("192.168.100.0", 28, "Zone Démilitarisée (DMZ)");
        SousReseau dmz = new SousReseau("DMZ", reseauDMZ);
        infrastructure.ajouterSousReseau(dmz);
        
        // Réseau VoIP - /26
        ReseauIP reseauVoIP = new ReseauIP("192.168.50.0", 26, "Réseau Téléphonie VoIP");
        SousReseau voip = new SousReseau("VOIP", reseauVoIP);
        infrastructure.ajouterSousReseau(voip);
        
        // Réseau Invités - /27
        ReseauIP reseauGuest = new ReseauIP("192.168.200.0", 27, "Réseau Invités");
        SousReseau guest = new SousReseau("GUEST", reseauGuest);
        infrastructure.ajouterSousReseau(guest);
        
        // Réseau Serveurs - /24
        ReseauIP reseauServeurs = new ReseauIP("10.10.0.0", 24, "Réseau Serveurs");
        SousReseau serveurs = new SousReseau("SERVEURS", reseauServeurs);
        infrastructure.ajouterSousReseau(serveurs);
        
        // Réseau Management - /29 (très petit)
        ReseauIP reseauMgmt = new ReseauIP("192.168.255.0", 29, "Réseau Management");
        SousReseau management = new SousReseau("MANAGEMENT", reseauMgmt);
        infrastructure.ajouterSousReseau(management);
        
        System.out.println("\n=== AFFICHAGE COMPLET DES SOUS-RÉSEAUX ===\n");
        
        // Affichage de tous les sous-réseaux avec leurs calculs
        System.out.println("\n=========================================");
        System.out.println("        DÉTAIL DES SOUS-RÉSEAUX");
        System.out.println("=========================================\n");
        
        afficherDetailReseau(reseauAdmin);
        afficherDetailReseau(reseauTechnique);
        afficherDetailReseau(reseauWifi);
        afficherDetailReseau(reseauDMZ);
        afficherDetailReseau(reseauVoIP);
        afficherDetailReseau(reseauGuest);
        afficherDetailReseau(reseauServeurs);
        afficherDetailReseau(reseauMgmt);
        
        System.out.println("\n=== TEST DES MÉTHODES STATIQUES DU CALCULATEUR ===\n");
        
        // Tests supplémentaires des méthodes de CalculateurReseau
        System.out.println("--- Test de calculNombreHotes ---");
        System.out.println("/24 -> " + CalculateurReseau.calculerNombreHotes(24) + " hôtes");
        System.out.println("/25 -> " + CalculateurReseau.calculerNombreHotes(25) + " hôtes");
        System.out.println("/26 -> " + CalculateurReseau.calculerNombreHotes(26) + " hôtes");
        System.out.println("/27 -> " + CalculateurReseau.calculerNombreHotes(27) + " hôtes");
        System.out.println("/28 -> " + CalculateurReseau.calculerNombreHotes(28) + " hôtes");
        System.out.println("/29 -> " + CalculateurReseau.calculerNombreHotes(29) + " hôtes");
        System.out.println("/30 -> " + CalculateurReseau.calculerNombreHotes(30) + " hôtes");
        
        System.out.println("\n--- Test de obtenirClasseReseau ---");
        System.out.println("192.168.1.0 -> " + CalculateurReseau.obtenirClasseReseau("192.168.1.0"));
        System.out.println("172.16.0.0 -> " + CalculateurReseau.obtenirClasseReseau("172.16.0.0"));
        System.out.println("10.0.0.0 -> " + CalculateurReseau.obtenirClasseReseau("10.0.0.0"));
        System.out.println("224.0.0.1 -> " + CalculateurReseau.obtenirClasseReseau("224.0.0.1"));
        
        System.out.println("\n--- Test de estAdressePrivee ---");
        System.out.println("192.168.1.0 -> " + (CalculateurReseau.estAdressePrivee("192.168.1.0") ? "Privée" : "Publique"));
        System.out.println("172.20.0.0 -> " + (CalculateurReseau.estAdressePrivee("172.20.0.0") ? "Privée" : "Publique"));
        System.out.println("10.0.0.0 -> " + (CalculateurReseau.estAdressePrivee("10.0.0.0") ? "Privée" : "Publique"));
        System.out.println("8.8.8.8 -> " + (CalculateurReseau.estAdressePrivee("8.8.8.8") ? "Privée" : "Publique"));
        System.out.println("127.0.0.1 -> " + (CalculateurReseau.estAdressePrivee("127.0.0.1") ? "Privée (Loopback)" : "Publique"));
        
        System.out.println("\n=== FIN DES TESTS ===\n");
    }
    
    // Méthode utilitaire pour afficher les détails d'un réseau
    private static void afficherDetailReseau(ReseauIP reseau) {
        reseau.afficher();
        System.out.println();
    }  
}
