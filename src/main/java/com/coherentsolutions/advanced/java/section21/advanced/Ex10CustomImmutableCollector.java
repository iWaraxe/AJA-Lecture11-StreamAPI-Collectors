package com.coherentsolutions.advanced.java.section21.advanced;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Ex10CustomImmutableCollector
 *
 * This class demonstrates how to create a custom collector that accumulates
 * elements into an immutable collection. Specifically, it collects strings into
 * an unmodifiable List.
 */
public class Ex10CustomImmutableCollector {
    public static void main(String[] args) {
        // Creating a stream of names
        Stream<String> namesStream = Stream.of("Alice", "Bob", "Charlie", "David");

        // Using the custom collector to collect names into an unmodifiable List
        List<String> immutableNames = namesStream.collect(toImmutableList());

        // Displaying the immutable list of names
        System.out.println("Immutable Names: " + immutableNames); // Output: [Alice, Bob, Charlie, David]

        // Attempting to modify the list will throw an UnsupportedOperationException
        try {
            immutableNames.add("Eve");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable list: " + e.getMessage());
        }
    }

    /**
     * Creates a custom collector that accumulates elements into an unmodifiable List.
     *
     * @param <T> the type of input elements to the reduction operation
     * @return a Collector that collects elements into an unmodifiable List
     */
    public static <T> Collector<T, List<T>, List<T>> toImmutableList() {
        return Collector.of(
                ArrayList::new,                       // Supplier: Provides a new ArrayList
                List::add,                            // Accumulator: Adds an element to the ArrayList
                (list1, list2) -> {                   // Combiner: Merges two ArrayLists
                    list1.addAll(list2);
                    return list1;
                },
                Collections::unmodifiableList,        // Finisher: Converts the ArrayList to an unmodifiable List
                Collector.Characteristics.UNORDERED    // Characteristics: Indicates unordered collection
        );
    }
}
