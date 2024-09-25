package com.coherentsolutions.advanced.java.section21.advanced;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Ex07ImmutableCollectorExample
 *
 * This class demonstrates how to create a custom collector that accumulates
 * stream elements into an immutable List.
 */
public class Ex07ImmutableCollectorExample {
    public static void main(String[] args) {
        // Creating a stream of animals
        Stream<String> animalsStream = Stream.of("Lion", "Tiger", "Bear", "Elephant");

        // Creating a custom collector to collect animals into an immutable List
        Collector<String, List<String>, List<String>> toImmutableListCollector = Collector.of(
                ArrayList::new,                       // Supplier: Provides a new ArrayList
                List::add,                            // Accumulator: Adds an element to the ArrayList
                (list1, list2) -> {                   // Combiner: Merges two ArrayLists
                    list1.addAll(list2);
                    return list1;
                },
                Collections::unmodifiableList,        // Finisher: Converts the ArrayList to an unmodifiable List
                Collector.Characteristics.UNORDERED    // Characteristics: Indicates unordered collection
        );

        // Using the custom collector to collect animals into an immutable List
        List<String> immutableAnimals = animalsStream.collect(toImmutableListCollector);

        // Displaying the immutable list of animals
        System.out.println("Immutable Animals: " + immutableAnimals); // Output: [Lion, Tiger, Bear, Elephant]

        // Attempting to modify the list will throw an UnsupportedOperationException
        try {
            immutableAnimals.add("Giraffe");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable list: " + e.getMessage());
        }
    }
}
