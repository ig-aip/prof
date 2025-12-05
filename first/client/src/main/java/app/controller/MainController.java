package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

import data.Workers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
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


    @FXML
    private Label roleLabel;



    public void setWorker(){
        Workers worker = apiService.getCurrentWorker();
        StringBuilder sBuilder = new StringBuilder();
        menuDropDOwn.setText(worker.getSecondName() + " " + String.valueOf(worker.getFirstName().charAt(0)).toUpperCase() + "." + String.valueOf(worker.getThirdName().charAt(0)).toUpperCase() + "." );
        roleLabel.setText(worker.getRole().getString());
    }

    @FXML
    public void shutdown(ActionEvent event) throws IOException, BackingStoreException {
        try {
            apiService.clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/logIn.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) (menuDropDOwn.getItems().get(2).getParentPopup().getOwnerNode().getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }



    }

    @FXML
    void openProfile(ActionEvent event) {
        System.out.println("my profile ");
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
