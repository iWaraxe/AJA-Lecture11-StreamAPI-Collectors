package com.coherentsolutions.advanced.java.section22;

/**
 * Employee
 *
 * A simple class representing an employee with a name, department, and city.
 */
public class Employee {
    private String name;
    private String department;
    private String city;

    public Employee(String name, String department, String city) {
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