package JavaDevProject;

public class Order {
    private int id;
    private int clientId;
    private String typeConstruction;
    private String commandeId;
    private String adresse;
    private String description;
    private String lanced ;

    public Order(int id, int clientId, String typeConstruction, String commandeId, String adresse, String description ,String lanced) {
        this.id = id;
        this.clientId = clientId;
        this.typeConstruction = typeConstruction;
        this.commandeId = commandeId;
        this.adresse = adresse;
        this.description = description;
        this.lanced = lanced ;
    }

    public String getLanced() {
        return lanced;
    }

    public void setLanced(String lanced) {
        this.lanced = lanced;
    }

    // Getters et setters
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

    public String getTypeConstruction() {
        return typeConstruction;
    }

    public void setTypeConstruction(String typeConstruction) {
        this.typeConstruction = typeConstruction;
    }

    public String getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(String commandeId) {
        this.commandeId = commandeId;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
