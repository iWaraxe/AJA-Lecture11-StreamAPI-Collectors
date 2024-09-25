package com.coherentsolutions.advanced.java.section23;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * Ex01ImmutableMapCollector
 *
 * This class demonstrates how to create a custom collector that accumulates
 * Person objects into an immutable Map<String, Integer> where the key is the person's name,
 * and the value is their age. If duplicate names are encountered, the collector keeps the maximum age.
 */
public class Ex01ImmutableMapCollector {
    public static void main(String[] args) {
        // Creating a list of Person objects with duplicate names
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Alice", 35), // Duplicate name with higher age
                new Person("Charlie", 28)
        );

        // Using the custom collector to collect into an immutable map
        Map<String, Integer> nameToAgeMap = people.stream()
                .collect(toImmutableNameAgeMap());

        // Displaying the immutable map
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
     * Creates a custom collector that accumulates Person objects into an immutable Map<String, Integer>.
     * The key is the person's name, and the value is their age. In case of duplicate names,
     * the collector keeps the maximum age.
     *
     * @return a Collector that collects Person objects into an immutable Map<String, Integer>
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
                // Combiner: Merges two maps by keeping the maximum age for duplicate names
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
                // Characteristics: No specific characteristics
                return EnumSet.noneOf(Characteristics.class);
            }
        };
    }
}

