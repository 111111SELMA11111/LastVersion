module org.example.myjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires java.mail;
    requires jdk.jsobject;


    exports JavaDevProject to javafx.fxml, javafx.graphics;
    opens JavaDevProject to javafx.base, javafx.fxml, javafx.graphics;
    exports Testing.files to javafx.fxml, javafx.graphics;
    opens Testing.files to javafx.base, javafx.fxml, javafx.graphics;


}

