package JavaDevProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ClientProfilesController {
    @FXML
    private FlowPane clientFlowPane;

    @FXML
    private Button refreshButton;

    private boolean initialized = false;

    public void initialize() {
        if (!initialized) {
            displayClients();
            initialized = true;
        }
    }

    @FXML
    private void displayClients() {
        List<Clients> clients = new ClientBaseHandler().getItemss();
        if (clients == null || clients.isEmpty()) {
            System.out.println("No client data available.");
            return;
        }

        clientFlowPane.getChildren().clear(); // Clear existing content

        for (Clients client : clients) {
            VBox clientBox = createClientBox(client);
            clientFlowPane.getChildren().add(clientBox);
        }
    }

    private static final String HUMAN_IMAGE_PATH = "/pics/AllVehicule.jpg";

    private VBox createClientBox(Clients client) {
        VBox box = new VBox(5);
        box.getStyleClass().add("vbox-client");

        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: #333; -fx-border-width: 2; -fx-background-color: #EEE; -fx-cursor: hand;");

        try (InputStream imageStream = getClass().getResourceAsStream(HUMAN_IMAGE_PATH)) {
            ImageView imageView = new ImageView(new Image(imageStream));
            imageView.setFitWidth(200);
            imageView.setFitHeight(100);
            box.getChildren().addAll(
                    imageView, new Label("Nom : " +
                    client.getClientName() + " " +
                    client.getClientPrenam()), new Label("Email : " +
                    client.getClientEmail()), new Label("Service : " +
                    client.getClientServiceProposer()));
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
        }

        box.setOnMouseClicked(e -> showClientProfile(client));
        return box;
    }

    private void showClientProfile(Clients client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/myjavafx/ClientDetails.fxml"));
            AnchorPane root = loader.load();
            ClientDetailsController controller = loader.getController();
            controller.initData(client);

            Stage stage = new Stage();
            stage.setTitle("Client Profile");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to load the FXML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void refreshClients() {
        clientFlowPane.getChildren().clear(); // Clear existing content before refreshing
        displayClients(); // Refresh the client data
    }
}
