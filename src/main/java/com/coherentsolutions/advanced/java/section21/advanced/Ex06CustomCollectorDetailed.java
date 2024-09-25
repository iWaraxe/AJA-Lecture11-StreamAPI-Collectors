package com.coherentsolutions.advanced.java.section21.advanced;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Ex06CustomCollectorDetailed
 *
 * This class provides a detailed implementation of a custom collector that accumulates
 * Person objects into a TreeSet, maintaining sorted order without duplicates.
 */
public class Ex06CustomCollectorDetailed {
    public static void main(String[] args) {
        // Creating a stream of Person objects with duplicate names
        Stream<Person> peopleStream = Stream.of(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35),
                new Person("Alice", 28),
                new Person("Eve", 40)
        );

        // Creating a custom collector to collect persons into a TreeSet sorted by name
        Collector<Person, ?, TreeSet<Person>> toSortedPersonSetCollector = Collector.of(
                TreeSet::new, // Supplier: Provides a new TreeSet with a custom comparator
                TreeSet::add, // Accumulator: Adds a Person to the TreeSet
                (left, right) -> { // Combiner: Merges two TreeSets
                    left.addAll(right);
                    return left;
                },
                Collector.Characteristics.UNORDERED
        );

        // Using the custom collector to collect sorted and unique persons
        TreeSet<Person> sortedPeople = peopleStream.collect(toSortedPersonSetCollector);

        // Displaying the sorted and unique persons
        System.out.println("Sorted People:");
        sortedPeople.forEach(System.out::println);
        // Output:
        // Sorted People:
        // Person{name='Alice', age=30}
        // Person{name='Bob', age=25}
        // Person{name='Charlie', age=35}
        // Person{name='Eve', age=40}
    }
}

/**
 * Person
 *
 * A simple class representing a person with a name and age.
 * Implements Comparable to define natural ordering based on name.
 */
class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    /**
     * Compares this person with another based on name.
     *
     * @param other the other person to compare to
     * @return a negative integer, zero, or a positive integer as this person's name
     *         is less than, equal to, or greater than the other person's name
     */
    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Overrides equals to compare persons based on name and age.
     *
     * @param o the object to compare to
     * @return true if both name and age are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    /**
     * Overrides hashCode to compute hash based on name and age.
     *
     * @return the hash code of the person
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    /**
     * Overrides toString to provide a readable representation of the person.
     *
     * @return the string representation of the person
     */
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}
