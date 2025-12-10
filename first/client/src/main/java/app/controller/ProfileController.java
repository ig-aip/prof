package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Workers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButtonId;

    @FXML
    private Label nameId;

    @FXML
    private Label emailId;

    @FXML
    private Label familyId;

    @FXML
    private Label roleId;

    @FXML
    private Label thirdNameId;

    public void setWorker(Workers worker){
        familyId.setText(worker.getSecondName());
        nameId.setText(worker.getFirstName());
        thirdNameId.setText(worker.getThirdName());
        emailId.setText(worker.getEmail());
        roleId.setText(worker.getRole().getString());

    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/main.fxml"));
            Parent root = loader.load();

            MainController mainController = loader.getController();
            mainController.setWorker();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception ex){
            System.out.printf("exeption in back: " + ex.getMessage());
        }
    }


    @FXML
    void initialize() {
        assert nameId != null : "fx:id=\"NameId\" was not injected: check your FXML file 'profile.fxml'.";
        assert emailId != null : "fx:id=\"enailId\" was not injected: check your FXML file 'profile.fxml'.";
        assert familyId != null : "fx:id=\"familyId\" was not injected: check your FXML file 'profile.fxml'.";
        assert roleId != null : "fx:id=\"roleId\" was not injected: check your FXML file 'profile.fxml'.";
        assert thirdNameId != null : "fx:id=\"thirdNameId\" was not injected: check your FXML file 'profile.fxml'.";

    }

}
