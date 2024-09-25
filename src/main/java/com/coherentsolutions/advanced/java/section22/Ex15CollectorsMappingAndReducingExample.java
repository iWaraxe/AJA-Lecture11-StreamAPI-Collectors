package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.function.BinaryOperator;

/**
 * Ex15CollectorsMappingAndReducingExample
 *
 * This class demonstrates how to combine Collectors.mapping() and Collectors.reducing()
 * to perform complex operations on grouped stream elements.
 */
public class Ex15CollectorsMappingAndReducingExample {
    public static void main(String[] args) {
        // Creating a list of employees
        List<Employee15> employees = Arrays.asList(
                new Employee15("Alice", "Sales", 3000),
                new Employee15("Bob", "Sales", 4000),
                new Employee15("Charlie", "Engineering", 5000),
                new Employee15("David", "Engineering", 6000),
                new Employee15("Eve", "Sales", 3500),
                new Employee15("Frank", "Engineering", 5500)
        );

        // Grouping employees by department, mapping to their salaries, and finding the maximum salary
        Map<String, Optional<Integer>> maxSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee15::getDepartment,
                        Collectors.mapping(Employee15::getSalary, Collectors.reducing(BinaryOperator.maxBy(Integer::compare)))));

        // Displaying the maximum salary by department
        System.out.println("Maximum Salary by Department:");
        maxSalaryByDept.forEach((dept, maxSalary) -> {
            System.out.println(dept + ": " + maxSalary.orElse(0));
        });
        // Output:
        // Maximum Salary by Department:
        // Sales: 4000
        // Engineering: 6000
    }
}

/**
 * Employee
 *
 * A simple class representing an employee with a name, department, and salary.
 */
class Employee15 {
    private String name;
    private String department;
    private int salary;

    public Employee15(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() { return department; }
    public int getSalary() { return salary; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " ($" + salary + ")";
    }
}
