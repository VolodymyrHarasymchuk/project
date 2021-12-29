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
import main.project.model.Comfort;
import main.project.model.Econom;
import main.project.model.Premium;
import main.project.model.TaxiStation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Create implements MenuCommand {
    public final static String NAME = "createButton";
    private Scanner scan = new Scanner(System.in);
    private TaxiData data = Main.data;

    @FXML
    private RadioButton rButton1, rButton2, rButton3, rButton4, rButton5;
    @FXML
    private TextField fieldNameStation, fieldNameCar, fieldCost, fieldNumOfSeats, fieldSpeed, fieldFuelConsumption;
    @FXML
    private Label carClassLabel;

    public void select(ActionEvent event) {
        if (rButton1.isSelected()) {
            fieldNameCar.visibleProperty().setValue(false);
            fieldCost.visibleProperty().setValue(false);
            fieldNumOfSeats.visibleProperty().setValue(false);
            fieldSpeed.visibleProperty().setValue(false);
            fieldFuelConsumption.visibleProperty().setValue(false);
            rButton3.visibleProperty().setValue(false);
            rButton4.visibleProperty().setValue(false);
            rButton5.visibleProperty().setValue(false);
            carClassLabel.visibleProperty().setValue(false);
        } else if (rButton2.isSelected()) {
            fieldNameCar.visibleProperty().setValue(true);
            fieldCost.visibleProperty().setValue(true);
            fieldNumOfSeats.visibleProperty().setValue(true);
            fieldSpeed.visibleProperty().setValue(true);
            fieldFuelConsumption.visibleProperty().setValue(true);
            rButton3.visibleProperty().setValue(true);
            rButton4.visibleProperty().setValue(true);
            rButton5.visibleProperty().setValue(true);
            carClassLabel.visibleProperty().setValue(true);
        }
    }

    public void onOkButton(ActionEvent event) throws IOException {
        if (rButton1.isSelected()) {
            String stationName = fieldNameStation.getText();
            data.taxiStations.add(new TaxiStation(stationName, new ArrayList<>()));
        } else if (rButton2.isSelected()) {
            String stationName = fieldNameStation.getText();
            TaxiStation taxiStation = Methods.checkStationName(data, stationName);
            if (taxiStation == null) {
                return;
            }
            String carName = fieldNameCar.getText();
            int carCost = Integer.parseInt(fieldCost.getText());
            int carNumOfSeats = Integer.parseInt(fieldNumOfSeats.getText());
            int carSpeed = Integer.parseInt(fieldSpeed.getText());
            int carFuelConsumption = Integer.parseInt(fieldFuelConsumption.getText());
            if (rButton3.isSelected()) {
                taxiStation.getCarsList().add(new Econom(carName, carCost, carNumOfSeats, carSpeed, carFuelConsumption));
            } else if (rButton4.isSelected()) {
                taxiStation.getCarsList().add(new Comfort(carName, carCost, carNumOfSeats, carSpeed, carFuelConsumption));
            } else if (rButton5.isSelected()) {
                taxiStation.getCarsList().add(new Premium(carName, carCost, carNumOfSeats, carSpeed, carFuelConsumption));
            }
        }
        createExitAlert(event);
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void createExitAlert(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create");
        alert.setHeaderText("Створення завершено");
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
        return "createScene.fxml";
    }
}
