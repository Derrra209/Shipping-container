package main;

import calculation.*;
import tanks.*;

/**
 * Test class to demonstrate the shipping calculation system
 * Author: [Your Name]
 */
public class TestApplication {
    
    public static void main(String[] args) {
        System.out.println("=== SHIPPING COMPANY TEST APPLICATION ===\n");
        
        // Create calculation instance
        ShippingCalculation calculator = new ShippingCalculation();
        
        // Test 1: Single item order
        System.out.println("TEST 1: Single Item Order");
        System.out.println("-------------------------");
        
        Order order1 = new Order("Berlin");
        Item oxygen = MaterialFactory.createOxygen(3200);
        calculator.addItems(order1, oxygen);
        calculator.addOrder(order1);
        calculator.printOrder(order1);
        
        // Test 2: Multiple items order
        System.out.println("\n\nTEST 2: Multiple Items Order");
        System.out.println("-----------------------------");
        
        Order order2 = new Order("Munich");
        Item hydrogen = MaterialFactory.createHydrogen(1500);
        Item water = MaterialFactory.createWater(2000);
        Item nitrogen = MaterialFactory.createNitrogen(800);
        
        calculator.addItems(order2, hydrogen);
        calculator.addItems(order2, water);
        calculator.addItems(order2, nitrogen);
        calculator.addOrder(order2);
        calculator.printOrder(order2);
        
        // Test 3: Long distance order
        System.out.println("\n\nTEST 3: Long Distance Order");
        System.out.println("----------------------------");
        
        Order order3 = new Order("Madrid");
        Item propene = MaterialFactory.createPropene(5000);
        calculator.addItems(order3, propene);
        calculator.addOrder(order3);
        calculator.printOrder(order3);
        
        // Test 4: Custom city
        System.out.println("\n\nTEST 4: Custom City Order");
        System.out.println("--------------------------");
        
        calculator.addCustomCity("Amsterdam", 358);
        Order order4 = new Order("Amsterdam");
        Item methane = MaterialFactory.createMethane(2500);
        calculator.addItems(order4, methane);
        calculator.addOrder(order4);
        calculator.printOrder(order4);
        
        System.out.println("\n=== ALL TESTS COMPLETED ===");
    }
}