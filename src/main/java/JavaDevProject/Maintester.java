package JavaDevProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Maintester extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Corrected the path to reflect the structure from the classpath root
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/myjavafx/Comm.fxml"));
        Scene scene = new Scene(loader.load());

        // Corrected the path to load the CSS file
     //   scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/files/myjavafx/style.css")).toExternalForm());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
