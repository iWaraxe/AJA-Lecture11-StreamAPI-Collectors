package com.coherentsolutions.advanced.java.section23.advanced;

import com.coherentsolutions.advanced.java.section23.Person;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * Ex08ThreadSafeImmutableCollector
 *
 * This class demonstrates how to create a thread-safe custom collector that accumulates elements
 * into a ConcurrentHashMap and then transforms it into an immutable map.
 * It collects Person objects into an immutable Map<String, Integer> with conflict resolution.
 */
public class Ex08ThreadSafeImmutableCollector {
    public static void main(String[] args) {
        // Creating a list of Person objects with duplicate names
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Alice", 35), // Duplicate name with higher age
                new Person("Charlie", 28),
                new Person("Bob", 40)    // Duplicate name with higher age
        );

        // Using the thread-safe custom collector to collect into an immutable map
        Map<String, Integer> nameToAgeMap = people.parallelStream()
                .collect(toConcurrentImmutableNameAgeMap());

        // Displaying the immutable map
        System.out.println("Immutable Concurrent Map: " + nameToAgeMap);
        // Output: Immutable Concurrent Map: {Alice=35, Bob=40, Charlie=28}

        // Attempting to modify the map will throw an UnsupportedOperationException
        try {
            nameToAgeMap.put("David", 45);
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable map: " + e.getMessage());
        }
    }

    /**
     * Creates a thread-safe custom collector that accumulates Person objects into a ConcurrentHashMap
     * and then transforms it into an immutable Map<String, Integer>.
     * The key is the person's name, and the value is their age. In case of duplicate names,
     * the collector keeps the maximum age.
     *
     * @return a Collector that collects Person objects into an immutable Concurrent Map<String, Integer>
     */
    public static Collector<Person, ConcurrentHashMap<String, Integer>, Map<String, Integer>> toConcurrentImmutableNameAgeMap() {
        return new Collector<Person, ConcurrentHashMap<String, Integer>, Map<String, Integer>>() {

            @Override
            public Supplier<ConcurrentHashMap<String, Integer>> supplier() {
                // Supplier: Provides a new ConcurrentHashMap
                return ConcurrentHashMap::new;
            }

            @Override
            public BiConsumer<ConcurrentHashMap<String, Integer>, Person> accumulator() {
                // Accumulator: Adds a Person to the map, keeping the max age for duplicate names
                return (map, person) -> map.merge(
                        person.getName(),
                        person.getAge(),
                        Integer::max
                );
            }

            @Override
            public BinaryOperator<ConcurrentHashMap<String, Integer>> combiner() {
                // Combiner: Merges two maps by keeping the maximum age for duplicate names
                return (map1, map2) -> {
                    map2.forEach((key, value) ->
                            map1.merge(key, value, Integer::max)
                    );
                    return map1;
                };
            }

            @Override
            public Function<ConcurrentHashMap<String, Integer>, Map<String, Integer>> finisher() {
                // Finisher: Returns an immutable copy of the concurrent map
                return Collections::unmodifiableMap;
            }

            @Override
            public Set<Characteristics> characteristics() {
                // Characteristics: CONCURRENT and UNORDERED for thread-safe and unordered collection
                return EnumSet.of(Characteristics.CONCURRENT, Characteristics.UNORDERED);
            }
        };
    }
}