package JavaDevProject;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class MainClass extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the main FXML file for the application
            URL url = getClass().getResource("/files/myjavafx/App.fxml");
            if (url == null) {
                System.err.println("Cannot find the main FXML file. Please verify the path.");
                return; // Exit if FXML file not found
            }

            // Load the FXML content using FXMLLoader
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            // Set up the primary stage (application window) with CSS
            Scene scene = new Scene(root);
           /* URL cssUrl = getClass().getResource("/files/myjavafx/style.css");
            System.out.println("CSS URL: " + cssUrl); // This should not print null
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            */
            primaryStage.setScene(scene);
            primaryStage.show();


            // Retrieve the MainController instance linked to the FXML layout
            MainController mainController = loader.getController();

            // Optional: Initialize further components if needed
            mainController.initialize();

        } catch (IOException e) {
            System.err.println("Failed to load FXML: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // Catch-all for other exceptions
        }
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
