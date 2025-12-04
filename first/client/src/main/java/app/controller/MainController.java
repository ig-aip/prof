package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.Workers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import org.example.demo.ApiService;

public class MainController {

    ApiService apiService = ApiService.getSINGLTON();

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
    private MenuButton menuDropDOwn;

    @FXML
    private Button monitorTaId;


    public void setWorker(){
        Workers worker = apiService.getCurrentWorker();
        StringBuilder sBuilder = new StringBuilder();
        menuDropDOwn.setText(worker.getSecondName() + " " + String.valueOf(worker.getFirstName().charAt(0)).toUpperCase() + "." + String.valueOf(worker.getThirdName().charAt(0)).toUpperCase() + "." );
    }

    @FXML
    void openProfile(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert administrateId != null : "fx:id=\"administrateId\" was not injected: check your FXML file 'main.fxml'.";
        assert detailsStatesId != null : "fx:id=\"detailsStatesId\" was not injected: check your FXML file 'main.fxml'.";
        assert mainId != null : "fx:id=\"mainId\" was not injected: check your FXML file 'main.fxml'.";
        assert menuDropDOwn != null : "fx:id=\"menuDropDOwn\" was not injected: check your FXML file 'main.fxml'.";
        assert monitorTaId != null : "fx:id=\"monitorTaId\" was not injected: check your FXML file 'main.fxml'.";

    }

}
