package JavaDevProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class CommConsController {

    @FXML
    private ListView<String> listViewAvailableConstruction, listViewConstruction;
    @FXML
    private TextField BetonCamionTextField, CompacteurTextField, GrueTextField, PompBetonTextField;
    @FXML
    private DatePicker departureDatePicker, arrivalDatePicker;
    @FXML
    private Button affecterButton;

    // Adding CheckBox components
    @FXML
    private CheckBox BetonCamionCheckBox, CompacteurCheckBox, GrueCheckBox, PompBetonCheckBox;

    @FXML
    private TableView<V_Disponibiliy> tablediponibility;

    private final ClientBaseHandler clientBaseHandler = new ClientBaseHandler();
    private List<String> selectedVehicleTypes = new ArrayList<>();


    public void setSelectedVehicleTypes(List<String> selectedTypes) {
        this.selectedVehicleTypes = new ArrayList<>(selectedTypes);
    }

    @FXML
    public void initialize() {
        listViewAvailableConstruction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadConstructionVehicles();
        setupTextFieldListeners();
        affecterButton.setOnAction(event -> handleAffectAction());
        refreshTable();
    }


    private void loadConstructionVehicles() {
        List<V_Construction> allAvailableVehicles = clientBaseHandler.getAvailableConstr();
        List<V_Construction> allNotAvailableVehicles = clientBaseHandler.getNotAvailableConstr();
        List<V_Construction> allVehicles = clientBaseHandler.getConstr();

        List<V_Construction> filteredAvailableVehicles = filterVehiclesByType(allAvailableVehicles);
        List<V_Construction> filteredNotAvailableVehicles = filterVehiclesByType(allNotAvailableVehicles);
        List<V_Construction> filteredAllVehicles = filterVehiclesByType(allVehicles);

        loadConstructionVehicleList(filteredAvailableVehicles, listViewAvailableConstruction);
        loadConstructionVehicleList(filteredNotAvailableVehicles, listViewConstruction);
        loadConstructionVehicleList(filteredAllVehicles, listViewConstruction);
    }


    private List<V_Construction> filterVehiclesByType(List<V_Construction> vehicles) {
        if (selectedVehicleTypes == null || selectedVehicleTypes.isEmpty()) {
            return vehicles; // Retourne non filtré si aucun type n'est sélectionné
        }

        List<V_Construction> filteredList = new ArrayList<>();
        for (V_Construction vehicle : vehicles) {
            if (selectedVehicleTypes.contains(vehicle.getType())) {
                filteredList.add(vehicle);
            }
        }
        return filteredList;
    }


    public void loadConstructionVehicleList(List<V_Construction> vehicles, ListView<String> listView) {
        ObservableList<String> vehicleItems = FXCollections.observableArrayList();
        for (V_Construction vehicle : vehicles) {
            vehicleItems.add(formatVehicleText(vehicle));
        }
        listView.setItems(vehicleItems);
    }


    private void setupTextFieldListeners() {
        setupListener(BetonCamionTextField, "BetonCamion");
        setupListener(CompacteurTextField, "Compacteur");
        setupListener(GrueTextField, "Grue");
        setupListener(PompBetonTextField, "PompBeton");
    }


    private void setupListener(TextField textField, String vehicleType) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                textField.setText("0");  // Définit la valeur par défaut sur 0 si le champ est vide
            }

            if (!textField.isDisabled()) {
                selectVehicles(newValue, vehicleType);
            }
        });
    }


    private String formatVehicleText(V_Construction vehicle) {
        return vehicle.getVehicule_id() + " - " + vehicle.getType() + " | " + vehicle.getStatus();
    }

    private void handleAffectAction() {
        if (AppState.getSelectedOrderId() == null || AppState.getSelectedOrderId().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Aucun ID de commande sélectionné.");
            return;
        }

        if (departureDatePicker.getValue() == null || arrivalDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez sélectionner les dates de départ et d'arrivée.");
            return;
        }

        affectVehicles(BetonCamionTextField, "BetonCamion");
        affectVehicles(CompacteurTextField, "Compacteur");
        affectVehicles(GrueTextField, "Grue");
        affectVehicles(PompBetonTextField, "PompBeton");
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


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

        int availableVehicleCount = countVehicles(vehicleType, listViewAvailableConstruction);
        if (count > availableVehicleCount) {
            showAlert(Alert.AlertType.WARNING, "Pas assez de " + vehicleType + "s disponibles",
                    "Seulement " + availableVehicleCount + " " + vehicleType + "s sont disponibles.");
            return;
        }

        selectVehiclesFromList(vehicleType, count, listViewAvailableConstruction);
    }


    private int countVehicles(String vehicleType, ListView<String> listView) {
        int count = 0;
        for (String item : listView.getItems()) {
            if (item.contains(vehicleType)) {
                count++;
            }
        }
        return count;
    }


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


    private void affectVehicles(TextField textField, String vehicleType) {
        String inputText = textField.getText().trim();
        if (inputText.isEmpty()) {
            inputText = "0";
            textField.setText("0"); // Définir la valeur par défaut sur 0
        }

        int numberToAffect;
        try {
            numberToAffect = Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Entrée invalide", "Veuillez entrer un nombre valide.");
            return;
        }

        ObservableList<String> availableVehicles = listViewAvailableConstruction.getItems();
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
                clientBaseHandler.addToConstrAvailability(vehicleId, selectedOrderId, departureDatePicker.getValue(), arrivalDatePicker.getValue());
                affectedCount++;
            }
        }

        refreshTable();
    }


    private String extractVehicleIdFromString(String vehicleString) {return vehicleString.split(" - ")[0]; // Extraire l'ID du véhicule
    }

    @FXML
    void refreshTable() {
        ObservableList<V_Disponibiliy> vDisponibiliys = FXCollections.observableArrayList(clientBaseHandler.getC_Disponibiliy());tablediponibility.setItems(vDisponibiliys);}


    @FXML
    void refreshAll() {
        loadConstructionVehicles();
        refreshTable();
        disableUnselectedTextFields();
    }


    private void disableUnselectedTextFields() {
        BetonCamionTextField.setDisable(!selectedVehicleTypes.contains("BetonCamion"));
        CompacteurTextField.setDisable(!selectedVehicleTypes.contains("Compacteur"));
        GrueTextField.setDisable(!selectedVehicleTypes.contains("Grue"));
        PompBetonTextField.setDisable(!selectedVehicleTypes.contains("PompBeton"));
    }
}
