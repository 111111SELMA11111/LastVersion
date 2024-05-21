package JavaDevProject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Objects;

public class V_ConstructionController {

    @FXML
    private Button BetonCamion1;

    @FXML
    private Button BetonCamion2;

    @FXML
    private Button BetonCamion3;

    @FXML
    private Button BetonCamion4;

    @FXML
    private Button BetonCamion5;

    @FXML
    private Button Compacteurs1;

    @FXML
    private Button Compacteurs2;

    @FXML
    private Button Compacteurs3;

    @FXML
    private Button Compacteurs4;

    @FXML
    private Button Compacteurs5;

    @FXML
    private Button Grues1;

    @FXML
    private Button Grues2;

    @FXML
    private Button Grues3;

    @FXML
    private Button Grues4;

    @FXML
    private Button Grues5;

    @FXML
    private Button Pellesmecanique1;

    @FXML
    private Button Pellesmecanique2;

    @FXML
    private Button Pellesmecanique3;

    @FXML
    private Button Pellesmecanique4;

    @FXML
    private Button Pellesmecanique5;

    @FXML
    private Button Pompbeton1;

    @FXML
    private Button Pompbeton2;

    @FXML
    private Button Pompbeton3;

    @FXML
    private Button Pompbeton4;

    @FXML
    private Button Pompbeton5;


    public void initialize(){
        ClientBaseHandler dbHandler = new ClientBaseHandler();
        List<V_Construction> V_cons = dbHandler.getV_ConstructionStatus();

        if (V_cons == null || V_cons.isEmpty()) {
            System.out.println("No vehicle data available.");
            return;
        }

        setButtonsToDefaultImage(List.of(BetonCamion1, BetonCamion2, BetonCamion3, BetonCamion4, BetonCamion5), "/pics/BetonCamion_available.jpg");
        setButtonsToDefaultImage(List.of(Compacteurs1, Compacteurs2, Compacteurs3, Compacteurs4, Compacteurs5), "/pics/Compacteur_available.jpg");
        setButtonsToDefaultImage(List.of(Grues1, Grues2, Grues3, Grues4, Grues5), "/pics/Grue_available.jpg");
        setButtonsToDefaultImage(List.of(Pellesmecanique1, Pellesmecanique2, Pellesmecanique3, Pellesmecanique4, Pellesmecanique5), "/pics/Pellemecanique_available.jpg");
        setButtonsToDefaultImage(List.of(Pompbeton1, Pompbeton2, Pompbeton3, Pompbeton4, Pompbeton5), "/pics/Pompeabeton_available.jpg");
        V_cons.forEach(this::UpdateImageButtonBasedOnVehicule);
    }

    private void setButtonsToDefaultImage(List<Button> buttons, String imagePath) {
        buttons.forEach(button -> loadImageAndSetButton(button, imagePath));
    }





    private void UpdateImageButtonBasedOnVehicule(V_Construction vConstruction) {
        Button button = null;
        String type = vConstruction.getType();
        if (type != null) {
            switch (type) {
                case "BetonCamion":
                    button = switch (vConstruction.getVehicule_id()) {
                        case "BetonCamion1" -> BetonCamion1;
                        case "BetonCamion2" -> BetonCamion2;
                        case "BetonCamion3" -> BetonCamion3;
                        case "BetonCamion4" -> BetonCamion4;
                        case "BetonCamion5" -> BetonCamion5;
                        default -> {
                            System.out.println("Unrecognized vehicle ID: " + vConstruction.getVehicule_id());
                            yield null;
                        }
                    };
                    break;

                case "Compacteur":
                    button = switch (vConstruction.getVehicule_id()) {
                        case "Compacteurs1" -> Compacteurs1;
                        case "Compacteurs2" -> Compacteurs2;
                        case "Compacteurs3" -> Compacteurs3;
                        case "Compacteurs4" -> Compacteurs4;
                        case "Compacteurs5" -> Compacteurs5;
                        default -> {
                            System.out.println("Unrecognized vehicle ID: " + vConstruction.getVehicule_id());
                            yield null;
                        }
                    };
                    break;

                case "Grue":
                    button = switch (vConstruction.getVehicule_id()) {
                        case "Grues1" -> Grues1;
                        case "Grues2" -> Grues2;
                        case "Grues3" -> Grues3;
                        case "Grues4" -> Grues4;
                        case "Grues5" -> Grues5;
                        default -> {
                            System.out.println("Unrecognized vehicle ID: " + vConstruction.getVehicule_id());
                            yield null;
                        }
                    };
                    break;

                case "Pellemecanique":
                    button = switch (vConstruction.getVehicule_id()) {
                        case "Pellesmecanique1" -> Pellesmecanique1;
                        case "Pellesmecanique2" -> Pellesmecanique2;
                        case "Pellesmecanique3" -> Pellesmecanique3;
                        case "Pellesmecanique4" -> Pellesmecanique4;
                        case "Pellesmecanique5" -> Pellesmecanique5;
                        default -> {
                            System.out.println("Unrecognized vehicle ID: " + vConstruction.getVehicule_id());
                            yield null;
                        }
                    };
                    break;

                case "Pompeabeton":
                    button = switch (vConstruction.getVehicule_id()) {
                        case "Pompbeton1" -> Pompbeton1;
                        case "Pompbeton2" -> Pompbeton2;
                        case "Pompbeton3" -> Pompbeton3;
                        case "Pompbeton4" -> Pompbeton4;
                        case "Pompbeton5" -> Pompbeton5;
                        default -> {
                            System.out.println("Unrecognized vehicle ID: " + vConstruction.getVehicule_id());
                            yield null;
                        }
                    };
                    break;

                default:
                    System.out.println("Unrecognized vehicle type: " + type);
            }

        } else {
            System.out.println("Type is null for vehicle ID: " + vConstruction.getVehicule_id());
        }
        if (button != null) {
            String imagePath = getImageBasedOnStatus(vConstruction.getStatus(), vConstruction.getType());
            System.out.println("Loading image for " + vConstruction.getType() + " with status " + vConstruction.getStatus() + ": " + imagePath);
            loadImageAndSetButton(button, imagePath);
        }
    }


    private String getImageBasedOnStatus(String status , String type){
        String imageType = status.equalsIgnoreCase("available") ? "available" : "not-available";
        return switch (type) {
            case "BetonCamion", "Compacteur", "Grue","Pellemecanique","Pompeabeton" -> "/pics/" + type.toLowerCase() + "_" + imageType + ".jpg";
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
