package main;

/**
 * Student Information Class
 * Name: [Your Name Here]
 * Matriculation Number: [Your Matriculation Number]
 * Group: [Your Group]
 * Date of Creation: June 2025
 * Total Lines of Code: Approximately 800+ lines
 */
public class Info {
    private String studentName;
    private String matriculationNumber;
    private String group;
    private String creationDate;
    private int totalLinesOfCode;
    
    public Info(String studentName, String matriculationNumber, String group, String creationDate, int totalLinesOfCode) {
        this.studentName = studentName;
        this.matriculationNumber = matriculationNumber;
        this.group = group;
        this.creationDate = creationDate;
        this.totalLinesOfCode = totalLinesOfCode;
    }
    
    // Getters and Setters
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    
    public String getMatriculationNumber() { return matriculationNumber; }
    public void setMatriculationNumber(String matriculationNumber) { this.matriculationNumber = matriculationNumber; }
    
    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }
    
    public String getCreationDate() { return creationDate; }
    public void setCreationDate(String creationDate) { this.creationDate = creationDate; }
    
    public int getTotalLinesOfCode() { return totalLinesOfCode; }
    public void setTotalLinesOfCode(int totalLinesOfCode) { this.totalLinesOfCode = totalLinesOfCode; }
    
    public void printInfo() {
        System.out.println("=== Student Information ===");
        System.out.println("Name: " + studentName);
        System.out.println("Matriculation Number: " + matriculationNumber);
        System.out.println("Group: " + group);
        System.out.println("Creation Date: " + creationDate);
        System.out.println("Total Lines of Code: " + totalLinesOfCode);
        System.out.println("============================");
    }
}