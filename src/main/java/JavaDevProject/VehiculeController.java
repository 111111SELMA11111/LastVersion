package JavaDevProject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Objects;

public class VehiculeController {
    @FXML
    private Button AvionButton1, AvionButton2, AvionButton3, BateauButton1, BateauButton2, BateauButton3, BateauButton4, BateauButton5, CamionButton1, CamionButton2, CamionButton3, CamionButton4, CamionButton5, CamionButton6, CamionButton7, CamionButton8, CamionButton9, CamionButton10, CamionButton11, CamionButton12, CamionButton13, CamionButton14, CamionButton15, CamionButton16, CamionButton17, CamionButton18, CamionButton19, CamionButton20;

    public void initialize() {
        ClientBaseHandler dbHandler = new ClientBaseHandler();
        List<Vehicule> vehicles = dbHandler.getVehicleStatus();

        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicle data available.");
            return;
        }

        setButtonsToDefaultImage(List.of(AvionButton1, AvionButton2, AvionButton3), "/pics/avion_available.jpg");
        setButtonsToDefaultImage(List.of(BateauButton1, BateauButton2, BateauButton3, BateauButton4, BateauButton5), "/pics/bateau_available.jpg");
        setButtonsToDefaultImage(List.of(CamionButton1, CamionButton2, CamionButton3, CamionButton4, CamionButton5, CamionButton6, CamionButton7, CamionButton8, CamionButton9, CamionButton10, CamionButton11, CamionButton12, CamionButton13, CamionButton14, CamionButton15, CamionButton16, CamionButton17, CamionButton18, CamionButton19, CamionButton20), "/pics/camion_available.jpg");

        vehicles.forEach(this::updateButtonImageBasedOnVehicle);
    }

    private void setButtonsToDefaultImage(List<Button> buttons, String imagePath) {
        buttons.forEach(button -> loadImageAndSetButton(button, imagePath));
    }

    private void updateButtonImageBasedOnVehicle(Vehicule vehicle) {
        Button button = null;
        String type = vehicle.getType();
        if (type != null) {
            switch (type) {
                case "Avion":
                    button = switch (vehicle.getVehicule_id()) {
                        case "Avion1" -> AvionButton1;
                        case "Avion2" -> AvionButton2;
                        case "Avion3" -> AvionButton3;
                        default -> {
                            System.out.println("Unrecognized vehicle ID: " + vehicle.getVehicule_id());
                            yield null;
                        }
                    };
                    break;
                case "Camion":
                    button = switch (vehicle.getVehicule_id()) {
                        case "Camion1" -> CamionButton1;
                        case "Camion2" -> CamionButton2;
                        case "Camion3" -> CamionButton3;
                        case "Camion4" -> CamionButton4;
                        case "Camion5" -> CamionButton5;
                        case "Camion6" -> CamionButton6;
                        case "Camion7" -> CamionButton7;
                        case "Camion8" -> CamionButton8;
                        case "Camion9" -> CamionButton9;
                        case "Camion10" -> CamionButton10;
                        case "Camion11" -> CamionButton11;
                        case "Camion12" -> CamionButton12;
                        case "Camion13" -> CamionButton13;
                        case "Camion14" -> CamionButton14;
                        case "Camion15" -> CamionButton15;
                        case "Camion16" -> CamionButton16;
                        case "Camion17" -> CamionButton17;
                        case "Camion18" -> CamionButton18;
                        case "Camion19" -> CamionButton19;
                        case "Camion20" -> CamionButton20;
                        default -> {
                            System.out.println("Unrecognized vehicle ID: " + vehicle.getVehicule_id());
                            yield null;
                        }
                    };
                    break;
                case "Bateau":
                    button = switch (vehicle.getVehicule_id()) {
                        case "Bateau1" -> BateauButton1;
                        case "Bateau2" -> BateauButton2;
                        case "Bateau3" -> BateauButton3;
                        case "Bateau4" -> BateauButton4;
                        case "Bateau5" -> BateauButton5;
                        default -> {
                            System.out.println("Unrecognized vehicle ID: " + vehicle.getVehicule_id());
                            yield null;
                        }
                    };
                    break;
                default:
                    System.out.println("Unrecognized vehicle type: " + type);
            }
        } else {
            System.out.println("Type is null for vehicle ID: " + vehicle.getVehicule_id());
        }

        if (button != null) {
            String imagePath = getImagePathBasedOnStatus(vehicle.getStatus(), vehicle.getType());
            System.out.println("Loading image for " + vehicle.getType() + " with status " + vehicle.getStatus() + ": " + imagePath);
            loadImageAndSetButton(button, imagePath);
        }
    }

    private String getImagePathBasedOnStatus(String status, String type) {
        String imageType = status.equalsIgnoreCase("available") ? "available" : "not-available";
        return switch (type) {
            case "Avion", "Bateau", "Camion" -> "/pics/" + type.toLowerCase() + "_" + imageType + ".jpg";
            default -> "/pics/default.jpg";
        };
    }

    private void loadImageAndSetButton(Button button, String imagePath) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            if (!image.isError()) {
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(button.getPrefWidth());
                imageView.setFitHeight(button.getPrefHeight());
                imageView.setPreserveRatio(false);
                button.setGraphic(imageView);
            } else {
                System.out.println("Image loading failed: " + imagePath);
            }
        } catch (Exception e) {
            System.out.println("Failed to load image: " + imagePath + ", Error: " + e.getMessage());
        }
    }
}
