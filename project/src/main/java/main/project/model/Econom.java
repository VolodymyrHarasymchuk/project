package main.project.model;

public class Econom extends Car {
    public Econom(String name, int carCost, int numOfSeats, int speed, int fuelConsumption) {
        super(name, carCost, (int)(50 + Math.random() * 20), (int)(5 + Math.random() * 3), (int)(10 + Math.random() * 10), numOfSeats, speed, fuelConsumption);
    }
}
