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

public class Print implements MenuCommand {
    public final static String NAME = "printButton";
    private TaxiData data = Main.data;

    @FXML
    private RadioButton rButton1, rButton2, rButton3, rButton4, rButton5;
    @FXML
    private TextField fieldNameStation, fieldNameCar, fieldFrom, fieldTo;
    @FXML
    private TextArea textArea;

    public void select() {
        if (rButton1.isSelected()) {
            fieldNameStation.visibleProperty().setValue(true);
            fieldNameCar.visibleProperty().setValue(false);
            fieldFrom.visibleProperty().setValue(false);
            fieldTo.visibleProperty().setValue(false);
        } else if (rButton2.isSelected()) {
            fieldNameStation.visibleProperty().setValue(true);
            fieldNameCar.visibleProperty().setValue(true);
            fieldFrom.visibleProperty().setValue(false);
            fieldTo.visibleProperty().setValue(false);
        } else if (rButton3.isSelected()) {
            fieldNameStation.visibleProperty().setValue(false);
            fieldNameCar.visibleProperty().setValue(false);
            fieldFrom.visibleProperty().setValue(false);
            fieldTo.visibleProperty().setValue(false);
        } else if (rButton4.isSelected()) {
            fieldNameStation.visibleProperty().setValue(true);
            fieldNameCar.visibleProperty().setValue(false);
            fieldFrom.visibleProperty().setValue(true);
            fieldTo.visibleProperty().setValue(true);
        } else if (rButton5.isSelected()) {
            fieldNameStation.visibleProperty().setValue(true);
            fieldNameCar.visibleProperty().setValue(false);
            fieldFrom.visibleProperty().setValue(false);
            fieldTo.visibleProperty().setValue(false);
        }
    }

    public void onOkButton(){
        if (rButton1.isSelected()) {
            textArea.clear();
            String stationName = fieldNameStation.getText();
            TaxiStation taxiStation = Methods.checkStationName(data, stationName);
            if (taxiStation == null) {
                return;
            }
            textArea.setText(taxiStation.toString());
        } else if (rButton2.isSelected()) {
            textArea.clear();
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
            textArea.setText(car.toString());
        } else if (rButton3.isSelected()) {
            textArea.clear();
            for (int i = 0; i < data.taxiStations.size(); i++) {
                textArea.appendText(data.taxiStations.get(i).getName() + "\n");
            }
        } else if (rButton4.isSelected()) {
            textArea.clear();
            String stationName = fieldNameStation.getText();
            TaxiStation taxiStation = Methods.checkStationName(data, stationName);
            if (taxiStation == null) {
                return;
            }
            int from = Integer.parseInt(fieldFrom.getText());
            int to = Integer.parseInt(fieldTo.getText());
            boolean isSelected = false;
            for (int i = 0; i < taxiStation.getCarsList().size(); i++) {
                if (taxiStation.getCarsList().get(i).getSpeed() > from && taxiStation.getCarsList().get(i).getSpeed() < to) {
                    textArea.appendText(taxiStation.getCarsList().get(i) + "\n");
                    isSelected = true;
                }
            }
            if (!isSelected)
                textArea.appendText("Немає авто з заданими параметрами в даній станції");
        } else if (rButton5.isSelected()) {
            textArea.clear();
            String stationName = fieldNameStation.getText();
            TaxiStation taxiStation = Methods.checkStationName(data, stationName);
            if (taxiStation == null) {
                return;
            }
            textArea.setText("" + taxiStation.calculateCost());
        }
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void createExitAlert(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public String getSceneStr() {
        return "printScene.fxml";
    }
}
