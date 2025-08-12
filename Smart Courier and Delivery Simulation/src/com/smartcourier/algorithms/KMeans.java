package com.smartcourier.algorithms;

import com.smartcourier.models.Location;
import com.smartcourier.models.Order;

import java.util.*;
import java.util.stream.Collectors;

public class KMeans {

    private static final int MAX_ITERATIONS = 100;

    /**
     * Clusters a list of orders into k clusters based on their location.
     *
     * @param orders The list of orders to cluster.
     * @param k      The number of clusters (e.g., number of available drivers).
     * @return A map where the key is the cluster centroid (a Location) and the value is the list of orders in that cluster.
     */
    public Map<Location, List<Order>> cluster(List<Order> orders, int k) {
        if (orders == null || orders.isEmpty() || k <= 0) {
            return Collections.emptyMap();
        }

        List<Location> centroids = initializeCentroids(orders, k);
        Map<Location, List<Order>> clusters = new HashMap<>();

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            // Assignment step
            clusters = assignOrdersToClusters(orders, centroids);

            // Update step
            List<Location> newCentroids = calculateNewCentroids(clusters);

            // Check for convergence
            if (centroids.equals(newCentroids)) {
                break;
            }
            centroids = newCentroids;
        }

        return clusters;
    }

    private List<Location> initializeCentroids(List<Order> orders, int k) {
        List<Location> centroids = new ArrayList<>();
        List<Order> shuffledOrders = new ArrayList<>(orders);
        Collections.shuffle(shuffledOrders);
        for (int i = 0; i < k && i < shuffledOrders.size(); i++) {
            centroids.add(shuffledOrders.get(i).getDeliveryLocation());
        }
        return centroids;
    }

    private Map<Location, List<Order>> assignOrdersToClusters(List<Order> orders, List<Location> centroids) {
        Map<Location, List<Order>> clusters = new HashMap<>();
        for (Location centroid : centroids) {
            clusters.put(centroid, new ArrayList<>());
        }

        for (Order order : orders) {
            Location bestCentroid = findNearestCentroid(order.getDeliveryLocation(), centroids);
            clusters.get(bestCentroid).add(order);
        }
        return clusters;
    }

    private Location findNearestCentroid(Location location, List<Location> centroids) {
        Location nearestCentroid = null;
        double minDistance = Double.MAX_VALUE;

        for (Location centroid : centroids) {
            double distance = location.distanceTo(centroid);
            if (distance < minDistance) {
                minDistance = distance;
                nearestCentroid = centroid;
            }
        }
        return nearestCentroid;
    }

    private List<Location> calculateNewCentroids(Map<Location, List<Order>> clusters) {
        return clusters.entrySet().stream()
                .map(entry -> {
                    List<Order> clusterOrders = entry.getValue();
                    if (clusterOrders.isEmpty()) {
                        return entry.getKey(); // Keep old centroid if cluster is empty
                    }
                    double sumX = 0, sumY = 0;
                    for (Order order : clusterOrders) {
                        sumX += order.getDeliveryLocation().getX();
                        sumY += order.getDeliveryLocation().getY();
                    }
                    return new Location(sumX / clusterOrders.size(), sumY / clusterOrders.size());
                })
                .collect(Collectors.toList());
    }
}