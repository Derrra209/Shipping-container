package tanks;

/**
 * Item class representing a shipment item
 */
public class Item extends Material {
    private double volumeInGallons;
    private double volumeInCubicMeters;
    
    public Item(String name, double riskFactor, double density, double volumeInGallons) {
        super(name, riskFactor, density);
        this.volumeInGallons = volumeInGallons;
        this.volumeInCubicMeters = convertGallonsToCubicMeters(volumeInGallons);
    }
    
    // Getters and Setters
    public double getVolumeInGallons() { 
        return volumeInGallons; 
    }
    
    public void setVolumeInGallons(double volumeInGallons) { 
        this.volumeInGallons = volumeInGallons;
        this.volumeInCubicMeters = convertGallonsToCubicMeters(volumeInGallons);
    }
    
    public double getVolumeInCubicMeters() { 
        return volumeInCubicMeters; 
    }
    
    /**
     * Calculate volume method - converts gallons to cubic meters
     */
    public double calculateVolume() {
        return convertGallonsToCubicMeters(volumeInGallons);
    }
    
    private double convertGallonsToCubicMeters(double gallons) {
        return gallons * 0.00378541; // 1 gallon = 0.00378541 cubic meters
    }
    
    @Override
    public double calculateWeight(double volume) {
        return volume * density;
    }
    
    /**
     * Print item information
     */
    public void printItemInfo() {
        System.out.println("Material: " + name);
        System.out.println("Volume: " + volumeInGallons + " gallons (" + 
                          String.format("%.4f", volumeInCubicMeters) + " cubic meters)");
        System.out.println("Risk Factor: " + riskFactor + "%");
        System.out.println("Density: " + density + " kg/gallon");
        System.out.println("Weight: " + String.format("%.2f", calculateWeight(volumeInGallons)) + " kg");
        System.out.println("------------------------");
    }
}