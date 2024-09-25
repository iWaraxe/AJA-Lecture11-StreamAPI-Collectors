package com.coherentsolutions.advanced.java.section22.advanced;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Ex18CollectorsCustomImmutableMapExample
 *
 * This class demonstrates how to create a custom collector that accumulates
 * elements into an immutable Map. It collects Person objects into an unmodifiable Map
 * where the key is the person's name and the value is their age, keeping the maximum age in case of duplicate names.
 */
public class Ex18CollectorsCustomImmutableMapExample {
    public static void main(String[] args) {
        // Creating a stream of Person objects with duplicate names
        Stream<Person> peopleStream = Stream.of(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Alice", 35), // Duplicate name with higher age
                new Person("Charlie", 28)
        );

        // Using the custom collector to collect into an immutable Map
        Map<String, Integer> nameToAgeMap = peopleStream.collect(toImmutableNameAgeMap());

        // Displaying the immutable Map
        System.out.println("Immutable Map: " + nameToAgeMap);
        // Output: Immutable Map: {Alice=35, Bob=25, Charlie=28}

        // Attempting to modify the map will throw an UnsupportedOperationException
        try {
            nameToAgeMap.put("David", 40);
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable map: " + e.getMessage());
        }
    }

    /**
     * Creates a custom collector that accumulates Person objects into an immutable Map.
     * The key is the person's name, and the value is their age. In case of duplicate names,
     * the maximum age is kept.
     *
     * @return a Collector that collects Person objects into an unmodifiable Map<String, Integer>
     */
    public static Collector<Person, Map<String, Integer>, Map<String, Integer>> toImmutableNameAgeMap() {
        return new Collector<Person, Map<String, Integer>, Map<String, Integer>>() {

            @Override
            public Supplier<Map<String, Integer>> supplier() {
                // Supplier: Provides a new mutable HashMap
                return HashMap::new;
            }

            @Override
            public BiConsumer<Map<String, Integer>, Person> accumulator() {
                // Accumulator: Adds a Person to the map, keeping the max age for duplicate names
                return (map, person) -> map.merge(
                        person.getName(),
                        person.getAge(),
                        Integer::max
                );
            }

            @Override
            public BinaryOperator<Map<String, Integer>> combiner() {
                // Combiner: Merges two maps by keeping the max age for duplicate names
                return (map1, map2) -> {
                    map2.forEach((key, value) ->
                            map1.merge(key, value, Integer::max)
                    );
                    return map1;
                };
            }

            @Override
            public Function<Map<String, Integer>, Map<String, Integer>> finisher() {
                // Finisher: Returns an immutable copy of the map
                return Collections::unmodifiableMap;
            }

            @Override
            public Set<Characteristics> characteristics() {
                // Characteristics: None in this case
                return EnumSet.noneOf(Characteristics.class);
            }
        };
    }
}

/**
 * Person
 *
 * A simple class representing a person with a name and age.
 */
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}
