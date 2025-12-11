package app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.ApiService;
import org.example.demo.MainController;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class LogInController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;


    private ApiService apiServ = ApiService.getSINGLTON();

    public LogInController() {
    }

    public LogInController(TextField emailField, PasswordField passwordField, Label statusLabel) {
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.statusLabel = statusLabel;
    }

    @FXML
    private void onSignIn(ActionEvent event) throws IOException, InterruptedException {
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        if(email.isEmpty() || password.isEmpty()){
            setStatus("Почта и пароль обязаьельны!", true);
            return;
        }

        setStatus("Signing....", false);
        CompletableFuture.supplyAsync(() -> {
            try {
                return apiServ.signIn(email, password);
            } catch (Exception e) {
                System.out.println("Error in signIn controller: " + e.getMessage());
            }
            return null;
        }).whenComplete((result, er) -> {
            Platform.runLater(() -> {
                if(er != null){
                    setStatus("Error after Chek signIn: " + er.getMessage(), true);
                }else{
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/main.fxml"));
                        Parent root = loader.load();

                        app.controller.MainController mainController = loader.getController();
                        mainController.setWorker();

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();

                        String accessToke = result.getJwtPair().getAccessToken();
                        String refreshToken = result.getJwtPair().getRefreshToken();
                        setStatus("Server response: " + refreshToken + result.getWorker().getFirstName(), false);
                    } catch (Exception e) {
                        System.out.printf("Server response error: " + e.getMessage());
                    }
                }
            });
        });
    }

    @FXML
    private void allClear(){
        emailField.clear();
        passwordField.clear();
        statusLabel.setText("");
    }

    private void setStatus(String newStatus, boolean isError){
        statusLabel.setText(newStatus);
        if(!isError){
            statusLabel.setStyle("-fx-text-fill: #b00020");
        }else{
            statusLabel.setStyle("-fx-text-fill: #006400");
        }
    }

}
