package main.project.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.project.model.TaxiData;
import main.project.model.TaxiStation;

import java.io.IOException;

public class Sort implements MenuCommand {
    public final static String NAME = "sortButton";
    private TaxiData data = Main.data;

    @FXML
    private TextField fieldNameStation;

    public void onOkButton(ActionEvent event) throws IOException {
        String stationName = fieldNameStation.getText();
        TaxiStation taxiStation = Methods.checkStationName(data, stationName);
        if (taxiStation == null) {
            return;
        }
        taxiStation.sortCars();
        createExitAlert(event);
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void createExitAlert(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sort");
        alert.setHeaderText("Авто посортовано");
        if (alert.showAndWait().get() == ButtonType.OK) {
            root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public String getSceneStr() {
        return "sortScene.fxml";
    }
}
