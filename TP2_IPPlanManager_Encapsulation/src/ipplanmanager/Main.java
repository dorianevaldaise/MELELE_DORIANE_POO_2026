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
        System.out.println("===== TP2 : Encapsulation =====");
        System.out.println();

        // Test des adresses IP (cas valides et invalides)
        System.out.println("--- Test des adresses IP ---");
        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("");      // Cas invalide
        AdresseIP ip3 = new AdresseIP(null);    // Cas invalide
        AdresseIP ip4 = new AdresseIP("10.0.0.1");
        AdresseIP ip5 = new AdresseIP("192.168.0.100");

        System.out.println();

        // Test de la méthode estAdresseLocale()
        System.out.println("--- Test estAdresseLocale() ---");
        System.out.println("ip1 (192.168.1.1) est locale ? " + ip1.estAdresseLocale());
        System.out.println("ip4 (10.0.0.1) est locale ? " + ip4.estAdresseLocale());
        System.out.println();

        // Test des interfaces réseau
        System.out.println("--- Test des interfaces réseau ---");
        InterfaceReseau interface1 = new InterfaceReseau("eth0", ip1);
        InterfaceReseau interface2 = new InterfaceReseau("", ip2);      // Nom invalide
        InterfaceReseau interface3 = new InterfaceReseau("eth1", ip4);
        InterfaceReseau interface4 = new InterfaceReseau("wlan0", ip5);

        interface1.activer();
        interface1.afficher();
        System.out.println();
        interface2.afficher();
        System.out.println();

        // Test des équipements (plusieurs équipements)
        System.out.println("--- Test des équipements ---");
        Equipement routeur = new Equipement("R1EDGE", "Router", interface1);
        Equipement switch1 = new Equipement("SW-CORE", "Switch", interface3);
        Equipement serveur = new Equipement("", "Server", interface4);  // Nom invalide
        Equipement firewall = new Equipement("FW-01", "", null);        // Type invalide

        System.out.println("Routeur :");
        routeur.afficher();
        System.out.println();
        System.out.println("Switch :");
        switch1.afficher();
        System.out.println();
        System.out.println("Serveur (nom invalide) :");
        serveur.afficher();
        System.out.println();
        System.out.println("Firewall (type invalide) :");
        firewall.afficher();
        System.out.println();

        // Test de modification avec setters
        System.out.println("--- Test des setters ---");
        System.out.println("Avant modification - IP du routeur : " + routeur.getInterfacePrincipale().getAdresseIP().getValeur());
        
        // Modification de l'adresse IP
        routeur.getInterfacePrincipale().getAdresseIP().setValeur("10.10.10.1");
        System.out.println("Après modification - IP du routeur : " + routeur.getInterfacePrincipale().getAdresseIP().getValeur());
        
        // Modification du nom de l'interface
        interface1.setNom("eth0_renamed");
        System.out.println("Nouveau nom de l'interface : " + interface1.getNom());
        
        // Activation/désactivation
        interface3.activer();
        System.out.println("Interface du switch active ? " + interface3.isActive());
        interface3.desactiver();
        System.out.println("Après désactivation - active ? " + interface3.isActive());
        System.out.println();

        // Test du réseau
        System.out.println("--- Test de la classe ReseauIP ---");
        ReseauIP reseau1 = new ReseauIP("192.168.1.0", 24, "Réseau local");
        ReseauIP reseau2 = new ReseauIP("", 45, null);  // Cas invalides
        ReseauIP reseau3 = new ReseauIP("10.0.0.0", 8, "Réseau entreprise");
        
        reseau1.afficher();
        reseau2.afficher();  // Doit afficher valeurs par défaut
        reseau3.afficher();
    }
}
