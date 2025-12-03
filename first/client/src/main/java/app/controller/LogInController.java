package app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.demo.ApiService;

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
    private void onSignIn(){
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
                        String accessToke = result.get("accessToken");
                        String refreshToken = result.toString();
                        setStatus("Server response: " + refreshToken, false);
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
