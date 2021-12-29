package main.project.model;

import java.io.Serializable;

public class Car implements Comparable<Car>, Serializable {
    private String name;
    private int carCost;
    private int callCost;
    private int distanceCost;
    private int personCost;
    private int numOfSeats;
    private int speed;
    private int fuelConsumption;

    public Car(String name, int carCost, int callCost, int distanceCost, int personCost, int numOfSeats, int speed, int fuelConsumption) {
        this.name = name;
        this.carCost = carCost;
        this.callCost = callCost;
        this.distanceCost = distanceCost;
        this.personCost = personCost;
        this.numOfSeats = numOfSeats;
        this.speed = speed;
        this.fuelConsumption = fuelConsumption;
    }

    public String getName() {
        return name;
    }
    public int getCarCost() {
        return carCost;
    }
    public int getSpeed() {
        return speed;
    }
    public int getNumOfSeats() {
        return numOfSeats;
    }
    public int getCallCost() {
        return callCost;
    }
    public int getDistanceCost() {
        return distanceCost;
    }
    public int getPersonCost() {
        return personCost;
    }
    public int getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public int compareTo(Car o) {
        return this.fuelConsumption - o.fuelConsumption;
    }

    public int calculateRideCost(int numOfPassengers, int distance) {
        return callCost + distanceCost * distance + personCost * numOfPassengers;
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\n" +
                "Car cost: " + carCost + "\n" +
                "Call cost: " + callCost + "\n" +
                "Distance cost: " + distanceCost + "\n" +
                "Person cost: " + personCost + "\n" +
                "Number of seats: " + numOfSeats + "\n" +
                "Speed: " + speed + "\n" +
                "Fuel consumption: " + fuelConsumption + "\n";
    }
}
