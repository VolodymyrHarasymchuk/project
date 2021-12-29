package main.project.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class TaxiStation implements Serializable {
    private String name;
    private List<Car> cars;

    public TaxiStation(String name, List<Car> cars) {
        this.name = name;
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCarsList() {
        return cars;
    }

    public int calculateCost() {
        int cost = 0;
        for (int i = 0; i < cars.size(); i++)
            cost += cars.get(i).getCarCost();
        return cost;
    }

    public void sortCars() {
        Collections.sort(cars);
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\n" +
                "Cars:\n" + cars;
    }
}
