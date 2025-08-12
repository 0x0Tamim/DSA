package com.smartcourier.models;

import java.util.List;

public class DeliveryTask {
    private Vehicle vehicle;
    private List<Order> assignedOrders;
    private List<Location> optimizedRoute; // The sequence of locations to visit

    public DeliveryTask(Vehicle vehicle, List<Order> assignedOrders, List<Location> optimizedRoute) {
        this.vehicle = vehicle;
        this.assignedOrders = assignedOrders;
        this.optimizedRoute = optimizedRoute;
    }

    public void startDelivery() {
        System.out.printf("\n--- Starting Delivery for Driver: %s (Vehicle %d) ---\n",
                vehicle.getDriver().getUsername(), vehicle.getVehicleId());
        System.out.println("Optimized Route: " + optimizedRoute);
        for (Order order : assignedOrders) {
            order.setStatus(com.smartcourier.enums.OrderStatus.OUT_FOR_DELIVERY);
            System.out.printf("  > Delivering Order %d to %s\n", order.getOrderId(), order.getDeliveryLocation());
            // Simulate delivery time
            order.setStatus(com.smartcourier.enums.OrderStatus.DELIVERED);
            System.out.printf("  > Order %d DELIVERED\n", order.getOrderId());
        }
        System.out.println("--- All deliveries for this task completed ---");
    }

    public List<Order> getAssignedOrders() { return assignedOrders; }
}