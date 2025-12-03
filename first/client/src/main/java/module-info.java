module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.prefs;

    opens org.example.demo to javafx.graphics , javafx.fxml;
    opens app.controller to javafx.fxml;

    exports org.example.demo;
}