package main.project.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.project.model.TaxiData;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class Save implements MenuCommand, Initializable {
    public final static String NAME = "saveButton";
    private TaxiData data = Main.data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=project";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "sa", "12345");
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            String deleteFromTable1 = "delete from [dbo].[stations]";
            Statement statement2 = connection.createStatement();
            String deleteFromTable2 = "delete from [dbo].[cars]";
            statement1.execute(deleteFromTable1);
            statement2.execute(deleteFromTable2);
            for (int i = 0; i < data.taxiStations.size(); i++) {
                String stationName = data.taxiStations.get(i).getName();
                String insert2 = "Insert Into [dbo].[stations] " +
                        "Values ("+i+", '"+stationName+"')";
                for (int j = 0; j < data.taxiStations.get(i).getCarsList().size(); j++) {
                    String carName = data.taxiStations.get(i).getCarsList().get(j).getName();
                    int carCost = data.taxiStations.get(i).getCarsList().get(j).getCarCost();
                    int callCost = data.taxiStations.get(i).getCarsList().get(j).getCallCost();
                    int distanceCost = data.taxiStations.get(i).getCarsList().get(j).getDistanceCost();
                    int personCost = data.taxiStations.get(i).getCarsList().get(j).getPersonCost();
                    int numOfSeats = data.taxiStations.get(i).getCarsList().get(j).getNumOfSeats();
                    int speed = data.taxiStations.get(i).getCarsList().get(j).getSpeed();
                    int fuelConsumption = data.taxiStations.get(i).getCarsList().get(j).getFuelConsumption();
                    String insert1 = "Insert Into [dbo].[cars] " +
                            "(stationName, carName, carCost, callCost, distanceCost, " +
                            "personCost, numOfSeats, speed, fuelConsumption) " +
                            "Values ('" + stationName + "', '" + carName + "', " +carCost+ ", "+callCost+", " +
                            ""+distanceCost+", "+personCost+", "+numOfSeats+", "+speed+", "+fuelConsumption+")";
                    statement.execute(insert1);
                }
                statement.execute(insert2);
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
        return "saveScene.fxml";
    }
}
