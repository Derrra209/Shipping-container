package calculation;

import tanks.Item;

/**
 * Factory class to create different materials with their properties
 */
public class MaterialFactory {
    
    public static Item createOxygen(double volume) {
        return new Item("Oxygen O2", 17.0, 1.141, volume); // density approximated
    }
    
    public static Item createHydrogen(double volume) {
        return new Item("Hydrogen H2", 18.0, 0.071, volume);
    }
    
    public static Item createNitrogen(double volume) {
        return new Item("Nitrogen N2", 2.0, 0.808, volume);
    }
    
    public static Item createPropene(double volume) {
        return new Item("Propene", 20.0, 1.514, volume);
    }
    
    public static Item createCarbonDioxide(double volume) {
        return new Item("Carbon Dioxide", 10.0, 1.977, volume);
    }
    
    public static Item createMethane(double volume) {
        return new Item("Methane", 18.0, 0.717, volume);
    }
    
    public static Item createBenzeneSupperE95(double volume) {
        return new Item("Benzene Supper E95", 20.0, 3.486, volume);
    }
    
    public static Item createBenzeneSupper(double volume) {
        return new Item("Benzene Supper", 20.0, 3.486, volume);
    }
    
    public static Item createWater(double volume) {
        return new Item("Water", 0.0, 8.345, volume);
    }
    
    public static Item createMilk(double volume) {
        return new Item("Milk", 0.0, 8.6, volume);
    }
}