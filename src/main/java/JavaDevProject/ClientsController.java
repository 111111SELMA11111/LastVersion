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
 public void generatePDF(Clients client) {
        try {
            var doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("contrat_" + client.getClientName() + ".pdf"));
            doc.open();

            var bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            var title = new Paragraph("Contrat de Service\n\n", bold);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(title);

            var clientInfo = new Paragraph("Informations du Client\n\n");

            // Ajouter les informations du client sous forme de paragraphes
            clientInfo.add("ID Client: " + client.getClientId() + "\n");
            clientInfo.add("Nom: " + client.getClientName() + "\n");
            clientInfo.add("Prénom: " + client.getClientPrenam() + "\n");
            clientInfo.add("Sexe: " + client.getClientSex() + "\n");
            clientInfo.add("Email: " + client.getClientEmail() + "\n");
            clientInfo.add("Téléphone: " + client.getClientTelephone() + "\n");
            clientInfo.add("Adresse: " + client.getClientAddress() + "\n");
            clientInfo.add("Ville: " + client.getClientCity() + "\n");
            clientInfo.add("Date d'Inscription: " + client.getClientDateInscription() + "\n");
            clientInfo.add("Service Proposé: " + client.getClientServiceProposer() + "\n");
            clientInfo.add("Date du Service: " + client.getClientDateService() + "\n");
            clientInfo.add("Type de Service: " + client.getClientTypeService() + "\n");
            clientInfo.add("Description: " + client.getClientDescription() + "\n");
            clientInfo.add("Statut: " + client.getClientStatus() + "\n");

            doc.add(clientInfo);

            var signature = new Paragraph("\n\n\n\nSignature: ____________________");
            signature.setAlignment(Paragraph.ALIGN_RIGHT);
            doc.add(signature);

            doc.close();
            System.out.println("Le contrat a été généré avec succès pour le client: " + client.getClientName() + " " + client.getClientPrenam());
            // Ouvrir le fichier PDF avec l'application par défaut pour les fichiers PDF
            openPDF("contrat_" + client.getClientName() + ".pdf");
        } catch (DocumentException | IOException e) {
            System.out.println("Une erreur s'est produite lors de la génération du PDF : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void openPDF(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                System.out.println("Une erreur s'est produite lors de l'ouverture du fichier PDF : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Le fichier PDF n'existe pas : " + filePath);

        }
        Clients client =new Clients();
        saveContractToDatabase("contrat_" + client.getClientName() + ".pdf", client.getClientId());
    }

    private void saveContractToDatabase(String fileName, String clientId) {
        // Connexion à la base de données
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project", "root", "mdp")) {
            // Requête SQL pour insérer les détails du contrat dans la table des contrats
            String sql = "INSERT INTO contract (contract, Id, created_at) VALUES (?, ?, NOW())";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, fileName);
                pstmt.setString(2, clientId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de l'enregistrement du contrat dans la base de données : " + e.getMessage());
            e.printStackTrace();
        }
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

// Ajouter la colonne "Contract" au TableView
        TableColumn<Clients, String> contractColumn = new TableColumn<>("Contract");
        contractColumn.setCellValueFactory(cellData -> new SimpleStringProperty("Contract")); // Remplacer "Contract" par le nom du lien du contrat
        contractColumn.setCellFactory(column -> new ContractLinkCell<>()); // Utilisation de la cellule personnalisée ContractLinkCell

        // Ajouter la nouvelle colonne à la tableClient
        tableClient.getColumns().add(contractColumn);

        contractColumn.setCellFactory(column -> {
            return new TableCell<Clients, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty && item != null) {
                        Hyperlink hyperlink = new Hyperlink("View Contract");
                        hyperlink.setOnAction(event -> {
                            // Récupérer le client correspondant à la ligne de la cellule
                            Clients client = getTableView().getItems().get(getIndex());
                            // Générer le fichier PDF pour ce client
                            generatePDF(client);
                        });
                        setGraphic(hyperlink);
                    } else {
                        setGraphic(null);
                    }
                }
            };
        });

    }
    private static class ContractLinkCell<T> extends TextFieldTableCell<T, String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty && item != null) {
                Hyperlink hyperlink = new Hyperlink("View Contract"); // Texte du lien, peut être modifié selon vos besoins
                hyperlink.setOnAction(event -> {
                    // Insérer ici la logique pour ouvrir le contrat
                    System.out.println("Opening contract...");
                });
                setGraphic(hyperlink);
            } else {
                setGraphic(null);
            }
        }
    }




        

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



 // Check if the selected status is "Not Accepted"
            if ("Not Accepted".equals(selectedStatus)) {
                // Create a text area for user input
                TextArea textArea = new TextArea();
                textArea.setPrefRowCount(10); // Set the number of visible rows
                textArea.setPrefColumnCount(50); // Set the number of visible columns
                textArea.setWrapText(true); // Enable text wrapping

                // Create an alert dialog with custom content (text area)
                Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
                dialog.setTitle("Chat Box");
                dialog.setHeaderText("La Raison du Refus:");
                dialog.getDialogPane().setContent(textArea);

                // Show the alert dialog
                Optional<ButtonType> result = dialog.showAndWait();

                // Process the user's input if available
                result.ifPresent(buttonType -> {
                    if (buttonType == ButtonType.OK) {
                        // Get user's message from the text area
                        String message = textArea.getText();
                        // Update the message of refusal for the selected client
                        selectedClient.setMessageRefus(message);
                        // Update the client status
                        clientBaseHandler.updateClientStatus(selectedClient);
                        // Refresh table view
                        refreshTable();
                    }
                });

            } else {
                // Update client status
                //selectedClient.setProspectStatus(selectedStatus);
                clientBaseHandler.updateClientStatus(selectedClient);
                // Refresh table view
                refreshTable();
            }
        



            
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
