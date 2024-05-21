package JavaDevProject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientDetailsController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label serviceLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label descriptionLabel;

    private Clients client;

    // Method to initialize the data
    public void initData(Clients client) {
        this.client = client;
        nameLabel.setText("Name: " + client.getClientName() + " " + client.getClientPrenam());
        emailLabel.setText("Email: " + client.getClientEmail());
        serviceLabel.setText("Service: " + client.getClientServiceProposer());
        phoneLabel.setText("Phone: " + client.getClientTelephone());
        addressLabel.setText("Address: " + client.getClientAddress());
        cityLabel.setText("City: " + client.getClientCity());
        descriptionLabel.setText("Description: " + client.getClientDescription());
    }
}
