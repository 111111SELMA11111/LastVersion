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





 @FXML

    private void handleAddClient() {
        // Créez une nouvelle fenêtre pour ajouter un client
        Stage addClientStage = new Stage();
        addClientStage.setTitle("Ajouter un Client");

        // Créez les champs de saisie pour le nouveau client
        TextField idField = new TextField();
        TextField nomField = new TextField();
        TextField prenomField = new TextField();
        TextField emailField = new TextField();
        TextField telephoneField = new TextField();
        TextField adresseField = new TextField();
        TextField villeField = new TextField();
        DatePicker dateInscriptionPicker = new DatePicker();
        TextField serviceProposeField = new TextField();
        DatePicker dateServicePicker = new DatePicker();
        TextField typeServiceField = new TextField();
        TextField descriptionField = new TextField();
        TextField statutField = new TextField();

        // Créez un bouton pour ajouter le client
        Button addButton = new Button("Ajouter");
        addButton.setOnAction(e -> {
            try {
                // Récupérez les valeurs des champs
                int id = Integer.parseInt(idField.getText());
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String email = emailField.getText();
                String telephone = telephoneField.getText();
                String adresse = adresseField.getText();
                String ville = villeField.getText();
                String servicePropose = serviceProposeField.getText();
                String typeService = typeServiceField.getText();
                String description = descriptionField.getText();
                String statut = statutField.getText();

                // Vérifiez si les champs obligatoires ne sont pas vides
                if (nom.isBlank() || prenom.isBlank() || email.isBlank() || telephone.isBlank()) {
                    // Affichez un message d'erreur si les champs obligatoires sont vides
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir tous les champs obligatoires.");
                    alert.showAndWait();
                    return;
                }

                // Créez un nouveau client avec les valeurs récupérées
                ClientsEnAtt nouveauClient = new ClientsEnAtt();
                nouveauClient.setProspectId(String.valueOf(id));
                nouveauClient.setProspectName(nom);
                nouveauClient.setProspectPrenam(prenom);
                nouveauClient.setProspectEmail(email);
                //nouveauClient.setProspectTelephone(telephone);

                int telephoneValue = 0; // Initialisez avec une valeur par défaut
                if (!telephone.isEmpty()) {
                    // pour Vérifiez si la chaîne n'est pas vide avant de la convertir en entier
                    telephoneValue = Integer.parseInt(telephone);
                }
                nouveauClient.setProspectTelephone(telephoneValue);

                nouveauClient.setProspectAddress(adresse);
                nouveauClient.setProspectCity(ville);
                nouveauClient.setProspectServiceProposer(servicePropose);
                nouveauClient.setProspectTypeService(typeService);
                nouveauClient.setProspectDescription(description);
                nouveauClient.setProspectStatus(statut);
                nouveauClient.setProspectDateInscription(java.sql.Date.valueOf(dateInscriptionPicker.getValue()));
                nouveauClient.setProspectDateService(java.sql.Date.valueOf(dateServicePicker.getValue()));

                // Ajoutez le nouveau client à votre tableau de prospects
                // Créez une liste observable modifiable pour stocker vos clients
                ObservableList<ClientsEnAtt> clientsList = FXCollections.observableArrayList();

// pour s'aasurer que  TableView est correctement initialisé et lié à la liste
                tableprospect.setItems(clientsList);


                insertClient(nouveauClient, "clients");
                refreshTable();
                // Fermez la fenêtre d'ajout de client
                addClientStage.close();
            } catch (NumberFormatException ex) {
                // Affichez un message d'erreur si une exception NumberFormatException est levée
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez saisir un numéro de téléphone valide.");
                alert.showAndWait();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, new Label("ID du client:"), idField);
        gridPane.addRow(1, new Label("Nom:"), nomField);
        gridPane.addRow(2, new Label("Prénom:"), prenomField);
        gridPane.addRow(3, new Label("Email:"), emailField);
        gridPane.addRow(4, new Label("Téléphone:"), telephoneField);
        gridPane.addRow(5, new Label("Adresse:"), adresseField);
        gridPane.addRow(6, new Label("Ville:"), villeField);
        gridPane.addRow(7, new Label("Date d'inscription:"), dateInscriptionPicker);
        gridPane.addRow(8, new Label("Service proposé:"), serviceProposeField);
        gridPane.addRow(9, new Label("Date de service:"), dateServicePicker);
        gridPane.addRow(10, new Label("Type de service:"), typeServiceField);
        gridPane.addRow(11, new Label("Description:"), descriptionField);
        gridPane.addRow(12, new Label("Statut:"), statutField);

        // Affichez la nouvelle fenêtre
        gridPane.add(addButton, 1, 13);

        Scene scene = new Scene(gridPane, 400, 500);
        addClientStage.setScene(scene);
        addClientStage.show();
    }



    private void insertClient(ClientsEnAtt nouveauClient, String tableName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project", "root", "mdp");

            // Vérifier si la table existe déjà dans la base de données
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, tableName, null);
            boolean tableExists = tables.next();

            // Si la table existe, insérer les données
            if (tableExists) {
                String query = "INSERT INTO " + tableName + " (Id, Name, Prenam, Sex,Email,Telephone, Address, City, DateInscription, ServiceProposer, DateService, TypeService, Description,Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, nouveauClient.getProspectId());
                stmt.setString(2, nouveauClient.getProspectName());
                stmt.setString(3, nouveauClient.getProspectPrenam());
                stmt.setString(4, nouveauClient.getProspectSex());
                stmt.setString(5, nouveauClient.getProspectEmail());
                stmt.setInt(6, nouveauClient.getProspectTelephone());
                stmt.setString(7, nouveauClient.getProspectAddress());
                stmt.setString(8, nouveauClient.getProspectCity());
                stmt.setDate(9, nouveauClient.getProspectDateInscription());
                stmt.setString(10, nouveauClient.getProspectServiceProposer());
                stmt.setDate(11, nouveauClient.getProspectDateService());
                stmt.setString(12, nouveauClient.getProspectTypeService());
                stmt.setString(13, nouveauClient.getProspectDescription());
                stmt.setString(14, nouveauClient.getProspectStatus());
                stmt.executeUpdate();
            } else {
                System.out.println("La table " + tableName + " n'existe pas dans la base de données.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }











    @FXML


    private void supprimerButtonHandler(ActionEvent event) {
        // Récupérer le client sélectionné dans le tableau
        ClientsEnAtt clientSelectionne = tableprospect.getSelectionModel().getSelectedItem();
        if (clientSelectionne != null) {
            // Afficher une boîte de dialogue de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Confirmation de suppression");
            alert.setContentText("Voulez-vous vraiment supprimer ce client ?");

            // Ajouter les boutons de confirmation et d'annulation à la boîte de dialogue
            ButtonType ouiButton = new ButtonType("Oui");
            ButtonType nonButton = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(ouiButton, nonButton);

            // Attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ouiButton) {
                // Supprimer le client de la base de données
                deleteClient(clientSelectionne);
                // Rafraîchir le tableau pour refléter les changements
                refreshTable();
            }
        } else {
            // Afficher un message d'erreur si aucun client n'est sélectionné
            System.out.println("Veuillez sélectionner un client à supprimer.");
        }
    }


    private void deleteClient(ClientsEnAtt client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Établir une connexion avec la base de données
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project", "root", "mdp");
            // Préparer la requête SQL pour supprimer le client de la table
            String query = "DELETE FROM clients WHERE Id = ?";
            stmt = conn.prepareStatement(query);
            // Définir la valeur du paramètre de la requête avec l'ID du client
            stmt.setString(1, client.getProspectId());
            // Exécuter la requête pour supprimer le client de la base de données
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources de connexion et de déclaration
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }













    
}
