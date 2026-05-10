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
        System.out.println("===== IPPlan-Manager : TP1 =====");
        System.out.println("Découverte des premières classes du projet");

        AdresseIP ipRouteur = new AdresseIP("192.168.1.1");
        AdresseIP ipServeur = new AdresseIP("192.168.1.10");
        AdresseIP ipClient1 = new AdresseIP("192.168.1.50");
        AdresseIP ipClient2 = new AdresseIP("192.168.1.51");
        AdresseIP ipSwitch = new AdresseIP("192.168.1.2");
        AdresseIP ipAP = new AdresseIP("192.168.1.3");

        InterfaceReseau interfaceRouteur = new InterfaceReseau("eth0", ipRouteur);
        InterfaceReseau interfaceServeur = new InterfaceReseau("eth0", ipServeur);
        InterfaceReseau interfaceClient1 = new InterfaceReseau("wlan0", ipClient1);
        InterfaceReseau interfaceClient2 = new InterfaceReseau("wlan0", ipClient2);
        InterfaceReseau interfaceSwitch = new InterfaceReseau("eth1", ipSwitch);
        InterfaceReseau interfaceAP = new InterfaceReseau("wlan1", ipAP);
        InterfaceReseau interfaceSansIP = new InterfaceReseau("eth2", null);

        interfaceRouteur.activer();
        interfaceServeur.activer();
        interfaceSwitch.activer();
        interfaceAP.activer();
        interfaceClient2.desactiver();

        Equipement routeur = new Equipement("R1", "Routeur", interfaceRouteur);
        Equipement serveur = new Equipement("SRV_DNS", "Serveur", interfaceServeur);
        Equipement client1 = new Equipement("PC_ADMIN", "Poste client", interfaceClient1);
        Equipement client2 = new Equipement("PC_GUEST", "Poste client", interfaceClient2);
        Equipement switchEquip = new Equipement("SW1", "Switch", interfaceSwitch);
        Equipement apEquip = new Equipement("AP1", "Point d'accès WiFi", interfaceAP);
        Equipement equipementSansIP = new Equipement("DEV_NULL", "Test sans IP", interfaceSansIP);

        ReseauIP reseauPrincipal = new ReseauIP("192.168.1.0", 24, "Réseau principal du laboratoire IRT");
        ReseauIP reseauSecondaire = new ReseauIP("10.0.0.0", 8, "Réseau secondaire administratif");

        System.out.println("--- Réseaux créés ---");
        reseauPrincipal.afficher();
        System.out.println();
        reseauSecondaire.afficher();
        System.out.println();

        System.out.println("--- Équipements créés ---");
        routeur.afficher();
        System.out.println();
        serveur.afficher();
        System.out.println();
        client1.afficher();
        System.out.println();
        client2.afficher();
        System.out.println();
        switchEquip.afficher();
        System.out.println();
        apEquip.afficher();
        System.out.println();
        equipementSansIP.afficher();
    }
}
