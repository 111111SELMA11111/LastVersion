package JavaDevProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private AnchorPane anchorPane3;
    @FXML
    private AnchorPane anchorPane4;
    @FXML
    private AnchorPane anchorPane5;


    // Controllers for each AnchorPane
    private ClientsController clientsController;
    private ClientProfilesController clientProfilesController;
    private VehiculeController vehiculeController;
    private V_ConstructionController constructionController;
    private CommandeController commandeController;



    @FXML
    public void initialize() {
        try {
            loadClients();
            loadClientProfiles();
            loadVehicules();
            loadV_Construction();
            loadCommande();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadClients() throws IOException {
        FXMLLoader clientsLoader = new FXMLLoader(getClass().getResource("/files/myjavafx/Clients.fxml"));
        anchorPane1.getChildren().add(clientsLoader.load());
        clientsController = clientsLoader.getController();
    }

    private void loadClientProfiles() throws IOException {
        FXMLLoader clientProfilesLoader = new FXMLLoader(getClass().getResource("/files/myjavafx/ClientProfiles.fxml"));
        anchorPane2.getChildren().add(clientProfilesLoader.load());
        clientProfilesController = clientProfilesLoader.getController();
    }

    private void loadVehicules() throws IOException {
        FXMLLoader vehiculeLoader = new FXMLLoader(getClass().getResource("/files/myjavafx/Vehicule.fxml"));
        anchorPane3.getChildren().add(vehiculeLoader.load());
        vehiculeController = vehiculeLoader.getController();
    }

    private void loadV_Construction() throws IOException {
        FXMLLoader v_constructionLoader = new FXMLLoader(getClass().getResource("/files/myjavafx/V_Construction.fxml"));
        anchorPane4.getChildren().add(v_constructionLoader.load());
        constructionController = v_constructionLoader.getController();
    }

    private void loadCommande() throws IOException {
        FXMLLoader loadcommnde = new FXMLLoader(getClass().getResource("/files/myjavafx/Comm.fxml"));
        anchorPane5.getChildren().add(loadcommnde.load());
        commandeController = loadcommnde.getController();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String anchorPaneIdToShow = clickedButton.getUserData().toString();
        switchAnchorPane(anchorPaneIdToShow);
    }

    private void switchAnchorPane(String anchorPaneIdToShow) {
        anchorPane1.setVisible(anchorPaneIdToShow.equals("anchorPane1"));
        anchorPane2.setVisible(anchorPaneIdToShow.equals("anchorPane2"));
        anchorPane3.setVisible(anchorPaneIdToShow.equals("anchorPane3"));
        anchorPane4.setVisible(anchorPaneIdToShow.equals("anchorPane4"));
        anchorPane5.setVisible(anchorPaneIdToShow.equals("anchorPane5"));


    }

    public CommandeController getCommandeController() {
        return commandeController;
    }

    public V_ConstructionController getConstructionController() {
        return constructionController;
    }

    public VehiculeController getVehiculeController() {
        return vehiculeController;
    }

    public ClientProfilesController getClientProfilesController() {
        return clientProfilesController;
    }

    public ClientsController getClientsController() {
        return clientsController;
    }



}
