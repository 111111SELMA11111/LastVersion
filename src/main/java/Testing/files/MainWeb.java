package Testing.files;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class MainWeb {

    public static void main(String[] args) {
        String filePath = "src/main/resources/files/myjavafx/index.html";
        openInBrowser(filePath);
    }

    private static void openInBrowser(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Desktop.getDesktop().browse(file.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("n existe pas ");
        }
    }
}
