package main.project.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.project.model.TaxiData;
import main.project.model.Car;
import main.project.model.TaxiStation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Start implements MenuCommand, Initializable {
    public final static String NAME = "startButton";
    private Scanner scan = new Scanner(System.in);
    private TaxiData data = Main.data;

    @FXML
    private TextField fieldNameStation, fieldDistance, fieldNameCar;
    @FXML
    private TextArea textArea;
    @FXML
    private ChoiceBox<Integer> choiceBox;
    @FXML
    private Label label;

    private TaxiStation taxiStation;
    private Integer numOfPassengers;
    private Integer distance;
    private List<Car> deletedCars;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Integer[] integers = new Integer[11];
        for (int i = 1; i <= 10; i++) {
            integers[i] = i;
        }
        choiceBox.getItems().addAll(integers);
    }

    public void onOkButton1() {
        textArea.clear();
        String stationName = fieldNameStation.getText();
        taxiStation = Methods.checkStationName(data, stationName);
        if (taxiStation == null) {
            return;
        }
        numOfPassengers = choiceBox.getValue();
        distance = Integer.parseInt(fieldDistance.getText());
        deletedCars = new ArrayList<>();
        for (int i = 0; i < taxiStation.getCarsList().size(); i++) {
            if (taxiStation.getCarsList().get(i).getNumOfSeats() >= numOfPassengers) {
                textArea.appendText(taxiStation.getCarsList().get(i).toString());
            } else {
                deletedCars.add(taxiStation.getCarsList().remove(i));
                i--;
            }
        }
    }

    public void onOkButton2() {
        String carName = fieldNameCar.getText();
        Car car = Methods.checkCarName(taxiStation, carName);
        taxiStation.getCarsList().addAll(deletedCars);
        if (car == null) {
            return;
        }
        label.setText("Вартість поїздки: " + car.calculateRideCost(numOfPassengers, distance));
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
        return "startScene.fxml";
    }
}
