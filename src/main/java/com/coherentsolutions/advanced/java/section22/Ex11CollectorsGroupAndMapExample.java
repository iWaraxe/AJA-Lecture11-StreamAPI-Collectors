package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex11CollectorsGroupAndMapExample
 *
 * This class demonstrates how to use Collectors.groupingBy() in combination with Collectors.mapping()
 * to perform grouped transformations on stream elements.
 */
public class Ex11CollectorsGroupAndMapExample {
    public static void main(String[] args) {
        // Creating a list of employees
        List<Employee11> employees = Arrays.asList(
                new Employee11("Alice", "Sales", 3000),
                new Employee11("Bob", "Sales", 4000),
                new Employee11("Charlie", "Engineering", 5000),
                new Employee11("David", "Engineering", 6000),
                new Employee11("Eve", "Sales", 3500),
                new Employee11("Frank", "Engineering", 5500)
        );

        // Grouping employees by department and mapping to their names
        Map<String, List<String>> employeeNamesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee11::getDepartment,
                        Collectors.mapping(Employee11::getName, Collectors.toList())));

        // Displaying the employee names grouped by department
        System.out.println("Employee Names by Department: " + employeeNamesByDept);
        // Output:
        // Employee Names by Department: {Sales=[Alice, Bob, Eve], Engineering=[Charlie, David, Frank]}
    }
}

/**
 * Employee
 *
 * A simple class representing an employee with a name, department, and salary.
 */
class Employee11 {
    private String name;
    private String department;
    private int salary;

    public Employee11(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() { return department; }
    public String getName() { return name; }
    public int getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " ($" + salary + ")";
    }
}
