/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.model;

/**
 *
 * @author Mahone
 */
public class Recommandation {
  private String titre;
    private String priorite;
    private String message;
    
    public Recommandation(String titre, String priorite, String message) {
        setTitre(titre);
        setPriorite(priorite);
        setMessage(message);
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        if (titre == null || titre.isEmpty()) {
            this.titre = "Recommandation sans titre";
        } else {
            this.titre = titre;
        }
    }
    
    public String getPriorite() {
        return priorite;
    }
    
    public void setPriorite(String priorite) {
        if (priorite == null || priorite.isEmpty()) {
            this.priorite = "NORMALE";
        } else {
            this.priorite = priorite;
        }
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        if (message == null || message.isEmpty()) {
            this.message = "Aucun détail fourni.";
        } else {
            this.message = message;
        }
    }
    
    public void afficher() {
        String symbole;
        switch (priorite) {
            case "ÉLEVÉE":
                symbole = "🔴";
                break;
            case "MOYENNE":
                symbole = "🟠";
                break;
            default:
                symbole = "🟢";
        }
        System.out.println("  " + symbole + " [" + priorite + "] " + titre + " : " + message);
    }   
}
