package com.smartcourier.algorithms;

import com.smartcourier.models.Order;
import com.smartcourier.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {

    /**
     * Solves the 0/1 Knapsack problem to select the best set of orders for a vehicle.
     * This implementation uses dynamic programming.
     * For simplicity, we use weight as the "value" to maximize capacity utilization.
     *
     * @param vehicle The vehicle with a specific capacity.
     * @param orders  The list of available orders to choose from.
     * @return A list of orders assigned to the vehicle.
     */
    public List<Order> assignPackages(Vehicle vehicle, List<Order> orders) {
        // We need integer weights for the classic DP solution.
        // We can scale weights by a factor (e.g., 10) to handle decimals.
        int capacity = (int) (vehicle.getMaxCapacity() * 10);
        int n = orders.size();

        int[] weights = new int[n];
        int[] values = new int[n]; // Value is same as weight for this problem
        for (int i = 0; i < n; i++) {
            weights[i] = (int) (orders.get(i).getPackageWeight() * 10);
            values[i] = weights[i];
        }

        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find which items were included
        List<Order> assignedOrders = new ArrayList<>();
        int res = dp[n][capacity];
        int w = capacity;
        for (int i = n; i > 0 && res > 0; i--) {
            if (res != dp[i - 1][w]) {
                assignedOrders.add(orders.get(i - 1));
                res -= values[i - 1];
                w -= weights[i - 1];
            }
        }

        return assignedOrders;
    }
}