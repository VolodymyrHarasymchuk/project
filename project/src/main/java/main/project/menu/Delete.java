package main.project.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.project.model.TaxiData;
import main.project.model.Car;
import main.project.model.TaxiStation;

import java.io.IOException;
import java.util.Scanner;

public class Delete implements MenuCommand {
    public final static String NAME = "deleteButton";
    private Scanner scan = new Scanner(System.in);
    private TaxiData data = Main.data;

    @FXML
    private RadioButton rButton1, rButton2;
    @FXML
    private TextField fieldNameStation, fieldNameCar;

    public void select(ActionEvent event) {
        if (rButton1.isSelected()) {
            fieldNameCar.visibleProperty().setValue(false);
        } else if (rButton2.isSelected()) {
            fieldNameCar.visibleProperty().setValue(true);
        }
    }

    public void onOkButton(ActionEvent event) throws IOException {
        if (rButton1.isSelected()) {
            String stationName = fieldNameStation.getText();
            TaxiStation taxiStation = Methods.checkStationName(data, stationName);
            if (taxiStation == null) {
                return;
            }
            data.taxiStations.remove(taxiStation);
        } else if (rButton2.isSelected()) {
            String stationName = fieldNameStation.getText();
            TaxiStation taxiStation = Methods.checkStationName(data, stationName);
            if (taxiStation == null) {
                return;
            }
            String carName = fieldNameCar.getText();
            Car car = Methods.checkCarName(taxiStation, carName);
            if (car == null) {
                return;
            }
            taxiStation.getCarsList().remove(car);
        }
        createExitAlert(event);
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void createExitAlert(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Видалення завершено");
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
        return "deleteScene.fxml";
    }
}
