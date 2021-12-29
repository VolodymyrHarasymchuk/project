package main.project.menu;

import main.project.model.TaxiData;
import main.project.model.Car;
import main.project.model.TaxiStation;

public class Methods {
    public static TaxiStation checkStationName(TaxiData data, String input) {
        TaxiStation taxiStation = null;
        for (int i = 0; i < data.taxiStations.size(); i++) {
            if (input.equals(data.taxiStations.get(i).getName())) {
                taxiStation = data.taxiStations.get(i);
            }
        }
        return taxiStation;
    }

    public static Car checkCarName(TaxiStation station, String input) {
        Car car = null;
        for (int i = 0; i < station.getCarsList().size(); i++) {
            if (input.equals(station.getCarsList().get(i).getName())) {
                car = station.getCarsList().get(i);
            }
        }
        return car;
    }
}
