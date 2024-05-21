package JavaDevProject;

import javafx.beans.property.SimpleStringProperty;

public class CommandeDetail {
    private SimpleStringProperty clientId;
    private SimpleStringProperty commandeId;
    private SimpleStringProperty materieletMasse;
    private SimpleStringProperty vehiculesLivraison;
    private SimpleStringProperty dateDepartArrivee;


    public CommandeDetail(String clientId, String commandeId, String materieletMasse, String vehiculesLivraison, String dateDepartArrivee) {
        this.clientId = new SimpleStringProperty(clientId);
        this.commandeId = new SimpleStringProperty(commandeId);
        this.materieletMasse = new SimpleStringProperty(materieletMasse);
        this.vehiculesLivraison = new SimpleStringProperty(vehiculesLivraison);
        this.dateDepartArrivee = new SimpleStringProperty(dateDepartArrivee);
    }

    // Getters and setters for each property
    public String getClientId() { return clientId.get(); }
    public String getCommandeId() { return commandeId.get(); }
    public String getMaterieletMasse() { return materieletMasse.get(); }
    public String getVehiculesLivraison() { return vehiculesLivraison.get(); }
    public String getDateDepartArrivee() { return dateDepartArrivee.get(); }

}
