package JavaDevProject;

import javafx.scene.control.CheckBox;

import java.util.Date;

public class ClientsEnAtt {

    //prospect_id (INT, PK): Identifiant unique pour chaque prospect.
    //nom (VARCHAR): Nom du prospect.
    //prenom (VARCHAR): Prénom du prospect.
    //email (VARCHAR): Adresse email du prospect.
    //telephone (VARCHAR): Numéro de téléphone du prospect.
    //adresse (VARCHAR): Adresse physique du prospect.
    //date_inscription (DATE): Date à laquelle le prospect a été ajouté à la base.


    private String ProspectId ;
    private String ProspectName;
    private String ProspectPrenam;
    private String ProspectSex ;
    private String ProspectEmail ;
    private int ProspectTelephone ;
    private String ProspectAddress ;
    private String ProspectCity ;
    private Date ProspectDateInscription ; //Ajouter automatiquement
    private String ProspectServiceProposer ;
    private Date ProspectDateService ;
    private String ProspectTypeService ;
    private String ProspectDescription ;
    private String ProspectStatus ;

    public ClientsEnAtt(String prospectId, String prospectName, String prospectPrenam, String prospectSex, String prospectEmail, int prospectTelephone, String prospectAddress, String prospectCity, Date prospectDateInscription, String prospectServiceProposer, Date prospectDateService, String prospectTypeService, String prospectDescription, String prospectStatus) {
        ProspectId = prospectId;
        ProspectName = prospectName;
        ProspectPrenam = prospectPrenam;
        ProspectSex = prospectSex;
        ProspectEmail = prospectEmail;
        ProspectTelephone = prospectTelephone;
        ProspectAddress = prospectAddress;
        ProspectCity = prospectCity;
        ProspectDateInscription = prospectDateInscription;
        ProspectServiceProposer = prospectServiceProposer;
        ProspectDateService = prospectDateService;
        ProspectTypeService = prospectTypeService;
        ProspectDescription = prospectDescription;
        ProspectStatus = prospectStatus;
    }

    public ClientsEnAtt() {

    }

    public String getProspectId() {
        return ProspectId;
    }

    public void setProspectId(String prospectId) {
        ProspectId = prospectId;
    }

    public String getProspectName() {
        return ProspectName;
    }

    public void setProspectName(String prospectName) {
        ProspectName = prospectName;
    }

    public String getProspectPrenam() {
        return ProspectPrenam;
    }

    public void setProspectPrenam(String prospectPrenam) {
        ProspectPrenam = prospectPrenam;
    }

    public String getProspectSex() {
        return ProspectSex;
    }

    public void setProspectSex(String prospectSex) {
        ProspectSex = prospectSex;
    }

    public String getProspectEmail() {
        return ProspectEmail;
    }

    public void setProspectEmail(String prospectEmail) {
        ProspectEmail = prospectEmail;
    }

    public int getProspectTelephone() {
        return ProspectTelephone;
    }

    public void setProspectTelephone(int prospectTelephone) {
        ProspectTelephone = prospectTelephone;
    }

    public String getProspectAddress() {
        return ProspectAddress;
    }

    public void setProspectAddress(String prospectAddress) {
        ProspectAddress = prospectAddress;
    }

    public String getProspectCity() {
        return ProspectCity;
    }

    public void setProspectCity(String prospectCity) {
        ProspectCity = prospectCity;
    }

    public Date getProspectDateInscription() {
        return ProspectDateInscription;
    }

    public void setProspectDateInscription(Date prospectDateInscription) {
        ProspectDateInscription = prospectDateInscription;
    }

    public String getProspectServiceProposer() {
        return ProspectServiceProposer;
    }

    public void setProspectServiceProposer(String prospectServiceProposer) {
        ProspectServiceProposer = prospectServiceProposer;
    }

    public Date getProspectDateService() {
        return ProspectDateService;
    }

    public void setProspectDateService(Date prospectDateService) {
        ProspectDateService = prospectDateService;
    }

    public String getProspectTypeService() {
        return ProspectTypeService;
    }

    public void setProspectTypeService(String prospectTypeService) {
        ProspectTypeService = prospectTypeService;
    }

    public String getProspectDescription() {
        return ProspectDescription;
    }

    public void setProspectDescription(String prospectDescription) {
        ProspectDescription = prospectDescription;
    }

    public String getProspectStatus() {
        return ProspectStatus;
    }

    public void setProspectStatus(String prospectStatus) {
        ProspectStatus = prospectStatus;
    }


        // Existing class code...

        @Override
        public String toString() {
            return "ClientsEnAtt{" +
                    "Prospect_Id='" + ProspectId + '\'' +
                    ", Prospect_Name='" + ProspectName + '\'' +
                    ", Prospect_Prenam='" + ProspectPrenam + '\'' +
                    ", Prospect_Sex='" + ProspectSex + '\'' +
                    ", Prospect_Email='" + ProspectEmail + '\'' +
                    ", Prospect_Telephone=" + ProspectTelephone +
                    ", Prospect_Address='" + ProspectAddress + '\'' +
                    ", Prospect_City='" + ProspectCity + '\'' +
                    ", Prospect_DateInscription=" + ProspectDateInscription +
                    ", Prospect_ServiceProposer='" + ProspectServiceProposer + '\'' +
                    ", Prospect_DateService=" + ProspectDateService +
                    ", Prospect_TypeService='" + ProspectTypeService + '\'' +
                    ", Prospect_Description='" + ProspectDescription + '\'' +
                    ", Prospect_Status='" + ProspectStatus + '\'' +
                    '}';
        }


}
