import java.util.*;

class Courier {
    String name;
    String location; // current location (lowercase)
    boolean available;

    public Courier(String name, String location) {
        this.name = name;
        this.location = location.toLowerCase(); // normalize
        this.available = true;
    }
}

class Order {
    String id; // stored in uppercase
    String pickup;
    String drop;
    Courier assignedCourier;
    List<String> path;
    int distance;

    public Order(String id, String pickup, String drop) {
        this.id = id.toUpperCase(); // normalize
        this.pickup = pickup.toLowerCase();
        this.drop = drop.toLowerCase();
    }
}

public class SmartCourierSystem {
    static Map<String, List<String>> graph = new HashMap<>();
    static Map<String, Map<String, Integer>> weights = new HashMap<>();
    static List<Courier> couriers = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        setupGraph();
        setupCouriers();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Smart Courier & Delivery Optimisation System ---");
            System.out.println("1. Place new order");
            System.out.println("2. Show all orders");
            System.out.println("3. Search order by ID (Binary Search)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter order ID: ");
                    String id = sc.nextLine();

                    // Pickup location
                    String pickup = getLocationInput(sc, "pickup");

                    // Drop location
                    String drop = getLocationInput(sc, "drop");

                    placeOrder(new Order(id, pickup, drop));
                    break;
                case 2:
                    showOrders();
                    break;
                case 3:
                    System.out.print("Enter Order ID to search: ");
                    String searchId = sc.nextLine().toUpperCase(); // normalize
                    searchOrder(searchId);
                    break;
                case 4:
                    System.out.println("Exiting... Bye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------- Graph Setup ----------------
    static void setupGraph() {
        addEdge("Uttara", "Banani", 10);
        addEdge("Banani", "Gulshan", 5);
        addEdge("Banani", "Mirpur", 8);
        addEdge("Gulshan", "Dhanmondi", 12);
        addEdge("Mirpur", "Dhanmondi", 7);
        addEdge("Farmgate", "Dhanmondi", 6);
        addEdge("Mirpur", "Farmgate", 4);
    }

    static void addEdge(String u, String v, int w) {
        u = u.toLowerCase();
        v = v.toLowerCase();
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());
        weights.putIfAbsent(u, new HashMap<>());
        weights.putIfAbsent(v, new HashMap<>());
        graph.get(u).add(v);
        graph.get(v).add(u);
        weights.get(u).put(v, w);
        weights.get(v).put(u, w);
    }

    // ---------------- Courier Setup ----------------
    static void setupCouriers() {
        couriers.add(new Courier("Rahim", "Uttara"));
        couriers.add(new Courier("Karim", "Mirpur"));
        couriers.add(new Courier("Selim", "Banani"));
    }

    // ---------------- Show Available Locations ----------------
    static void showAvailableLocations() {
        System.out.println("Available locations:");
        for (String loc : graph.keySet()) {
            System.out.println("- " + capitalize(loc));
        }
    }

    static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    // ---------------- Get User Location Input with Validation & Suggestion ----------------
    static String getLocationInput(Scanner sc, String type) {
        String loc;
        while (true) {
            System.out.println("Enter " + type + " location (choose from below):");
            showAvailableLocations();
            loc = sc.nextLine().toLowerCase();

            if (graph.containsKey(loc)) {
                break;
            } else {
                String suggestion = getClosestLocation(loc);
                System.out.println("❌ Invalid location entered!");
                if (suggestion != null) {
                    System.out.println("Did you mean '" + capitalize(suggestion) + "'? Try again.");
                } else {
                    System.out.println("Please choose a valid location from the list.");
                }
            }
        }
        return loc;
    }

    // ---------------- Simple Auto-Suggestion Using Levenshtein Distance ----------------
    static String getClosestLocation(String input) {
        String closest = null;
        int minDist = Integer.MAX_VALUE;

        for (String loc : graph.keySet()) {
            int dist = levenshteinDistance(input, loc);
            if (dist < minDist) {
                minDist = dist;
                closest = loc;
            }
        }
        return minDist <= 2 ? closest : null; // only suggest if distance <= 2
    }

    static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length()+1][b.length()+1];
        for (int i=0; i<=a.length(); i++) dp[i][0] = i;
        for (int j=0; j<=b.length(); j++) dp[0][j] = j;

        for (int i=1; i<=a.length(); i++) {
            for (int j=1; j<=b.length(); j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
            }
        }
        return dp[a.length()][b.length()];
    }

    // ---------------- Place Order ----------------
    static void placeOrder(Order order) {
        Courier nearest = findNearestCourier(order.pickup);
        if (nearest == null) {
            System.out.println("❌ No available courier right now!");
            return;
        }

        // Assign courier
        order.assignedCourier = nearest;
        nearest.available = false;

        // Shortest path
        Map<String, String> parent = new HashMap<>();
        int dist = dijkstra(order.pickup, order.drop, parent);
        order.path = reconstructPath(order.pickup, order.drop, parent);
        order.distance = dist;

        orders.add(order);
        Collections.sort(orders, Comparator.comparing(o -> o.id)); // keep sorted for binary search

        System.out.println("✅ Order placed successfully!");
        System.out.println("Assigned Courier: " + nearest.name);
        System.out.println("Path: " + order.path);
        System.out.println("Total Distance: " + dist + " km");
    }

    // ---------------- Find Nearest Courier ----------------
    static Courier findNearestCourier(String pickup) {
        Courier best = null;
        int bestDist = Integer.MAX_VALUE;

        for (Courier c : couriers) {
            if (c.available) {
                Map<String, String> parent = new HashMap<>();
                int dist = dijkstra(c.location, pickup, parent);
                if (dist < bestDist) {
                    bestDist = dist;
                    best = c;
                }
            }
        }
        return best;
    }

    // ---------------- Dijkstra ----------------
    static int dijkstra(String src, String dest, Map<String, String> parent) {
        Map<String, Integer> dist = new HashMap<>();
        for (String node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(src, 0);

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(src);

        while (!pq.isEmpty()) {
            String u = pq.poll();
            for (String v : graph.getOrDefault(u, new ArrayList<>())) {
                int alt = dist.get(u) + weights.get(u).get(v);
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    parent.put(v, u);
                    pq.add(v);
                }
            }
        }

        return dist.getOrDefault(dest, Integer.MAX_VALUE);
    }

    static List<String> reconstructPath(String src, String dest, Map<String, String> parent) {
        List<String> path = new ArrayList<>();
        String cur = dest;
        while (cur != null && !cur.equals(src)) {
            path.add(cur);
            cur = parent.get(cur);
        }
        if (cur != null) path.add(src);
        Collections.reverse(path);
        return path;
    }

    // ---------------- Show Orders ----------------
    static void showOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }
        for (Order o : orders) {
            System.out.println("Order " + o.id + " | Pickup: " + o.pickup +
                    " | Drop: " + o.drop +
                    " | Courier: " + o.assignedCourier.name +
                    " | Distance: " + o.distance + " km");
        }
    }

    // ---------------- Binary Search for Orders ----------------
    static void searchOrder(String id) {
        int l = 0, r = orders.size() - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int cmp = orders.get(mid).id.compareTo(id);
            if (cmp == 0) {
                Order o = orders.get(mid);
                System.out.println("✅ Found Order " + o.id + " | Pickup: " + o.pickup +
                        " | Drop: " + o.drop + " | Courier: " + o.assignedCourier.name +
                        " | Distance: " + o.distance + " km");
                return;
            } else if (cmp < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println("❌ Order not found!");
    }
}
