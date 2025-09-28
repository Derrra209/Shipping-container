package calculation;

import tanks.Item;
import java.util.ArrayList;
import java.util.List;

/**
 * Order class to represent a shipping order
 */
public class Order {
    private List<Item> items;
    private String destinationCity;
    private double totalVolumeGallons;
    private double totalVolumeCubicMeters;
    private double totalWeight;
    
    public Order(String destinationCity) {
        this.destinationCity = destinationCity;
        this.items = new ArrayList<>();
        this.totalVolumeGallons = 0.0;
        this.totalVolumeCubicMeters = 0.0;
        this.totalWeight = 0.0;
    }
    
    // Getters and Setters
    public List<Item> getItems() { return items; }
    public String getDestinationCity() { return destinationCity; }
    public void setDestinationCity(String destinationCity) { this.destinationCity = destinationCity; }
    
    public double getTotalVolumeGallons() { return totalVolumeGallons; }
    public double getTotalVolumeCubicMeters() { return totalVolumeCubicMeters; }
    public double getTotalWeight() { return totalWeight; }
    
    public void addItem(Item item) {
        items.add(item);
        updateTotals();
    }
    
    private void updateTotals() {
        totalVolumeGallons = 0.0;
        totalVolumeCubicMeters = 0.0;
        totalWeight = 0.0;
        
        for (Item item : items) {
            totalVolumeGallons += item.getVolumeInGallons();
            totalVolumeCubicMeters += item.getVolumeInCubicMeters();
            totalWeight += item.calculateWeight(item.getVolumeInGallons());
        }
    }
    
    public void printOrderInfo() {
        System.out.println("=== ORDER INFORMATION ===");
        System.out.println("Destination: " + destinationCity);
        System.out.println("Number of Items: " + items.size());
        System.out.println("Total Volume: " + String.format("%.2f", totalVolumeGallons) + 
                          " gallons (" + String.format("%.4f", totalVolumeCubicMeters) + " cubic meters)");
        System.out.println("Total Weight: " + String.format("%.2f", totalWeight) + " kg");
        System.out.println("\nItems in Order:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println("Item " + (i + 1) + ":");
            items.get(i).printItemInfo();
        }
        System.out.println("========================");
    }
}