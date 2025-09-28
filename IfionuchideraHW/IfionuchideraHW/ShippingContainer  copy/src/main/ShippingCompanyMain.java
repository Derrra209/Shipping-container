package main;

import calculation.*;
import tanks.*;
import java.util.Scanner;

/**
 * Main class for the Shipping Company application
 * Author: [Your Name]
 * This program calculates optimal shipping solutions for liquid materials
 */
public class ShippingCompanyMain {
    private ShippingCalculation calculator;
    private Scanner scanner;
    
    public ShippingCompanyMain() {
        this.calculator = new ShippingCalculation();
        this.scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        // Display student information
        Info studentInfo = new Info(
            "[Your Name]", 
            "[Your Matriculation Number]", 
            "[Your Group]", 
            "June 2025", 
            850
        );
        studentInfo.printInfo();
        
        ShippingCompanyMain app = new ShippingCompanyMain();
        app.runApplication();
    }
    
    private void runApplication() {
        System.out.println("\n=== SHIPPING COMPANY MANAGEMENT SYSTEM ===");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    createNewOrder();
                    break;
                case 2:
                    displayAvailableMaterials();
                    break;
                case 3:
                    displayAvailableCities();
                    break;
                case 4:
                    addCustomCity();
                    break;
                case 5:
                    displayTruckInformation();
                    break;
                case 6:
                    runSampleCalculation();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using Shipping Company System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Create New Shipping Order");
        System.out.println("2. Display Available Materials");
        System.out.println("3. Display Available Cities");
        System.out.println("4. Add Custom City");
        System.out.println("5. Display Truck Information");
        System.out.println("6. Run Sample Calculation");
        System.out.println("0. Exit");
        System.out.println("==================");
    }
    
    private void createNewOrder() {
        System.out.println("\n=== CREATE NEW ORDER ===");
        
        System.out.print("Enter destination city: ");
        String city = scanner.nextLine();
        
        if (!calculator.cityExists(city)) {
            System.out.print("City not found. Enter distance from Hamburg (km): ");
            double distance = getDoubleInput("");
            calculator.addCustomCity(city, distance);
        }
        
        Order order = new Order(city);
        
        boolean addingItems = true;
        while (addingItems) {
            displayAvailableMaterials();
            int materialChoice = getIntInput("Select material (1-10): ");
            double volume = getDoubleInput("Enter volume in gallons: ");
            
            Item item = createItemByChoice(materialChoice, volume);
            if (item != null) {
                calculator.addItems(order, item);
                System.out.println("Item added successfully!");
            } else {
                System.out.println("Invalid material choice.");
            }
            
            System.out.print("Add another item? (y/n): ");
            String response = scanner.nextLine();
            addingItems = response.toLowerCase().startsWith("y");
        }
        
        calculator.addOrder(order);
        calculator.printOrder(order);
    }
    
    private Item createItemByChoice(int choice, double volume) {
        switch (choice) {
            case 1: return MaterialFactory.createOxygen(volume);
            case 2: return MaterialFactory.createHydrogen(volume);
            case 3: return MaterialFactory.createNitrogen(volume);
            case 4: return MaterialFactory.createPropene(volume);
            case 5: return MaterialFactory.createCarbonDioxide(volume);
            case 6: return MaterialFactory.createMethane(volume);
            case 7: return MaterialFactory.createBenzeneSupperE95(volume);
            case 8: return MaterialFactory.createBenzeneSupper(volume);
            case 9: return MaterialFactory.createWater(volume);
            case 10: return MaterialFactory.createMilk(volume);
            default: return null;
        }
    }
    
    private void displayAvailableMaterials() {
        System.out.println("\n=== AVAILABLE MATERIALS ===");
        System.out.println("1. Oxygen O2 (Risk: 17%)");
        System.out.println("2. Hydrogen H2 (Risk: 18%)");
        System.out.println("3. Nitrogen N2 (Risk: 2%)");
        System.out.println("4. Propene (Risk: 20%)");
        System.out.println("5. Carbon Dioxide (Risk: 10%)");
        System.out.println("6. Methane (Risk: 18%)");
        System.out.println("7. Benzene Supper E95 (Risk: 20%)");
        System.out.println("8. Benzene Supper (Risk: 20%)");
        System.out.println("9. Water (Risk: 0%)");
        System.out.println("10. Milk (Risk: 0%)");
        System.out.println("============================");
    }
    
    private void displayAvailableCities() {
        System.out.println("\n=== AVAILABLE CITIES ===");
        System.out.println("1. Berlin (290 km)");
        System.out.println("2. Munich (612 km)");
        System.out.println("3. Leipzig (294 km)");
        System.out.println("4. Dresden (383 km)");
        System.out.println("5. Köln (356 km)");
        System.out.println("6. Rome (1533 km)");
        System.out.println("7. Paris (769 km)");
        System.out.println("8. Wien (879 km)");
        System.out.println("9. Madrid (1769 km)");
        System.out.println("========================");
    }
    
    private void addCustomCity() {
        System.out.println("\n=== ADD CUSTOM CITY ===");
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        double distance = getDoubleInput("Enter distance from Hamburg (km): ");
        
        calculator.addCustomCity(cityName, distance);
        System.out.println("City added successfully!");
    }
    
    private void displayTruckInformation() {
        System.out.println("\n=== TRUCK INFORMATION ===");
        
        SmallTanker small = new SmallTanker();
        MediumTanker medium = new MediumTanker();
        LargeTanker large = new LargeTanker();
        
        small.printTankerInfo();
        medium.printTankerInfo();
        large.printTankerInfo();
    }
    
    private void runSampleCalculation() {
        System.out.println("\n=== SAMPLE CALCULATION ===");
        System.out.println("Sample: 3200 gallons of O2 to Berlin");
        
        Order sampleOrder = new Order("Berlin");
        Item oxygenItem = MaterialFactory.createOxygen(3200);
        calculator.addItems(sampleOrder, oxygenItem);
        
        calculator.printOrder(sampleOrder);
    }
    
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
    
    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return value;
    }
}