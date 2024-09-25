package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex13CollectorsMultiLevelGroupingExample
 *
 * This class demonstrates multi-level grouping using Collectors.groupingBy().
 * It groups employees by department and then by city.
 */
public class Ex13CollectorsMultiLevelGroupingExample {
    public static void main(String[] args) {
        // Creating a list of employees with departments and cities
        List<Employee13> employees = Arrays.asList(
                new Employee13("Alice", "Sales", "NY"),
                new Employee13("Bob", "Sales", "SF"),
                new Employee13("Charlie", "Engineering", "NY"),
                new Employee13("David", "Engineering", "SF"),
                new Employee13("Eve", "Sales", "NY"),
                new Employee13("Frank", "Engineering", "NY")
        );

        // Multi-level grouping: Group by department and then by city
        Map<String, Map<String, List<Employee13>>> employeesByDeptAndCity = employees.stream()
                .collect(Collectors.groupingBy(Employee13::getDepartment,
                        Collectors.groupingBy(Employee13::getCity)));

        // Displaying the multi-level grouped employees
        System.out.println("Employees grouped by department and city: " + employeesByDeptAndCity);
        // Output:
        // Employees grouped by department and city: {Sales={NY=[Alice, Eve], SF=[Bob]}, Engineering={NY=[Charlie, Frank], SF=[David]}}
    }
}

/**
 * Employee
 *
 * A simple class representing an employee with a name, department, and city.
 */
class Employee13 {
    private String name;
    private String department;
    private String city;

    public Employee13(String name, String department, String city) {
        this.name = name;
        this.department = department;
        this.city = city;
    }

    public String getDepartment() { return department; }
    public String getCity() { return city; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name;
    }
}
