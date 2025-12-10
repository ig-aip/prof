package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SideBarController {



    @FXML
    private ScrollPane scrollPaneId;

    @FXML
    private HBox sideBarHBox;

    @FXML
    private Label navigationLabel;

    @FXML
    private VBox administratePane;

    @FXML
    private VBox detalReportsPane;

    @FXML
    private VBox logsTMSPane;

    @FXML
    private VBox sideBarRoot;

    @FXML
    private Button sideButtonId;

    @FXML
    private Button toggleAdministationButtonId;

    @FXML
    private Button toggleDetalReportsButtonId;

    @FXML
    private Button toggleLogsTMSButtonId;

    private boolean isActive = false;
    private double expandedWidth = 240;
    private double collapsedWidth = 56;

    @FXML
    public void initialize(){
        detalReportsPane.setManaged(false);
        detalReportsPane.setVisible(false);

        administratePane.setManaged(false);
        administratePane.setVisible(false);

        logsTMSPane.setManaged(false);
        logsTMSPane.setVisible(false);
    }

    private void togglePain(VBox pane){
        boolean show = !pane.isVisible();
        pane.setVisible(show);
        pane.setManaged(show);
    }


    @FXML
    void toggleAdministration(ActionEvent event) {
        togglePain(administratePane);
    }

    @FXML
    void toggleDetalReports(ActionEvent event) {
        togglePain(detalReportsPane);
    }

    @FXML
    void toggleLogsTMS(ActionEvent event) {
        togglePain(logsTMSPane);
    }

    @FXML
    void toggleSideBar(ActionEvent event) {
        double afterPane = 0;
        double afterLabel;
        if(isActive){
            navigationLabel.setManaged(true);
            navigationLabel.setVisible(true);
            afterLabel = expandedWidth;
            afterPane = expandedWidth;
        }else{
            navigationLabel.setVisible(false);
            navigationLabel.setManaged(false);
            afterLabel = collapsedWidth;
            afterPane = 0;
        }


        scrollPaneId.setPrefWidth(afterPane);
        scrollPaneId.setMinWidth(afterPane);
        scrollPaneId.setMaxWidth(afterPane);
        sideBarHBox.setMinWidth(afterLabel);
        sideBarHBox.setMaxWidth(afterLabel);




        isActive = !isActive;
        System.out.println(isActive + " " + sideBarRoot.getPrefWidth() + " max: " + sideBarRoot.getMaxHeight());

    }


}
