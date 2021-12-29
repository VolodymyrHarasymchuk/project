package main.project.model;

public class Premium extends Car {
    public Premium(String name, int carCost, int numOfSeats, int speed, int fuelConsumption) {
        super(name, carCost, (int)(150 + Math.random() * 20), (int)(20 + Math.random() * 3), (int)(30 + Math.random() * 10), numOfSeats, speed, fuelConsumption);
    }
}
