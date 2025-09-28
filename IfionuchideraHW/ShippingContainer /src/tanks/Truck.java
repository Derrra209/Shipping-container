package tanks;

/**
 * Abstract Truck class
 */
public abstract class Truck {
    protected String truckType;
    protected double lengthInches;
    protected double radiusInches;
    protected double volumeGallons;
    protected double volumeCubicMeters;
    
    public Truck(String truckType, double lengthInches, double radiusInches) {
        this.truckType = truckType;
        this.lengthInches = lengthInches;
        this.radiusInches = radiusInches;
        this.volumeGallons = calculateVolumeInGallons();
        this.volumeCubicMeters = calculateVolumeInCubicMeters();
    }
    
    // Getters and Setters
    public String getTruckType() { return truckType; }
    public void setTruckType(String truckType) { this.truckType = truckType; }
    
    public double getLengthInches() { return lengthInches; }
    public void setLengthInches(double lengthInches) { this.lengthInches = lengthInches; }
    
    public double getRadiusInches() { return radiusInches; }
    public void setRadiusInches(double radiusInches) { this.radiusInches = radiusInches; }
    
    public double getVolumeGallons() { return volumeGallons; }
    public double getVolumeCubicMeters() { return volumeCubicMeters; }
    
    /**
     * Calculate volume in gallons
     */
    public double calculateVolumeInGallons() {
        // Volume = π * r² * h, convert cubic inches to gallons
        double volumeCubicInches = Math.PI * Math.pow(radiusInches, 2) * lengthInches;
        return volumeCubicInches / 231.0; // 231 cubic inches = 1 gallon
    }
    
    /**
     * Calculate volume in cubic meters
     */
    public double calculateVolumeInCubicMeters() {
        return volumeGallons * 0.00378541; // 1 gallon = 0.00378541 cubic meters
    }
    
    /**
     * Print tanker information
     */
    public void printTankerInfo() {
        System.out.println("Truck Type: " + truckType);
        System.out.println("Dimensions: Length=" + lengthInches + " inches, Radius=" + radiusInches + " inches");
        System.out.println("Capacity: " + String.format("%.2f", volumeGallons) + " gallons (" + 
                          String.format("%.4f", volumeCubicMeters) + " cubic meters)");
        System.out.println("------------------------");
    }
}