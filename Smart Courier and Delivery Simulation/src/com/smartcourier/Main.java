// File: com/smartcourier/Main.java
package com.smartcourier;

import com.smartcourier.algorithms.LevenshteinDistance;
import com.smartcourier.enums.OrderStatus;
import com.smartcourier.enums.UserRole;
import com.smartcourier.models.*;
import com.smartcourier.repositories.OrderRepository;
import com.smartcourier.repositories.UserRepository;
import com.smartcourier.services.AuthenticationService;
import com.smartcourier.services.DeliveryWorkflowService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Smart Courier & Delivery Optimization System ---");

        // Step 1: User Management Setup
        System.out.println("\n--- 1. User Management ---");
        UserRepository userRepository = new UserRepository();
        AuthenticationService authService = new AuthenticationService(userRepository);

        // Register Users
        User admin = authService.register("admin", "admin123", UserRole.ADMIN);
        User customer1 = authService.register("alice", "pass1", UserRole.CUSTOMER);
        User customer2 = authService.register("bob", "pass2", UserRole.CUSTOMER);
        User driver1 = authService.register("driver_tom", "drive1", UserRole.DELIVERY_AGENT);
        User driver2 = authService.register("driver_jane", "drive2", UserRole.DELIVERY_AGENT);

        // Login Example
        authService.login("alice", "pass1");
        authService.login("admin", "wrongpass");


        // Step 2: Order Creation
        System.out.println("\n--- 2. Order Management ---");
        OrderRepository orderRepository = new OrderRepository();
        // Create sample orders with locations and weights
        orderRepository.save(new Order(customer1, new Location(10, 20), 5.5));
        orderRepository.save(new Order(customer2, new Location(12, 25), 8.0));
        orderRepository.save(new Order(customer1, new Location(15, 18), 12.0));
        orderRepository.save(new Order(customer2, new Location(80, 85), 7.2));
        orderRepository.save(new Order(customer1, new Location(75, 90), 15.0));
        orderRepository.save(new Order(customer2, new Location(85, 88), 4.0));
        orderRepository.save(new Order(customer1, new Location(50, 50), 20.0)); // Central order
        System.out.println("Initial orders created:");
        orderRepository.findAll().forEach(System.out::println);


        // Step 3, 4, 5: Run the full delivery workflow
        Location depot = new Location(0, 0); // Our central warehouse
        DeliveryWorkflowService workflowService = new DeliveryWorkflowService(orderRepository, depot);

        // Create vehicles for our drivers
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(1, driver1, 25.0)); // 25kg capacity
        vehicles.add(new Vehicle(2, driver2, 30.0)); // 30kg capacity

        // Process all pending orders
        List<DeliveryTask> tasks = workflowService.processDeliveries(vehicles);

        // Step 6 & 7: Simulate delivery execution and real-time status updates
        System.out.println("\n--- 6 & 7. Executing Deliveries & Status Updates ---");
        for (DeliveryTask task : tasks) {
            task.startDelivery();
        }

        // Step 8: Admin Analytics Dashboard
        System.out.println("\n--- 8. Admin Analytics Dashboard ---");
        long deliveredCount = orderRepository.findByStatus(OrderStatus.DELIVERED).size();
        long pendingCount = orderRepository.findByStatus(OrderStatus.PENDING).size(); // Should be 0 if all assigned
        long failedCount = orderRepository.findByStatus(OrderStatus.FAILED).size();
        System.out.println("Total Orders: " + orderRepository.findAll().size());
        System.out.println("Delivered Orders: " + deliveredCount);
        System.out.println("Pending Orders: " + pendingCount);
        System.out.println("Failed Orders: " + failedCount);


        // Bonus: Levenshtein Distance for typo checking
        System.out.println("\n--- Bonus: Address Typo Check (Levenshtein) ---");
        String correctAddress = "Mirpur Road";
        String typedAddress1 = "Mirpur Raod"; // 1 typo
        String typedAddress2 = "Mirpur Avenue"; // high difference
        System.out.printf("Distance between '%s' and '%s': %d\n", correctAddress, typedAddress1,
                LevenshteinDistance.calculate(correctAddress, typedAddress1));
        System.out.printf("Distance between '%s' and '%s': %d\n", correctAddress, typedAddress2,
                LevenshteinDistance.calculate(correctAddress, typedAddress2));

    }
}