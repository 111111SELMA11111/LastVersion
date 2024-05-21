package JavaDevProject;
import java.util.Date;
public class Clients {
    private String ClientId;
    private String ClientName;
    private String ClientPrenam;
    private String ClientSex;
    private String ClientEmail;
    private int ClientTelephone;
    private String ClientAddress;
    private String ClientCity;
    private Date ClientDateInscription; // Automatically added
    private String ClientServiceProposer;
    private Date ClientDateService;
    private String ClientTypeService;
    private String ClientDescription;
    private String ClientStatus;

    public Clients(String clientId, String clientName, String clientPrenam, String clientSex, String clientEmail, int clientTelephone, String clientAddress, String clientCity, Date clientDateInscription, String clientServiceProposer, Date clientDateService, String clientTypeService, String clientDescription, String clientStatus) {
        ClientId = clientId;
        ClientName = clientName;
        ClientPrenam = clientPrenam;
        ClientSex = clientSex;
        ClientEmail = clientEmail;
        ClientTelephone = clientTelephone;
        ClientAddress = clientAddress;
        ClientCity = clientCity;
        ClientDateInscription = clientDateInscription;
        ClientServiceProposer = clientServiceProposer;
        ClientDateService = clientDateService;
        ClientTypeService = clientTypeService;
        ClientDescription = clientDescription;
        ClientStatus = clientStatus;
    }

    public Clients() {
    }

    public String getClientId() {
        return ClientId;
    }
    public void setClientId(String clientId) {
        ClientId = clientId;
    }
    public String getClientName() {
        return ClientName;
    }
    public void setClientName(String clientName) {
        ClientName = clientName;
    }
    public String getClientPrenam() {
        return ClientPrenam;
    }
    public void setClientPrenam(String clientPrenam) {
        ClientPrenam = clientPrenam;
    }
    public String getClientSex() {
        return ClientSex;
    }
    public void setClientSex(String clientSex) {
        ClientSex = clientSex;
    }
    public String getClientEmail() {
        return ClientEmail;
    }
    public void setClientEmail(String clientEmail) {
        ClientEmail = clientEmail;
    }
    public int getClientTelephone() {
        return ClientTelephone;
    }
    public void setClientTelephone(int clientTelephone) {
        ClientTelephone = clientTelephone;
    }
    public String getClientAddress() {
        return ClientAddress;
    }
    public void setClientAddress(String clientAddress) {
        ClientAddress = clientAddress;
    }
    public String getClientCity() {
        return ClientCity;
    }
    public void setClientCity(String clientCity) {
        ClientCity = clientCity;
    }
    public Date getClientDateInscription() {
        return ClientDateInscription;
    }
    public void setClientDateInscription(Date clientDateInscription) {
        ClientDateInscription = clientDateInscription;
    }
    public String getClientServiceProposer() {
        return ClientServiceProposer;
    }
    public void setClientServiceProposer(String clientServiceProposer) {
        ClientServiceProposer = clientServiceProposer;
    }
    public Date getClientDateService() {
        return ClientDateService;
    }
    public void setClientDateService(Date clientDateService) {
        ClientDateService = clientDateService;
    }
    public String getClientTypeService() {
        return ClientTypeService;
    }
    public void setClientTypeService(String clientTypeService) {
        ClientTypeService = clientTypeService;
    }
    public String getClientDescription() {
        return ClientDescription;
    }
    public void setClientDescription(String clientDescription) {
        ClientDescription = clientDescription;
    }
    public String getClientStatus() {
        return ClientStatus;
    }
    public void setClientStatus(String clientStatus) {
        ClientStatus = clientStatus;
    }

}
