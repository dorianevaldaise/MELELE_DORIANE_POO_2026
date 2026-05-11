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
  
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) {
            return 0;
        }
        int bitsHotes = 32 - cidr;
        if (bitsHotes == 0) {
            return 1;
        }
        return (int) Math.pow(2, bitsHotes) - 2;
    }
    
    public static int calculerCidrPourHotes(int nombreHotes) {
        for (int cidr = 32; cidr >= 0; cidr--) {
            int capacite = calculerNombreHotes(cidr);
            if (capacite >= nombreHotes) {
                return cidr;
            }
        }
        return -1;
    }
    
    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xffffffff << (32 - cidr);
        int octet1 = (masque >>> 24) & 0xFF;
        int octet2 = (masque >>> 16) & 0xFF;
        int octet3 = (masque >>> 8) & 0xFF;
        int octet4 = masque & 0xFF;
        return octet1 + "." + octet2 + "." + octet3 + "." + octet4;
    }
    
    public static int convertirIPEnEntier(String ip) {
        String[] parties = ip.split("\\.");
        int resultat = 0;
        for (int i = 0; i < 4; i++) {
            resultat = (resultat << 8) | Integer.parseInt(parties[i]);
        }
        return resultat;
    }
    
    public static String convertirEntierEnIP(int valeur) {
        int octet1 = (valeur >>> 24) & 0xFF;
        int octet2 = (valeur >>> 16) & 0xFF;
        int octet3 = (valeur >>> 8) & 0xFF;
        int octet4 = valeur & 0xFF;
        return octet1 + "." + octet2 + "." + octet3 + "." + octet4;
    }
    
    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }
    
    public static String calculerPremiereAdresseUtilisable(String adresseReseau) {
        int ipEntier = convertirIPEnEntier(adresseReseau);
        int premiereUtilisable = ipEntier + 1;
        return convertirEntierEnIP(premiereUtilisable);
    }
    
    public static String calculerDerniereAdresseUtilisable(String adresseReseau, int cidr) {
        int ipEntier = convertirIPEnEntier(adresseReseau);
        int tailleBloc = calculerTailleBloc(cidr);
        int broadcast = ipEntier + tailleBloc - 1;
        int derniereUtilisable = broadcast - 1;
        return convertirEntierEnIP(derniereUtilisable);
    }
    
    public static boolean estAdresseIPValide(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        String[] parties = ip.split("\\.");
        if (parties.length != 4) {
            return false;
        }
        for (String partie : parties) {
            try {
                int valeur = Integer.parseInt(partie);
                if (valeur < 0 || valeur > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
    public static void verifierAdresseIP(String ip) throws AdresseIPInvalideException {
        if (!estAdresseIPValide(ip)) {
            throw new AdresseIPInvalideException("Adresse IP invalide: " + ip);
        }
    }
    
    public static int calculerAdresseFin(String adresseReseau, int cidr) {
        int debut = convertirIPEnEntier(adresseReseau);
        int tailleBloc = calculerTailleBloc(cidr);
        return debut + tailleBloc - 1;
    }
    
    public static boolean reseauxSeChevauchent(String adresse1, int cidr1, 
                                                String adresse2, int cidr2) {
        int debut1 = convertirIPEnEntier(adresse1);
        int fin1 = calculerAdresseFin(adresse1, cidr1);
        int debut2 = convertirIPEnEntier(adresse2);
        int fin2 = calculerAdresseFin(adresse2, cidr2);
        return debut1 <= fin2 && debut2 <= fin1;
    }
    
    public static boolean estAdressePrivee(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        int premier = Integer.parseInt(parties[0]);
        int deuxieme = Integer.parseInt(parties[1]);
        if (premier == 10) return true;
        if (premier == 172 && deuxieme >= 16 && deuxieme <= 31) return true;
        if (premier == 192 && deuxieme == 168) return true;
        if (premier == 127) return true;
        return false;
    }
    
    public static String obtenirClasseReseau(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        int premierOctet = Integer.parseInt(parties[0]);
        if (premierOctet >= 1 && premierOctet <= 126) return "Classe A";
        if (premierOctet >= 128 && premierOctet <= 191) return "Classe B";
        if (premierOctet >= 192 && premierOctet <= 223) return "Classe C";
        if (premierOctet >= 224 && premierOctet <= 239) return "Classe D";
        if (premierOctet >= 240 && premierOctet <= 255) return "Classe E";
        return "Classe inconnue";
    }  
}
