package calculation;

import tanks.*;
import distance.CityDistance;
import java.util.ArrayList;
import java.util.List;

/**
 * Main calculation class for shipping operations
 */
public class ShippingCalculation {
    private CityDistance cityDistance;
    private List<Order> orders;
    private List<Truck> availableTrucks;
    
    public ShippingCalculation() {
        this.cityDistance = new CityDistance();
        this.orders = new ArrayList<>();
        this.availableTrucks = new ArrayList<>();
        initializeTrucks();
    }
    
    private void initializeTrucks() {
        availableTrucks.add(new SmallTanker());
        availableTrucks.add(new MediumTanker());
        availableTrucks.add(new LargeTanker());
    }
    
    /**
     * Calculate total volume of an order
     */
    public double totalVolume(Order order) {
        return order.getTotalVolumeGallons();
    }
    
    /**
     * Calculate total weight of an order
     */
    public double totalWeight(Order order) {
        return order.getTotalWeight();
    }
    
    /**
     * Find the best shipping method (combination of trucks) for minimum cost
     */
    public List<Truck> bestShipping(Order order) {
        double totalVolume = order.getTotalVolumeGallons();
        List<Truck> bestCombination = new ArrayList<>();
        double minCost = Double.MAX_VALUE;
        
        // Get truck capacities
        double smallCapacity = new SmallTanker().getVolumeGallons();
        double mediumCapacity = new MediumTanker().getVolumeGallons();
        double largeCapacity = new LargeTanker().getVolumeGallons();
        
        // Generate efficient combinations
        List<List<Truck>> combinations = generateEfficientTruckCombinations(totalVolume, 
            smallCapacity, mediumCapacity, largeCapacity);
        
        for (List<Truck> combination : combinations) {
            double combinationVolume = 0.0;
            for (Truck truck : combination) {
                combinationVolume += truck.getVolumeGallons();
            }
            
            if (combinationVolume >= totalVolume) {
                double cost = calculateCombinationCost(combination, order);
                if (cost < minCost) {
                    minCost = cost;
                    bestCombination = new ArrayList<>(combination);
                }
            }
        }
        
        // If no combination found, use the largest truck available
        if (bestCombination.isEmpty()) {
            bestCombination.add(new LargeTanker());
        }
        
        return bestCombination;
    }
    
    /**
     * Calculate shipping price for an order
     */
    public double shippingPrice(Order order, List<Truck> trucks) {
        double distance = cityDistance.getDistance(order.getDestinationCity());
        double totalVolume = order.getTotalVolumeGallons();
        double avgRiskFactor = calculateAverageRiskFactor(order);
        
        // Base cost: 0.01 Euro per gallon per kilometer
        double baseCost = totalVolume * distance * 0.01;
        
        // Risk factor cost
        double riskCost = baseCost * (avgRiskFactor / 100.0);
        
        // Return trip cost: 0.6 Euro per kilometer
        double returnCost = distance * 0.6;
        
        return baseCost + riskCost + returnCost;
    }
    
    private double calculateAverageRiskFactor(Order order) {
        double totalRisk = 0.0;
        double totalVolume = 0.0;
        
        for (Item item : order.getItems()) {
            double itemVolume = item.getVolumeInGallons();
            totalRisk += item.getRiskFactor() * itemVolume;
            totalVolume += itemVolume;
        }
        
        return totalVolume > 0 ? totalRisk / totalVolume : 0.0;
    }
    
    private double calculateCombinationCost(List<Truck> combination, Order order) {
        return shippingPrice(order, combination);
    }
    
    /**
     * Generate efficient truck combinations using dynamic programming approach
     */
    private List<List<Truck>> generateEfficientTruckCombinations(double requiredVolume, 
            double smallCap, double mediumCap, double largeCap) {
        List<List<Truck>> combinations = new ArrayList<>();
        
        // Calculate maximum number of each truck type needed
        int maxLarge = (int) Math.ceil(requiredVolume / largeCap) + 1;
        int maxMedium = (int) Math.ceil(requiredVolume / mediumCap) + 1;
        int maxSmall = (int) Math.ceil(requiredVolume / smallCap) + 1;
        
        // Limit to reasonable numbers to avoid performance issues
        maxLarge = Math.min(maxLarge, 10);
        maxMedium = Math.min(maxMedium, 15);
        maxSmall = Math.min(maxSmall, 20);
        
        // Generate combinations
        for (int large = 0; large <= maxLarge; large++) {
            for (int medium = 0; medium <= maxMedium; medium++) {
                for (int small = 0; small <= maxSmall; small++) {
                    if (large + medium + small > 0) { // At least one truck
                        double totalCapacity = (large * largeCap) + (medium * mediumCap) + (small * smallCap);
                        
                        // Only consider combinations that can handle the required volume
                        if (totalCapacity >= requiredVolume) {
                            List<Truck> combo = new ArrayList<>();
                            
                            // Add trucks to combination
                            for (int i = 0; i < large; i++) {
                                combo.add(new LargeTanker());
                            }
                            for (int i = 0; i < medium; i++) {
                                combo.add(new MediumTanker());
                            }
                            for (int i = 0; i < small; i++) {
                                combo.add(new SmallTanker());
                            }
                            
                            combinations.add(combo);
                        }
                    }
                }
            }
        }
        
        // If no valid combinations found, add single large truck as fallback
        if (combinations.isEmpty()) {
            List<Truck> fallback = new ArrayList<>();
            fallback.add(new LargeTanker());
            combinations.add(fallback);
        }
        
        return combinations;
    }
    
    /**
     * Add items to an order
     */
    public void addItems(Order order, Item item) {
        order.addItem(item);
    }
    
    /**
     * Add order to the system
     */
    public void addOrder(Order order) {
        orders.add(order);
    }
    
    /**
     * Print item information
     */
    public void printItem(Item item) {
        item.printItemInfo();
    }
    
    /**
     * Print order information with shipping details
     */
    public void printOrder(Order order) {
        order.printOrderInfo();
        
        List<Truck> bestTrucks = bestShipping(order);
        double totalCost = shippingPrice(order, bestTrucks);
        
        System.out.println("\n=== SHIPPING SOLUTION ===");
        System.out.println("Best Shipping Method:");
        
        // Count truck types
        long smallCount = bestTrucks.stream().filter(t -> t instanceof SmallTanker).count();
        long mediumCount = bestTrucks.stream().filter(t -> t instanceof MediumTanker).count();
        long largeCount = bestTrucks.stream().filter(t -> t instanceof LargeTanker).count();
        
        if (smallCount > 0) System.out.println("- " + smallCount + " Small Tanker(s)");
        if (mediumCount > 0) System.out.println("- " + mediumCount + " Medium Tanker(s)");
        if (largeCount > 0) System.out.println("- " + largeCount + " Large Tanker(s)");
        
        System.out.println("Total Shipping Cost: €" + String.format("%.2f", totalCost));
        System.out.println("Distance to " + order.getDestinationCity() + ": " + 
                          cityDistance.getDistance(order.getDestinationCity()) + " km");
        System.out.println("========================");
    }
    
    public void addCustomCity(String cityName, double distance) {
        cityDistance.addCity(cityName, distance);
    }
    
    public boolean cityExists(String cityName) {
        return cityDistance.cityExists(cityName);
    }
}