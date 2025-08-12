package com.smartcourier.models;

import com.smartcourier.enums.OrderStatus;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1001);

    private int orderId;
    private User customer;
    private Location deliveryLocation;
    private double packageWeight; // Used for Knapsack algorithm
    private OrderStatus status;

    public Order(User customer, Location deliveryLocation, double packageWeight) {
        this.orderId = ID_GENERATOR.getAndIncrement();
        this.customer = customer;
        this.deliveryLocation = deliveryLocation;
        this.packageWeight = packageWeight;
        this.status = OrderStatus.PENDING;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public Location getDeliveryLocation() { return deliveryLocation; }
    public double getPackageWeight() { return packageWeight; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Order{id=%d, location=%s, weight=%.1f, status=%s}",
                orderId, deliveryLocation, packageWeight, status);
    }
}