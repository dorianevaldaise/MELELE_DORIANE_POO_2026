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
        
        // Création de l'infrastructure principale
        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");
        
        System.out.println("=== CRÉATION DES SOUS-RÉSEAUX ===\n");
        
        // Réseau Administration
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Réseau administration");
        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        infrastructure.ajouterSousReseau(admin);
        
        // Réseau Technique
        ReseauIP reseauTech = new ReseauIP("192.168.2.0", 24, "Réseau technique");
        SousReseau tech = new SousReseau("TECH", reseauTech);
        infrastructure.ajouterSousReseau(tech);
        
        // Réseau WiFi (Troisième sous-réseau demandé)
        ReseauIP reseauWiFi = new ReseauIP("192.168.3.0", 24, "Réseau WiFi");
        SousReseau wifi = new SousReseau("WIFI", reseauWiFi);
        infrastructure.ajouterSousReseau(wifi);
        
        System.out.println("\n=== CRÉATION DES ÉQUIPEMENTS ===\n");
        
        // === ÉQUIPEMENT 1: Routeur principal ===
        AdresseIP ipRouteur1 = new AdresseIP("192.168.1.1");
        AdresseIP ipRouteur2 = new AdresseIP("192.168.2.1");
        AdresseIP ipRouteur3 = new AdresseIP("192.168.3.1");
        
        InterfaceReseau eth0 = new InterfaceReseau("eth0", ipRouteur1);
        InterfaceReseau eth1 = new InterfaceReseau("eth1", ipRouteur2);
        InterfaceReseau eth2 = new InterfaceReseau("eth2", ipRouteur3);
        
        eth0.activer();
        eth1.activer();
        eth2.activer();
        
        Equipement routeur = new Equipement("R1_EDGE", "Routeur");
        routeur.ajouterInterface(eth0);
        routeur.ajouterInterface(eth1);
        routeur.ajouterInterface(eth2);
        infrastructure.ajouterEquipement(routeur);
        
        // === ÉQUIPEMENT 2: Switch principal ===
        AdresseIP ipSwitch = new AdresseIP("192.168.1.2");
        InterfaceReseau gig0 = new InterfaceReseau("GigabitEthernet0/1", ipSwitch);
        InterfaceReseau gig1 = new InterfaceReseau("GigabitEthernet0/2", new AdresseIP("192.168.1.3"));
        InterfaceReseau gig2 = new InterfaceReseau("GigabitEthernet0/3", new AdresseIP("192.168.1.4"));
        
        gig0.activer();
        
        Equipement switchPrincipal = new Equipement("SW_CORE", "Switch");
        switchPrincipal.ajouterInterface(gig0);
        switchPrincipal.ajouterInterface(gig1);
        switchPrincipal.ajouterInterface(gig2);
        infrastructure.ajouterEquipement(switchPrincipal);
        
        // === ÉQUIPEMENT 3: Serveur Administration ===
        AdresseIP ipServeurAdmin = new AdresseIP("192.168.1.10");
        InterfaceReseau ethServeur1 = new InterfaceReseau("eth0", ipServeurAdmin);
        InterfaceReseau ethServeur2 = new InterfaceReseau("eth1", new AdresseIP("10.0.0.1"));
        
        ethServeur1.activer();
        
        Equipement serveurAdmin = new Equipement("SRV_ADMIN", "Serveur");
        serveurAdmin.ajouterInterface(ethServeur1);
        serveurAdmin.ajouterInterface(ethServeur2);
        infrastructure.ajouterEquipement(serveurAdmin);
        
        // === ÉQUIPEMENT 4: Serveur Technique ===
        AdresseIP ipServeurTech = new AdresseIP("192.168.2.10");
        InterfaceReseau ethTech1 = new InterfaceReseau("eth0", ipServeurTech);
        InterfaceReseau ethTech2 = new InterfaceReseau("eth1", new AdresseIP("172.16.0.1"));
        
        ethTech1.activer();
        
        Equipement serveurTech = new Equipement("SRV_TECH", "Serveur");
        serveurTech.ajouterInterface(ethTech1);
        serveurTech.ajouterInterface(ethTech2);
        infrastructure.ajouterEquipement(serveurTech);
        
        // === ÉQUIPEMENT 5: Point d'accès WiFi ===
        AdresseIP ipAP = new AdresseIP("192.168.3.2");
        InterfaceReseau wlan0 = new InterfaceReseau("wlan0", ipAP);
        InterfaceReseau ethAP = new InterfaceReseau("eth0", new AdresseIP("192.168.3.3"));
        
        wlan0.activer();
        ethAP.activer();
        
        Equipement accessPoint = new Equipement("AP_LOBBY", "Access Point");
        accessPoint.ajouterInterface(wlan0);
        accessPoint.ajouterInterface(ethAP);
        infrastructure.ajouterEquipement(accessPoint);
        
        // === ÉQUIPEMENT 6: Pare-feu ===
        AdresseIP ipFW1 = new AdresseIP("192.168.1.254");
        AdresseIP ipFW2 = new AdresseIP("10.0.0.254");
        InterfaceReseau fwWan = new InterfaceReseau("WAN", ipFW2);
        InterfaceReseau fwLan = new InterfaceReseau("LAN", ipFW1);
        
        fwWan.activer();
        fwLan.activer();
        
        Equipement firewall = new Equipement("FW_EDGE", "Pare-feu");
        firewall.ajouterInterface(fwWan);
        firewall.ajouterInterface(fwLan);
        infrastructure.ajouterEquipement(firewall);
        
        // === ÉQUIPEMENT 7: Client administratif ===
        AdresseIP ipClient = new AdresseIP("192.168.1.100");
        InterfaceReseau clientEth = new InterfaceReseau("eth0", ipClient);
        clientEth.activer();
        
        Equipement clientAdmin = new Equipement("PC_ADMIN", "Poste de travail");
        clientAdmin.ajouterInterface(clientEth);
        infrastructure.ajouterEquipement(clientAdmin);
        
        System.out.println("\n=== AFFICHAGE COMPLET DE L'INFRASTRUCTURE ===\n");
        infrastructure.afficher();
        
        System.out.println("\n=== TEST DE LA MÉTHODE DE RECHERCHE ===\n");
        infrastructure.rechercherEquipement("R1_EDGE");
        System.out.println();
        infrastructure.rechercherEquipement("EQUIPEMENT_INEXISTANT");
        
        System.out.println("\n=== TEST DE SUPPRESSION ===\n");
        infrastructure.supprimerEquipement("PC_ADMIN");
        infrastructure.supprimerSousReseau("WIFI");
        
        System.out.println("\n=== AFFICHAGE APRÈS MODIFICATIONS ===\n");
        infrastructure.afficher();
    } 
}
