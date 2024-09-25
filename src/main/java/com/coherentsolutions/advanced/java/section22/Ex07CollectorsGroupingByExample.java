package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex07CollectorsGroupingByExample
 *
 * This class demonstrates how to use the built-in collector Collectors.groupingBy()
 * to group stream elements based on a classifier function. It also shows multi-level grouping.
 */
public class Ex07CollectorsGroupingByExample {
    public static void main(String[] args) {
        // Creating a list of names
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");

        // Grouping names by their length using Collectors.groupingBy()
        Map<Integer, List<String>> namesByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));

        // Displaying the grouped names
        System.out.println("Names grouped by length: " + namesByLength);
        // Output: Names grouped by length: {3=[Bob, Eve], 5=[Alice, David, Frank], 7=[Charlie]}

        // Creating a list of employees with departments and cities
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Sales", "NY"),
                new Employee("Bob", "Sales", "SF"),
                new Employee("Charlie", "Engineering", "NY"),
                new Employee("David", "Engineering", "SF"),
                new Employee("Eve", "Sales", "NY"),
                new Employee("Frank", "Engineering", "NY")
        );

        // Multi-level grouping: Group by department and then by city
        Map<String, Map<String, List<Employee>>> employeesByDeptAndCity = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(Employee::getCity)));

        // Displaying the multi-level grouped employees
        System.out.println("Employees grouped by department and city: " + employeesByDeptAndCity);
        // Output:
        // Employees grouped by department and city: {
        //   Sales={
        //     NY=[Alice, Eve],
        //     SF=[Bob]
        //   },
        //   Engineering={
        //     NY=[Charlie, Frank],
        //     SF=[David]
        //   }
        // }
    }
}


