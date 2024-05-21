package JavaDevProject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientsController {
    @FXML
    private TableView<ClientsEnAtt> tableprospect ;
    @FXML
    private TableView<Clients> tableClient ;
    @FXML
    private TableView<ClientRefused> tableprospectrefused;

    @FXML
    private TableColumn<ClientsEnAtt, String> descriptionColumn;

    @FXML
    private TableColumn<ClientsEnAtt, String> descriptionColumn1;
    private ClientBaseHandler clientBaseHandler ;
    @FXML
    private ChoiceBox<String> choiceBox;
    private boolean choiceBoxInitialized = false;
    @FXML
    private Button updateButton;
    private Stage stage;


    @FXML
    private TextField searchField;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public ClientsController() {
        this.clientBaseHandler = new ClientBaseHandler();
    }
    @FXML
    public void initialize() {



        if (choiceBox == null) {
            System.out.println("ChoiceBox is null");
        } else {
            choiceBox.getItems().addAll("Accepted", "Not Accepted", "Waited");

            tableprospect.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    refreshTable();
                }
            });
        }
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            handleSearch();
        });



        descriptionColumn1.setCellValueFactory(new PropertyValueFactory<>("ClientDescription"));
        descriptionColumn1.setCellFactory(col -> new WrappingTableCell<>());


        setupTableViews();
        setupListeners();
        refreshTable();
    }
    private void setupTableViews() {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("ProspectDescription"));
        descriptionColumn.setCellFactory(col -> new WrappingTableCell<>());
    }

    private void setupListeners() {
        tableprospect.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                refreshTable();
            }
        });
        searchField.textProperty().addListener((observable, oldValue, newValue) -> handleSearch());
    }

    @FXML
    void refreshTable() {
        if (tableprospect != null) {
            System.out.println("tableprospect is fulllllll");        } else {
            System.out.println("tableprospect is null");
        }
        ObservableList<ClientsEnAtt> clientsEnAtts = FXCollections.observableArrayList(clientBaseHandler.getItems());
        ObservableList<Clients> clients = FXCollections.observableArrayList(clientBaseHandler.getItemss());
        tableprospect.setItems(clientsEnAtts);
        tableClient.setItems(clients);
        handleSearch();

    }
    @FXML
    private void handleUpdateButton(ActionEvent event) {
        // Get selected client from table view
        ClientsEnAtt selectedClient = tableprospect.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            // Get selected status from choice box
            String selectedStatus = choiceBox.getValue();
            // Update client status
            selectedClient.setProspectStatus(selectedStatus);
            clientBaseHandler.updateClientStatus(selectedClient);
            // Refresh table view
            refreshTable();
        }
    }

    @FXML
    private void showRefusedClientsTable(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/myjavafx/ClientRefused.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the FXML file: " + e.getMessage());
        }
    }

    @FXML
    public void handleSearch() {
        String searchText = searchField.getText().toLowerCase().trim();
        FilteredList<ClientsEnAtt> filteredData = new FilteredList<>(FXCollections.observableArrayList(clientBaseHandler.getItems()), p -> true);

        filteredData.setPredicate(client -> {
            if (searchText.isEmpty()) {
                return true;
            } else {
                return client.getProspectName().toLowerCase().contains(searchText);
            }
        });

        SortedList<ClientsEnAtt> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableprospect.comparatorProperty());
        tableprospect.setItems(sortedData);
    }

}
