module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;   // ← ДОБАВИТЬ
    requires java.prefs;

    opens org.example.demo to javafx.graphics, javafx.fxml;
    opens app.controller to javafx.fxml;
    opens data to com.fasterxml.jackson.databind;
    opens enams to com.fasterxml.jackson.databind;

    exports org.example.demo;
}
