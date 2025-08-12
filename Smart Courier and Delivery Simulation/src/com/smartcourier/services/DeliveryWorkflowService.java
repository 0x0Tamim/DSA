package com.smartcourier.services;

import com.smartcourier.algorithms.DijkstraRoutePlanner;
import com.smartcourier.algorithms.KMeans;
import com.smartcourier.algorithms.Knapsack;
import com.smartcourier.enums.OrderStatus;
import com.smartcourier.models.*;
import com.smartcourier.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveryWorkflowService {
    private final OrderRepository orderRepository;
    private final KMeans kMeans;
    private final Knapsack knapsack;
    private final DijkstraRoutePlanner routePlanner;
    private final Location depotLocation;

    public DeliveryWorkflowService(OrderRepository orderRepository, Location depotLocation) {
        this.orderRepository = orderRepository;
        this.depotLocation = depotLocation;
        this.kMeans = new KMeans();
        this.knapsack = new Knapsack();
        this.routePlanner = new DijkstraRoutePlanner();
    }

    public List<DeliveryTask> processDeliveries(List<Vehicle> availableVehicles) {
        System.out.println("\n--- Starting Daily Delivery Workflow ---");
        List<DeliveryTask> allTasks = new ArrayList<>();

        // 1. Fetch pending orders
        List<Order> pendingOrders = orderRepository.findByStatus(OrderStatus.PENDING);
        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders to process.");
            return allTasks;
        }
        System.out.println("Found " + pendingOrders.size() + " pending orders.");

        // 2. Cluster orders based on location
        int k = availableVehicles.size();
        Map<Location, List<Order>> clusters = kMeans.cluster(pendingOrders, k);
        System.out.println("Clustered orders into " + clusters.size() + " groups.");
        clusters.values().forEach(cluster -> {
            for (Order order : cluster) {
                order.setStatus(OrderStatus.CLUSTERED);
            }
        });

        // 3. Assign packages from each cluster to a vehicle
        int vehicleIndex = 0;
        for (List<Order> clusterOrders : clusters.values()) {
            if (vehicleIndex >= availableVehicles.size()) break; // No more vehicles

            Vehicle vehicle = availableVehicles.get(vehicleIndex++);
            System.out.printf("\nProcessing cluster for Vehicle %d (Driver: %s, Capacity: %.1fkg)...\n",
                    vehicle.getVehicleId(), vehicle.getDriver().getUsername(), vehicle.getMaxCapacity());

            // 4. Use Knapsack to select packages that fit
            List<Order> assignedOrders = knapsack.assignPackages(vehicle, clusterOrders);
            System.out.println("Assigned " + assignedOrders.size() + " orders to the vehicle.");
            assignedOrders.forEach(order -> {
                order.setStatus(OrderStatus.ASSIGNED);
                System.out.println("  - " + order);
            });

            // 5. Plan the route for the assigned packages
            if (!assignedOrders.isEmpty()) {
                List<Location> deliveryLocations = assignedOrders.stream()
                        .map(Order::getDeliveryLocation)
                        .collect(Collectors.toList());

                List<Location> optimizedRoute = routePlanner.findShortestTour(depotLocation, deliveryLocations);
                DeliveryTask task = new DeliveryTask(vehicle, assignedOrders, optimizedRoute);
                allTasks.add(task);
            }
        }
        System.out.println("\n--- Delivery Workflow Complete. " + allTasks.size() + " delivery tasks created. ---");
        return allTasks;
    }
}