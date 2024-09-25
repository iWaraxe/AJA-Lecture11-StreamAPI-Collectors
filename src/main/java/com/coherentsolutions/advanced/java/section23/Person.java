package com.coherentsolutions.advanced.java.section23;

/**
 * Person
 *
 * A simple class representing a person with a name and age.
 */
public class Person {
    private String name;
    private int age;

    /**
     * Constructs a new Person with the given name and age.
     *
     * @param name the person's name
     * @param age  the person's age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the person's name.
     *
     * @return the name of the person
     */
    public String getName() { return name; }

    /**
     * Returns the person's age.
     *
     * @return the age of the person
     */
    public int getAge() { return age; }
}
