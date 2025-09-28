package tanks;

/**
 * Abstract base class for all materials
 */
public abstract class Material {
    protected String name;
    protected double riskFactor;
    protected double density; // kg per gallon
    
    // Constructor - this was missing!
    public Material(String name, double riskFactor, double density) {
        this.name = name;
        this.riskFactor = riskFactor;
        this.density = density;
    }
    
    // Getters and Setters
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public double getRiskFactor() { 
        return riskFactor; 
    }
    
    public void setRiskFactor(double riskFactor) { 
        this.riskFactor = riskFactor; 
    }
    
    public double getDensity() { 
        return density; 
    }
    
    public void setDensity(double density) { 
        this.density = density; 
    }
    
    // Abstract method that must be implemented by subclasses
    public abstract double calculateWeight(double volume);
}