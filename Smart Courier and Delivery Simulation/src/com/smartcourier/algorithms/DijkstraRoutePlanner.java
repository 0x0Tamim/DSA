package com.smartcourier.algorithms;

import com.smartcourier.models.Location;
import java.util.*;

public class DijkstraRoutePlanner {

    /**
     * Creates an optimized delivery route (a tour) using the Nearest Neighbor heuristic.
     * It starts at the depot and repeatedly travels to the nearest unvisited location.
     * The "distance" between any two points is their direct Euclidean distance.
     *
     * @param depotLocation    The starting and ending point (e.g., a warehouse).
     * @param deliveryLocations The list of locations to visit.
     * @return An ordered list of locations representing the optimized route.
     */
    public List<Location> findShortestTour(Location depotLocation, List<Location> deliveryLocations) {
        List<Location> tour = new ArrayList<>();
        Set<Location> unvisited = new HashSet<>(deliveryLocations);
        Location currentLocation = depotLocation;

        tour.add(currentLocation);

        while (!unvisited.isEmpty()) {
            Location nearest = null;
            double minDistance = Double.MAX_VALUE;

            // This loop is a simplified version of Dijkstra's, finding the single nearest neighbor
            for (Location nextLocation : unvisited) {
                double distance = currentLocation.distanceTo(nextLocation);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = nextLocation;
                }
            }

            if (nearest != null) {
                tour.add(nearest);
                unvisited.remove(nearest);
                currentLocation = nearest;
            } else {
                // Should not happen if unvisited is not empty
                break;
            }
        }

        // Add the depot again to complete the tour
        tour.add(depotLocation);
        return tour;
    }
}