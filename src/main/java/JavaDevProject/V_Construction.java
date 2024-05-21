package JavaDevProject;

public class V_Construction {
    private String vehicule_id; // Corrected variable name
    private String commonde_id;
    private String immatriculation;
    private String type;
    private String capacite;
    private String status;

    // Constructor with parameters
    public V_Construction(String vehicule_id, String commonde_id, String immatriculation, String type, String capacite, String status) {
        this.vehicule_id = vehicule_id;
        this.commonde_id = commonde_id;
        this.immatriculation = immatriculation;
        this.type = type;
        this.capacite = capacite;
        this.status = status;
    }

    // Default constructor
    public V_Construction() {
    }


    // Getters and setters
    public String getVehicule_id() {
        return vehicule_id;
    }

    public void setVehicule_id(String vehicule_id) {
        this.vehicule_id = vehicule_id;
    }

    public String getCommonde_id() {
        return commonde_id;
    }

    public void setCommonde_id(String commonde_id) {
        this.commonde_id = commonde_id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method
    @Override
    public String toString() {
        return "V_Construction{" +
                "commonde_id='" + commonde_id + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", type='" + type + '\'' +
                ", capacite='" + capacite + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
