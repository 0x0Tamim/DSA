package com.smartcourier.models;

public class Vehicle {
    private int vehicleId;
    private User driver;
    private double maxCapacity; // e.g., in kilograms

    public Vehicle(int vehicleId, User driver, double maxCapacity) {
        this.vehicleId = vehicleId;
        this.driver = driver;
        this.maxCapacity = maxCapacity;
    }

    // Getters
    public int getVehicleId() { return vehicleId; }
    public User getDriver() { return driver; }
    public double getMaxCapacity() { return maxCapacity; }
}