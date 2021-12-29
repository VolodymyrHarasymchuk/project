package main.project.model;

public class Comfort extends Car {
    public Comfort(String name, int carCost, int numOfSeats, int speed, int fuelConsumption) {
        super(name, carCost, (int)(100 + Math.random() * 20), (int)(10 + Math.random() * 3), (int)(15 + Math.random() * 10), numOfSeats, speed, fuelConsumption);
    }
}
