package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

import data.DiagramInfo;
import data.Workers;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.demo.ApiService;

public class MainController {

    ApiService apiService = ApiService.getSINGLTON();

    @FXML
    private ProgressIndicator networkEffectPercent;



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane root;

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

    @FXML private PieChart pieChart;

    @FXML
    private Label roleLabel;

    @FXML
    private Label networkEffectPercentLabel;

    Timeline timeline;

    private PieChart.Data closed;
    private PieChart.Data working;
    private PieChart.Data remont;

    private void startAutoUpdate(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> fetchAndUpdate()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void pieChartSetter(){
        pieChart.setAnimated(false);
        String closed = "#DC143C";
        String working = "#7FFF00";
        String remont = "#00008B";

        String [] colors = {closed, working, remont};


        Platform.runLater(() ->{
            int i = 0;
            for(PieChart.Data data : pieChart.getData()){

                data.getNode().setStyle("-fx-pie-color: " + colors[i] + ";");
                ++i;

                int value = (int) data.getPieValue();
                String text = String.valueOf(value);
                Tooltip tooltip = new Tooltip(text);
                Tooltip.install(data.getNode(), tooltip);

                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    String msg = String.format("%s: %d", data.getName(), value);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
                    alert.show();
                });

            }
        });

    }

    private void fetchAndUpdate(){
        Task<DiagramInfo> task = new Task<>() {
            @Override
            protected DiagramInfo call() throws Exception {
                return apiService.getDiagramRequset();
            }
        };

        task.setOnSucceeded(event ->{
            DiagramInfo info = task.getValue();

            if(pieChart.getData().isEmpty()){
                 closed = new PieChart.Data("closed\n" , info.getClosed());
                 working = new PieChart.Data("working\n" , info.getWorking());
                 remont = new PieChart.Data("remont\n", info.getTech_remont());

                pieChart.getData().setAll(closed, working, remont);
                pieChartSetter();
            }else{
                closed.setPieValue(info.getClosed());
                working.setPieValue(info.getWorking());
                remont.setPieValue(info.getTech_remont());
            }




            //pieChart.setLabelsVisible(true);

            //pieChart.getData().addAll(new PieChart.Data("closed\n" + info.getClosed(), info.getClosed()), new PieChart.Data("working\n" + info.getWorking(), info.getWorking()), new PieChart.Data("remont\n"  +info.getTech_remont(), info.getTech_remont()));
            System.out.println("yappi");
        });

        new Thread(task).start();
    }

    private void init() throws IOException, InterruptedException {
        startAutoUpdate();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/sideBar.fxml"));
        Parent sideBar = loader.load();

        SideBarController sideBarController = loader.getController();
        sideBarController.initialize();

        DiagramInfo info = apiService.getDiagramRequset();



        networkEffectPercentLabel.setText(String.valueOf(String.format("%.2f%%", apiService.getNetworkEffectPercent())));
        networkEffectPercent.setProgress(apiService.getNetworkEffectPercent() / 100);
        networkEffectPercent.setAccessibleText("10");
        networkEffectPercentLabel.backgroundProperty().set(Background.fill(Paint.valueOf("white")));
        System.out.println("PERCENT: " + apiService.getNetworkEffectPercent());
        System.out.println("PERCENT: " + networkEffectPercent.getProgress());
        root.setLeft(sideBar);
        root.getLeft().maxWidth(10);
    }


    public void setWorker() throws IOException, InterruptedException {
        init();
        Workers worker = apiService.getCurrentWorker();
        StringBuilder sBuilder = new StringBuilder();
        menuDropDOwn.setText(worker.getSecondName() + " " + String.valueOf(worker.getFirstName().charAt(0)).toUpperCase() + "." + String.valueOf(worker.getThirdName().charAt(0)).toUpperCase() + "." );
        roleLabel.setText(worker.getRole().getString());
    }

    @FXML
    public void shutdown(ActionEvent event) throws IOException, BackingStoreException {
        try {
            if (timeline != null) timeline.stop();
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
    void openProfile(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/profile.fxml"));
            //loader.setRoot(loader);
            Parent profile = loader.load();



            ProfileController profileController = loader.getController();
            profileController.setWorker(apiService.getCurrentWorker());

            root.setCenter(profile);
            System.out.println("my profile ");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }



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
