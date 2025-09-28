package distance;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to manage city distances from Hamburg
 */
public class CityDistance {
    private Map<String, Double> cityDistances;
    
    public CityDistance() {
        cityDistances = new HashMap<>();
        initializeCityDistances();
    }
    
    private void initializeCityDistances() {
        // Distances from Hamburg harbor to various cities (approximate distances from Google Maps)
        cityDistances.put("Berlin", 290.0);
        cityDistances.put("Munich", 612.0);
        cityDistances.put("Leipzig", 294.0);
        cityDistances.put("Dresden", 383.0);
        cityDistances.put("Köln", 356.0);
        cityDistances.put("Rome", 1533.0);
        cityDistances.put("Paris", 769.0);
        cityDistances.put("Wien", 879.0);
        cityDistances.put("Madrid", 1769.0);
    }
    
    public double getDistance(String city) {
        return cityDistances.getOrDefault(city, 0.0);
    }
    
    public void addCity(String city, double distance) {
        cityDistances.put(city, distance);
    }
    
    public boolean cityExists(String city) {
        return cityDistances.containsKey(city);
    }
    
    public Map<String, Double> getAllCities() {
        return new HashMap<>(cityDistances);
    }
}