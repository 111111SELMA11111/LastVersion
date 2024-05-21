package JavaDevProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandeController {

    @FXML
    private CheckBox BetonCamionCheckBox, CompacteurCheckBox, GrueCheckBox, PompBetonCheckBox, camionCheckBox, bateauCheckBox, avionCheckBox;
    @FXML
    private FlowPane ordersFlowPane;
    @FXML
    private TextFlow textFlowId;
    @FXML
    private TextField ferid, betonid, caillaseid, tempsprojetid, nbreouvrierid, distanceid;
    @FXML
    private DatePicker daparttempsid, arriveetempsid;
    @FXML
    private Button ajoutbuttonid;
    @FXML
    private CheckBox statusid;
    @FXML
    private TableView tableviewid, tablematerielid, tablegeneralid;

    private final ClientBaseHandler dataManager = new ClientBaseHandler();
    private VBox selectedVBox = null;
    private List<String> selectedVehicleTypes = new ArrayList<>();

    @FXML
    public void initialize() {
        displayOrders();
        ajoutbuttonid.setOnAction(event -> handleAffectAction());
        refreshTable();
    }

    private void collectSelectedConstructeurTypes() {
        selectedVehicleTypes.clear();
        if (BetonCamionCheckBox.isSelected()) selectedVehicleTypes.add("BetonCamion");
        if (CompacteurCheckBox.isSelected()) selectedVehicleTypes.add("Compacteur");
        if (GrueCheckBox.isSelected()) selectedVehicleTypes.add("Grue");
        if (PompBetonCheckBox.isSelected()) selectedVehicleTypes.add("Pompeabeton");
    }

    private void collectSelectedVehicleTypes() {
        selectedVehicleTypes.clear();
        if (camionCheckBox.isSelected()) selectedVehicleTypes.add("Camion");
        if (bateauCheckBox.isSelected()) selectedVehicleTypes.add("Bateau");
        if (avionCheckBox.isSelected()) selectedVehicleTypes.add("Avion");
    }

    public void handleLivraisonButtonClick() {
        try {
            collectSelectedVehicleTypes();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/myjavafx/Comm_Details.fxml"));
            Parent root = loader.load();
            CommDetails detailsController = loader.getController();
            detailsController.setSelectedVehicleTypes(selectedVehicleTypes);
            detailsController.refreshAll();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Détails de Commande");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Could not load the details page.");
        }
    }

    private void displayOrders() {
        List<Order> orders = dataManager.getOngoingOrders();
        if (orders == null || orders.isEmpty()) {
            System.out.println("No order data available.");
            return;
        }

        ordersFlowPane.getChildren().clear();

        for (Order order : orders) {
            VBox orderBox = createOrderBox(order);
            ordersFlowPane.getChildren().add(orderBox);
        }
    }

    private VBox createOrderBox(Order order) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: #333; -fx-border-width: 2; -fx-background-color: #EEE; -fx-cursor: hand;");
        StackPane imageContainer = createImageContainer();
        Label descriptionLabel = createDescriptionLabel(order);

        box.setUserData(order);
        box.getChildren().addAll(
                imageContainer,
                new Label("Order ID: " + order.getCommandeId()),
                new Label("Client ID: " + order.getClientId()),
                new Label("Adresse: " + order.getAdresse()),
                descriptionLabel
        );

        box.setOnMouseClicked(e -> {
            if (selectedVBox != null) {
                selectedVBox.setStyle("-fx-border-color: #333; -fx-border-width: 2; -fx-background-color: #EEE;");
            }
            selectedVBox = box;
            selectedVBox.setStyle("-fx-background-color: #ccd; -fx-border-color: #555; -fx-border-width: 2;");
            AppState.setSelectedOrderId(order.getCommandeId());
            updateTextFlow(descriptionLabel.getText());
        });

        return box;
    }

    private StackPane createImageContainer() {
        StackPane imageContainer = new StackPane();
        imageContainer.setPadding(new Insets(5));

        Image image;
        try (InputStream imageStream = getClass().getResourceAsStream("/pics/Indus.jpg")) {
            if (imageStream == null) {
                throw new IllegalArgumentException("Image file not found!");
            }
            image = new Image(imageStream);
        } catch (IOException e) {
            System.err.println("Failed to load image: " + e.getMessage());
            return imageContainer;
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);
        imageContainer.getChildren().add(imageView);

        return imageContainer;
    }

    private Label createDescriptionLabel(Order order) {
        Label descriptionLabel = new Label("Description: " + order.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(180);
        return descriptionLabel;
    }

    private void updateTextFlow(String description) {
        textFlowId.getChildren().clear();
        Text text = new Text(description);
        textFlowId.getChildren().add(text);
    }

    public void handleConstrButtonClick() {
        collectSelectedConstructeurTypes();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/myjavafx/CommCons_details.fxml"));
            Parent root = loader.load();
            CommConsController detailsController = loader.getController();
            detailsController.setSelectedVehicleTypes(selectedVehicleTypes);
            detailsController.refreshAll();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Détails de Commande");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void handleAffectAction() {
        System.out.println("Selected VBox: " + selectedVBox);
        System.out.println("User data: " + selectedVBox.getUserData());

        if (selectedVBox == null || selectedVBox.getUserData() == null) {
            System.out.println("Please select an order first.");
            return;
        }

        Order selectedOrder = (Order) selectedVBox.getUserData();
        if (selectedOrder == null) {
            System.out.println("Selected order is null.");
            return;
        }

        int clientId = selectedOrder.getClientId();
        String commandeId = selectedOrder.getCommandeId();
        String ferValue = ferid.getText();
        String betonValue = betonid.getText();
        String caillaseValue = caillaseid.getText();
        String tempsProjetValue = tempsprojetid.getText();
        String nbreOuvrierValue = nbreouvrierid.getText();
        String distanceValue = distanceid.getText();
        LocalDate departTempsValue = daparttempsid.getValue();
        LocalDate arriveeTempsValue = arriveetempsid.getValue();
        if (departTempsValue == null || arriveeTempsValue == null) {
            System.out.println("Les champs de date ne peuvent pas être vides.");
            return;
        }
        boolean statusValue = statusid.isSelected();

        System.out.println("Client ID: " + clientId);
        System.out.println("Command ID: " + commandeId);
        System.out.println("Fer: " + ferValue);
        System.out.println("Beton: " + betonValue);
        System.out.println("Caillase: " + caillaseValue);
        System.out.println("Temps Projet: " + tempsProjetValue);
        System.out.println("Nbre Ouvrier: " + nbreOuvrierValue);
        System.out.println("Distance: " + distanceValue);
        System.out.println("Depart Temps: " + departTempsValue);
        System.out.println("Arrivee Temps: " + arriveeTempsValue);
        System.out.println("Status: " + statusValue);

        ClientBaseHandler clientBaseHandler = new ClientBaseHandler();
        clientBaseHandler.insertDataWithClientAndCommandId(String.valueOf(clientId), commandeId, ferValue, betonValue, caillaseValue, tempsProjetValue, nbreOuvrierValue,
                distanceValue, departTempsValue, arriveeTempsValue, statusValue);
    }

    @FXML
    void refreshTable() {
        ObservableList<Commande> vDisponibiliys = FXCollections.observableArrayList(dataManager.getMateriel());
        tablematerielid.setItems(vDisponibiliys);
        ObservableList<CommandeDetail> commandeDetails = FXCollections.observableArrayList(dataManager.getCommandeDetails());
        tablegeneralid.setItems(commandeDetails);
    }
}
