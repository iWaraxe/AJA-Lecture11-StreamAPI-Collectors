package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Ex10CollectorsReducingExample
 *
 * This class demonstrates how to use the built-in collector Collectors.reducing()
 * to perform reduction operations on grouped stream elements.
 */
public class Ex10CollectorsReducingExample {
    public static void main(String[] args) {
        // Creating a list of employees
        List<Employee10> employees = Arrays.asList(
                new Employee10("Alice", "Sales", 3000),
                new Employee10("Bob", "Sales", 4000),
                new Employee10("Charlie", "Engineering", 5000),
                new Employee10("David", "Engineering", 6000),
                new Employee10("Eve", "Sales", 3500),
                new Employee10("Frank", "Engineering", 5500)
        );

        // Finding the highest salary per department using Collectors.groupingBy() and Collectors.reducing()
        Map<String, Optional<Employee10>> highestSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee10::getDepartment,
                        Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Employee10::getSalary)))));

        // Displaying the highest salary by department
        System.out.println("Highest salary by department:");
        highestSalaryByDept.forEach((dept, emp) -> {
            System.out.println(dept + ": " + emp.orElse(null));
        });
        // Output:
        // Highest salary by department:
        // Sales: Bob ($4000)
        // Engineering: David ($6000)
    }
}

/**
 * Employee
 *
 * A simple class representing an employee with a name, department, and salary.
 */
class Employee10 {
    private String name;
    private String department;
    private int salary;

    public Employee10(String name, String department, int salary) {
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
