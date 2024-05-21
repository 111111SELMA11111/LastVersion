package JavaDevProject;

public class Vehicule {

    private String vehicule_id;
    private String mode_id;
    private String immatriculation;
    private String Type;
    private String capacite;
    private String status;

    public Vehicule(String vehicule_id, String mode_id, String immatriculation,String Type, String capacite, String status) {this.vehicule_id = vehicule_id;this.mode_id = mode_id;this.immatriculation = immatriculation;this.Type=Type;this.capacite = capacite;this.status = status;}
    public Vehicule() {}
    public String getVehicule_id() {return vehicule_id;}
    public void setVehicule_id(String vehicule_id) {this.vehicule_id = vehicule_id;}
    public String getMode_id() {return mode_id;}
    public void setMode_id(String mode_id) {this.mode_id = mode_id;}
    public String getImmatriculation() {return immatriculation;}
    public void setImmatriculation(String immatriculation) {this.immatriculation = immatriculation;}
    public String getCapacite() {return capacite;}
    public void setCapacite(String capacite) {this.capacite = capacite;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getType() {return Type;}
    public void setType(String type) {Type = type;}

    @Override
    public String toString() {
        return "Vehicule{" +
                "vehicule_id='" + vehicule_id + '\'' +
                ", mode_id='" + mode_id + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", capacite='" + capacite + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
