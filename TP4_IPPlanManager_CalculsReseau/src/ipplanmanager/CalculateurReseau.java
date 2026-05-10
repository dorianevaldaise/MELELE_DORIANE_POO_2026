/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Mahone
 */
public class CalculateurReseau {
  
    // Méthode pour calculer le nombre d'hôtes à partir du CIDR
    // Formule: 2^(32 - CIDR) - 2
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) {
            return 0;
        }
        int bitsHotes = 32 - cidr;
        return (int) Math.pow(2, bitsHotes) - 2;
    }
    
    // Méthode pour obtenir la classe réseau à partir d'une adresse IP
    public static String obtenirClasseReseau(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        int premierOctet = Integer.parseInt(parties[0]);
        
        if (premierOctet >= 1 && premierOctet <= 126) {
            return "Classe A (1-126)";
        }
        if (premierOctet >= 128 && premierOctet <= 191) {
            return "Classe B (128-191)";
        }
        if (premierOctet >= 192 && premierOctet <= 223) {
            return "Classe C (192-223)";
        }
        if (premierOctet >= 224 && premierOctet <= 239) {
            return "Classe D (Multicast)";
        }
        if (premierOctet >= 240 && premierOctet <= 255) {
            return "Classe E (Expérimental)";
        }
        return "Classe inconnue";
    }
    
    // Méthode pour convertir un CIDR en masque décimal
    public static String obtenirMasqueDecimal(int cidr) {
        switch (cidr) {
            case 0: return "0.0.0.0";
            case 1: return "128.0.0.0";
            case 2: return "192.0.0.0";
            case 3: return "224.0.0.0";
            case 4: return "240.0.0.0";
            case 5: return "248.0.0.0";
            case 6: return "252.0.0.0";
            case 7: return "254.0.0.0";
            case 8: return "255.0.0.0";
            case 9: return "255.128.0.0";
            case 10: return "255.192.0.0";
            case 11: return "255.224.0.0";
            case 12: return "255.240.0.0";
            case 13: return "255.248.0.0";
            case 14: return "255.252.0.0";
            case 15: return "255.254.0.0";
            case 16: return "255.255.0.0";
            case 17: return "255.255.128.0";
            case 18: return "255.255.192.0";
            case 19: return "255.255.224.0";
            case 20: return "255.255.240.0";
            case 21: return "255.255.248.0";
            case 22: return "255.255.252.0";
            case 23: return "255.255.254.0";
            case 24: return "255.255.255.0";
            case 25: return "255.255.255.128";
            case 26: return "255.255.255.192";
            case 27: return "255.255.255.224";
            case 28: return "255.255.255.240";
            case 29: return "255.255.255.248";
            case 30: return "255.255.255.252";
            case 31: return "255.255.255.254";
            case 32: return "255.255.255.255";
            default: return "Masque non disponible pour /" + cidr;
        }
    }
    
    // TRAVAIL SUPPLÉMENTAIRE: Vérifier si une adresse IP est privée
    public static boolean estAdressePrivee(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        if (parties.length != 4) return false;
        
        int premier = Integer.parseInt(parties[0]);
        int deuxieme = Integer.parseInt(parties[1]);
        
        // Plages d'adresses privées:
        // 10.0.0.0 - 10.255.255.255 (Classe A)
        if (premier == 10) return true;
        
        // 172.16.0.0 - 172.31.255.255 (Classe B)
        if (premier == 172 && deuxieme >= 16 && deuxieme <= 31) return true;
        
        // 192.168.0.0 - 192.168.255.255 (Classe C)
        if (premier == 192 && deuxieme == 168) return true;
        
        // 127.0.0.0 - 127.255.255.255 (Loopback)
        if (premier == 127) return true;
        
        return false;
    }
    
    // TRAVAIL SUPPLÉMENTAIRE: Obtenir la plage d'adresses utilisables
    public static String obtenirPlageUtilisable(String adresseReseau, int cidr) {
        String[] parties = adresseReseau.split("\\.");
        int premier = Integer.parseInt(parties[0]);
        int deuxieme = Integer.parseInt(parties[1]);
        int troisieme = Integer.parseInt(parties[2]);
        int quatrieme = Integer.parseInt(parties[3]);
        
        int bitsHotes = 32 - cidr;
        long nombreHotes = (long) Math.pow(2, bitsHotes);
        
        // Calcul de la première adresse utilisable (adresse réseau + 1)
        long adresseReseauLong = ((long) premier << 24) | ((long) deuxieme << 16) | 
                                   ((long) troisieme << 8) | (long) quatrieme;
        long premiereUtilisable = adresseReseauLong + 1;
        long derniereUtilisable = adresseReseauLong + nombreHotes - 2;
        
        String premiereIP = convertirLongEnIP(premiereUtilisable);
        String derniereIP = convertirLongEnIP(derniereUtilisable);
        
        return premiereIP + " - " + derniereIP;
    }
    
    // Méthode utilitaire: Convertir un long en adresse IP
    private static String convertirLongEnIP(long ip) {
        return ((ip >> 24) & 0xFF) + "." +
               ((ip >> 16) & 0xFF) + "." +
               ((ip >> 8) & 0xFF) + "." +
               (ip & 0xFF);
    }  
}
