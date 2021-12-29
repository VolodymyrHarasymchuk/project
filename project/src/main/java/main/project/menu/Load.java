package main.project.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.project.model.TaxiData;
import main.project.model.Car;
import main.project.model.TaxiStation;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Load implements MenuCommand, Initializable {
    public final static String NAME = "loadButton";
    private TaxiData data = Main.data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=project";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "sa", "12345");
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            ResultSet resultSetSelect1;
            ResultSet resultSetSelect2;
            String select1 = "select * from [dbo].[stations]";
            String select2 = "select * from [dbo].[cars]";
            resultSetSelect1 = statement.executeQuery(select1);
            resultSetSelect2 = statement2.executeQuery(select2);
            while (resultSetSelect1.next()) {
                TaxiStation newTaxiStation = new TaxiStation(resultSetSelect1.getString(2), new ArrayList<>());
                data.taxiStations.add(newTaxiStation);
                while (resultSetSelect2.next()) {
                    String carName = resultSetSelect2.getString(2);
                    int carCost = resultSetSelect2.getInt(3);
                    int callCost = resultSetSelect2.getInt(4);
                    int distanceCost = resultSetSelect2.getInt(5);
                    int personCost = resultSetSelect2.getInt(6);
                    int numOfSeats = resultSetSelect2.getInt(7);
                    int speed = resultSetSelect2.getInt(8);
                    int fuelConsumption = resultSetSelect2.getInt(9);
                    newTaxiStation.getCarsList().add(new Car(carName, carCost, callCost, distanceCost, personCost, numOfSeats, speed, fuelConsumption));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return "loadScene.fxml";
    }
}
