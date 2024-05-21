package JavaDevProject;

import java.time.LocalDate;

public class Commande {
    private int id;
    private int clientId;
    private String commandeId;
    private String fer;
    private String beton;
    private String caillase;
    private String tempsProjet;
    private String nbreOuvrier;
    private String distance;
    private LocalDate departTemps;
    private LocalDate arriveeTemps;
    private boolean status;

    public Commande(int id, int clientId, String commandeId, String fer, String beton, String caillase, String tempsProjet, String nbreOuvrier, String distance, LocalDate departTemps, LocalDate arriveeTemps, boolean status) {
        this.id = id;
        this.clientId = clientId;
        this.commandeId = commandeId;
        this.fer = fer;
        this.beton = beton;
        this.caillase = caillase;
        this.tempsProjet = tempsProjet;
        this.nbreOuvrier = nbreOuvrier;
        this.distance = distance;
        this.departTemps = departTemps;
        this.arriveeTemps = arriveeTemps;
        this.status = status;
    }

    public Commande(String commandeId, String fer, String beton, String caillase, String tempsProjet, String distance, String nbreOuvrier) {
        this.commandeId = commandeId;
        this.fer = fer;
        this.beton = beton;
        this.caillase = caillase;
        this.tempsProjet = tempsProjet;
        this.distance = distance;
        this.nbreOuvrier=nbreOuvrier;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(String commandeId) {
        this.commandeId = commandeId;
    }

    public String getFer() {
        return fer;
    }

    public void setFer(String fer) {
        this.fer = fer;
    }

    public String getBeton() {
        return beton;
    }

    public void setBeton(String beton) {
        this.beton = beton;
    }

    public String getCaillase() {
        return caillase;
    }

    public void setCaillase(String caillase) {
        this.caillase = caillase;
    }

    public String getTempsProjet() {
        return tempsProjet;
    }

    public void setTempsProjet(String tempsProjet) {
        this.tempsProjet = tempsProjet;
    }

    public String getNbreOuvrier() {
        return nbreOuvrier;
    }

    public void setNbreOuvrier(String nbreOuvrier) {
        this.nbreOuvrier = nbreOuvrier;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public LocalDate getDepartTemps() {
        return departTemps;
    }

    public void setDepartTemps(LocalDate departTemps) {
        this.departTemps = departTemps;
    }

    public LocalDate getArriveeTemps() {
        return arriveeTemps;
    }

    public void setArriveeTemps(LocalDate arriveeTemps) {
        this.arriveeTemps = arriveeTemps;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
