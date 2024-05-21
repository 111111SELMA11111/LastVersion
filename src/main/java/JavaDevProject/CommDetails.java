package JavaDevProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;

public class CommDetails {

    @FXML
    private ListView<String> listViewAvailableVehicles, listViewNotAvailableVehicles, listViewVehicles;
    @FXML
    private TextField camionTextField, bateauTextField, avionTextField;
    @FXML
    private DatePicker departureDatePicker, arrivalDatePicker;
    @FXML
    private Button affecterButton;
    @FXML
    private TableView<V_Disponibiliy> tablediponibility;

    private final ClientBaseHandler clientBaseHandler = new ClientBaseHandler();
    private List<String> selectedVehicleTypes = new ArrayList<>();

    /**
     * Définit les types de véhicules sélectionnés.
     *
     * @param selectedTypes Les types de véhicules sélectionnés
     */
    public void setSelectedVehicleTypes(List<String> selectedTypes) {
        this.selectedVehicleTypes = new ArrayList<>(selectedTypes);
    }

    @FXML
    public void initialize() {
        listViewAvailableVehicles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadVehicles();
        setupTextFieldListeners();
        affecterButton.setOnAction(event -> handleAffectAction());
        refreshAll();
    }

    /**
     * Charge les véhicules disponibles, non disponibles et tous les véhicules.
     */
    private void loadVehicles() {
        List<Vehicule> allAvailableVehicles = clientBaseHandler.getAvailableVehicles();
        List<Vehicule> allNotAvailableVehicles = clientBaseHandler.getNotAvailableVehicles();
        List<Vehicule> allVehicles = clientBaseHandler.getVehicles();

        List<Vehicule> filteredAvailableVehicles = filterVehiclesByType(allAvailableVehicles);
        List<Vehicule> filteredNotAvailableVehicles = filterVehiclesByType(allNotAvailableVehicles);
        List<Vehicule> filteredAllVehicles = filterVehiclesByType(allVehicles);

        loadVehicleList(filteredAvailableVehicles, listViewAvailableVehicles);
        loadVehicleList(filteredNotAvailableVehicles, listViewNotAvailableVehicles);
        loadVehicleList(filteredAllVehicles, listViewVehicles);
    }

    /**
     * Filtre les véhicules par type.
     *
     * @param vehicles La liste des véhicules à filtrer
     * @return La liste filtrée des véhicules
     */
    private List<Vehicule> filterVehiclesByType(List<Vehicule> vehicles) {
        if (selectedVehicleTypes == null || selectedVehicleTypes.isEmpty()) {
            return vehicles; // Retourne non filtré si aucun type n'est sélectionné
        }

        List<Vehicule> filteredList = new ArrayList<>();
        for (Vehicule vehicle : vehicles) {
            if (selectedVehicleTypes.contains(vehicle.getType())) {
                filteredList.add(vehicle);
            }
        }
        return filteredList;
    }

    /**
     * Charge la liste des véhicules dans un ListView.
     *
     * @param vehicles La liste des véhicules à afficher
     * @param listView Le ListView où les véhicules seront affichés
     */
    public void loadVehicleList(List<Vehicule> vehicles, ListView<String> listView) {
        ObservableList<String> vehicleItems = FXCollections.observableArrayList();
        for (Vehicule vehicle : vehicles) {
            vehicleItems.add(formatVehicleText(vehicle));
        }
        listView.setItems(vehicleItems);
    }

    /**
     * Configure les écouteurs des champs de texte.
     */
    private void setupTextFieldListeners() {
        setupListener(camionTextField, "Camion");
        setupListener(bateauTextField, "Bateau");
        setupListener(avionTextField, "Avion");
    }

    /**
     * Configure un écouteur pour un champ de texte spécifique.
     *
     * @param textField Le champ de texte à configurer
     * @param vehicleType Le type de véhicule associé
     */
    private void setupListener(TextField textField, String vehicleType) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                textField.setText("0");  // Définir la valeur par défaut à 0 si le champ de texte est vide
            }

            if (!textField.isDisabled()) {
                selectVehicles(newValue, vehicleType);
            }
        });
    }

    /**
     * Formate le texte du véhicule pour l'affichage.
     *
     * @param vehicle Le véhicule à formater
     * @return Le texte formaté du véhicule
     */
    private String formatVehicleText(Vehicule vehicle) {
        return vehicle.getVehicule_id() + " - " + vehicle.getType() + " | " + vehicle.getStatus();
    }

    /**
     * Gère l'action d'affectation des véhicules.
     */
    private void handleAffectAction() {
        if (AppState.getSelectedOrderId() == null || AppState.getSelectedOrderId().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Aucun ID de commande sélectionné.");
            return;
        }

        if (departureDatePicker.getValue() == null || arrivalDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez sélectionner les dates de départ et d'arrivée.");
            return;
        }

        affectVehicles(camionTextField, "Camion");
        affectVehicles(bateauTextField, "Bateau");
        affectVehicles(avionTextField, "Avion");
    }

    /**
     * Affiche une alerte.
     *
     * @param alertType Le type d'alerte
     * @param title Le titre de l'alerte
     * @param content Le contenu de l'alerte
     */
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Sélectionne les véhicules en fonction du nombre saisi.
     *
     * @param numberOfVehicles Le nombre de véhicules à sélectionner
     * @param vehicleType Le type de véhicule à sélectionner
     */
    private void selectVehicles(String numberOfVehicles, String vehicleType) {
        int count = 0; // Valeur par défaut

        if (!numberOfVehicles.isEmpty()) {
            try {
                count = Integer.parseInt(numberOfVehicles);
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Entrée invalide", "Veuillez entrer un nombre valide.");
                return;
            }
        }

        int availableVehicleCount = countVehicles(vehicleType, listViewAvailableVehicles);
        if (count > availableVehicleCount) {
            showAlert(Alert.AlertType.WARNING, "Pas assez de " + vehicleType + "s disponibles",
                    "Seulement " + availableVehicleCount + " " + vehicleType + "s sont disponibles.");
            return;
        }

        selectVehiclesFromList(vehicleType, count, listViewAvailableVehicles);
    }

    /**
     * Compte les véhicules d'un certain type dans un ListView.
     *
     * @param vehicleType Le type de véhicule à compter
     * @param listView Le ListView où les véhicules sont affichés
     * @return Le nombre de véhicules du type spécifié
     */
    private int countVehicles(String vehicleType, ListView<String> listView) {
        int count = 0;
        for (String item : listView.getItems()) {
            if (item.contains(vehicleType)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Sélectionne les véhicules dans un ListView.
     *
     * @param vehicleType Le type de véhicule à sélectionner
     * @param count Le nombre de véhicules à sélectionner
     * @param listView Le ListView où les véhicules sont affichés
     */
    private void selectVehiclesFromList(String vehicleType, int count, ListView<String> listView) {
        listView.getSelectionModel().clearSelection();
        int selectedCount = 0;

        for (int i = 0; i < listView.getItems().size() && selectedCount < count; i++) {
            if (listView.getItems().get(i).contains(vehicleType)) {
                listView.getSelectionModel().select(i);
                selectedCount++;
            }
        }
    }

    /**
     * Affecte des véhicules en fonction du type et du nombre spécifiés.
     *
     * @param textField Le champ de texte contenant le nombre de véhicules
     * @param vehicleType Le type de véhicule à affecter
     */
    private void affectVehicles(TextField textField, String vehicleType) {
        String inputText = textField.getText().trim();
        if (inputText.isEmpty()) {
            inputText = "0";
            textField.setText("0");
        }

        int numberToAffect;
        try {
            numberToAffect = Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Entrée invalide", "Veuillez entrer un nombre valide.");
            return;
        }

        ObservableList<String> availableVehicles = listViewAvailableVehicles.getItems();
        int affectedCount = 0;

        String selectedOrderId = AppState.getSelectedOrderId();
        if (selectedOrderId == null || selectedOrderId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Aucun ID de commande sélectionné.");
            return;
        }

        if (departureDatePicker.getValue() == null || arrivalDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez sélectionner les dates de départ et d'arrivée.");
            return;
        }

        for (String vehicle : availableVehicles) {
            if (vehicle.contains(vehicleType) && affectedCount < numberToAffect) {
                String vehicleId = extractVehicleIdFromString(vehicle);
                clientBaseHandler.addToVehicleAvailability(vehicleId, selectedOrderId, departureDatePicker.getValue(), arrivalDatePicker.getValue());
                affectedCount++;
            }
        }

        refreshTable();
    }

    /**
     * Extrait l'ID du véhicule à partir de la chaîne de caractères.
     *
     * @param vehicleString La chaîne contenant les informations du véhicule
     * @return L'ID du véhicule
     */
    private String extractVehicleIdFromString(String vehicleString) {
        return vehicleString.split(" - ")[0]; // Extraire l'ID du véhicule
    }

    /**
     * Rafraîchit le tableau des disponibilités.
     */
    @FXML
    void refreshTable() {
        ObservableList<V_Disponibiliy> vDisponibiliys = FXCollections.observableArrayList(clientBaseHandler.getV_Disponibiliy());
        tablediponibility.setItems(vDisponibiliys);
    }

    /**
     * Rafraîchit toutes les vues et données.
     */
    @FXML
    public void refreshAll() {
        loadVehicles();
        refreshTable();
        disableUnselectedTextFields();
    }

    /**
     * Désactive les champs de texte non sélectionnés.
     */
    private void disableUnselectedTextFields() {
        camionTextField.setDisable(!selectedVehicleTypes.contains("Camion"));
        bateauTextField.setDisable(!selectedVehicleTypes.contains("Bateau"));
        avionTextField.setDisable(!selectedVehicleTypes.contains("Avion"));
    }
}
