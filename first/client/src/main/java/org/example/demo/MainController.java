package org.example.demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button administrateId;

    @FXML
    private Button detailsStatesId;

    @FXML
    private Button mainId;

    @FXML
    private Button monitorTaId;

    @FXML
    private Button profileId;

    @FXML
    void initialize() {
        assert administrateId != null : "fx:id=\"administrateId\" was not injected: check your FXML file 'Untitled'.";
        assert detailsStatesId != null : "fx:id=\"detailsStatesId\" was not injected: check your FXML file 'Untitled'.";
        assert mainId != null : "fx:id=\"mainId\" was not injected: check your FXML file 'Untitled'.";
        assert monitorTaId != null : "fx:id=\"monitorTaId\" was not injected: check your FXML file 'Untitled'.";
        assert profileId != null : "fx:id=\"profileId\" was not injected: check your FXML file 'Untitled'.";

    }

}
