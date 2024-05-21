package JavaDevProject;
import java.util.Date;
public class ClientRefused {
    private String RefusedId ;
    private String RefusedName;
    private String RefusedPrenam;
    private String RefusedSex ;
    private String RefusedEmail ;
    private int RefusedTelephone ;
    private String RefusedAddress ;
    private String RefusedCity ;
    private Date RefusedDateInscription ; //Ajouter automatiquement
    private String RefusedServiceProposer ;
    private Date RefusedDateService ;
    private String RefusedTypeService ;
    private String RefusedDescription ;
    private String RefusedStatus ;
    public ClientRefused(String refusedId, String refusedName, String refusedPrenam, String refusedSex, String refusedEmail, int refusedTelephone, String refusedAddress, String refusedCity, Date refusedDateInscription, String refusedServiceProposer, Date refusedDateService, String refusedTypeService, String refusedDescription, String refusedStatus) {
        RefusedId = refusedId;
        RefusedName = refusedName;
        RefusedPrenam = refusedPrenam;
        RefusedSex = refusedSex;
        RefusedEmail = refusedEmail;
        RefusedTelephone = refusedTelephone;
        RefusedAddress = refusedAddress;
        RefusedCity = refusedCity;
        RefusedDateInscription = refusedDateInscription;
        RefusedServiceProposer = refusedServiceProposer;
        RefusedDateService = refusedDateService;
        RefusedTypeService = refusedTypeService;
        RefusedDescription = refusedDescription;
        RefusedStatus = refusedStatus;
    }
    public ClientRefused() {
    }
    public String getRefusedId() {
        return RefusedId;
    }
    public void setRefusedId(String refusedId) {
        RefusedId = refusedId;
    }
    public String getRefusedName() {
        return RefusedName;
    }
    public void setRefusedName(String refusedName) {
        RefusedName = refusedName;
    }
    public String getRefusedPrenam() {
        return RefusedPrenam;
    }
    public void setRefusedPrenam(String refusedPrenam) {
        RefusedPrenam = refusedPrenam;
    }
    public String getRefusedSex() {
        return RefusedSex;
    }
    public void setRefusedSex(String refusedSex) {
        RefusedSex = refusedSex;
    }
    public String getRefusedEmail() {
        return RefusedEmail;
    }
    public void setRefusedEmail(String refusedEmail) {
        RefusedEmail = refusedEmail;
    }
    public int getRefusedTelephone() {
        return RefusedTelephone;
    }
    public void setRefusedTelephone(int refusedTelephone) {
        RefusedTelephone = refusedTelephone;
    }
    public String getRefusedAddress() {
        return RefusedAddress;
    }
    public void setRefusedAddress(String refusedAddress) {
        RefusedAddress = refusedAddress;
    }
    public String getRefusedCity() {
        return RefusedCity;
    }
    public void setRefusedCity(String refusedCity) {
        RefusedCity = refusedCity;
    }
    public Date getRefusedDateInscription() {
        return RefusedDateInscription;
    }
    public void setRefusedDateInscription(Date refusedDateInscription) {
        RefusedDateInscription = refusedDateInscription;
    }
    public String getRefusedServiceProposer() {
        return RefusedServiceProposer;
    }
    public void setRefusedServiceProposer(String refusedServiceProposer) {
        RefusedServiceProposer = refusedServiceProposer;
    }
    public Date getRefusedDateService() {
        return RefusedDateService;
    }
    public void setRefusedDateService(Date refusedDateService) {
        RefusedDateService = refusedDateService;
    }
    public String getRefusedTypeService() {
        return RefusedTypeService;
    }
    public void setRefusedTypeService(String refusedTypeService) {
        RefusedTypeService = refusedTypeService;
    }
    public String getRefusedDescription() {
        return RefusedDescription;
    }
    public void setRefusedDescription(String refusedDescription) {
        RefusedDescription = refusedDescription;
    }
    public String getRefusedStatus() {
        return RefusedStatus;
    }
    public void setRefusedStatus(String refusedStatus) {
        RefusedStatus = refusedStatus;
    }
    @Override
    public String toString() {
        return "ClientRefused{" +
                "RefusedId='" + RefusedId + '\'' +
                ", RefusedName='" + RefusedName + '\'' +
                ", RefusedPrenam='" + RefusedPrenam + '\'' +
                ", RefusedSex='" + RefusedSex + '\'' +
                ", RefusedEmail='" + RefusedEmail + '\'' +
                ", RefusedTelephone=" + RefusedTelephone +
                ", RefusedAddress='" + RefusedAddress + '\'' +
                ", RefusedCity='" + RefusedCity + '\'' +
                ", RefusedDateInscription=" + RefusedDateInscription +
                ", RefusedServiceProposer='" + RefusedServiceProposer + '\'' +
                ", RefusedDateService=" + RefusedDateService +
                ", RefusedTypeService='" + RefusedTypeService + '\'' +
                ", RefusedDescription='" + RefusedDescription + '\'' +
                ", RefusedStatus='" + RefusedStatus + '\'' +
                '}';
    }
}