package app.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

import data.*;
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
import javafx.scene.chart.*;
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
    ListDaysOfSale daysOfSale = new ListDaysOfSale(10);

    @FXML
    private ProgressIndicator networkEffectPercent;

    @FXML
    private BarChart<String, Number> salesDiagram;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

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


    @FXML
    private VBox newsList;

    Timeline timeline;

    private PieChart.Data closed;
    private PieChart.Data working;
    private PieChart.Data remont;

    private void startAutoUpdate(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> fetchAndUpdate()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void pieChartSetDynamicUI(){
        String closed = "#DC143C";
        String working = "#7FFF00";
        String remont = "#00008B";

        String [] colors = {closed, working, remont};
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

    }

    private void pieChartSetter(){
        pieChart.setAnimated(false);

        Platform.runLater(() ->{
            pieChartSetDynamicUI();
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
                pieChartSetDynamicUI();
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




        networkEffectPercentLabel.setText(String.valueOf(String.format("%.2f%%", apiService.getNetworkEffectPercent())));
        networkEffectPercent.setProgress(apiService.getNetworkEffectPercent() / 100);
        networkEffectPercentLabel.backgroundProperty().set(Background.fill(Paint.valueOf("white")));
        root.setLeft(sideBar);
        root.getLeft().maxWidth(10);

        //диаграмма
        daysOfSale.setArray(apiService.getLastSalesForDays(daysOfSale.getDaysAgo()));
        showMoneyChart();

    }


    public void setWorker() throws IOException, InterruptedException, BackingStoreException {

        Workers worker = apiService.getCurrentWorker();
        StringBuilder sBuilder = new StringBuilder();
        menuDropDOwn.setText(worker.getSecondName() + " " + String.valueOf(worker.getFirstName().charAt(0)).toUpperCase() + "." + String.valueOf(worker.getThirdName().charAt(0)).toUpperCase() + "." );
        roleLabel.setText(worker.getRole().getString());
        init();
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
    public void showMoneyChart(){
        salesDiagram.getData().clear();
        xAxis.setLabel("Дни");
        yAxis.setLabel("Сумма продаж");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("День / Сумма");

        for(DayOfSale day : daysOfSale.getDayOfSaleList()){
            String label = String.valueOf(day.getTime().getMonthValue()) + "."+ String.valueOf(day.getTime().getDayOfMonth());
            int sum = day.getAllMoneyForThisDay();

            series.getData().add(new XYChart.Data<>(label, sum));
        }
        System.out.println("DATS MONEY: " + daysOfSale.getDayOfSaleList().size());

        salesDiagram.getData().add(series);
    }

    @FXML
    public void showCountChart(){
        salesDiagram.getData().clear();
        xAxis.setLabel("Дни");
        yAxis.setLabel("Количество продаж");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("День / Количество проданных товаров");

        for(DayOfSale day : daysOfSale.getDayOfSaleList()){
            String label = String.valueOf(day.getTime().getMonthValue()) + "."+ String.valueOf(day.getTime().getDayOfMonth());
            Long count = day.getAllSaledCount();

            series.getData().add(new XYChart.Data<>(label, count));
        }

        System.out.println("DATS COUNT: " + daysOfSale.getDayOfSaleList().size());
        salesDiagram.getData().add(series);
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
