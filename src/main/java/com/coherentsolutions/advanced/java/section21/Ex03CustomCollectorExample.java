package com.coherentsolutions.advanced.java.section21;

import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Ex03CustomCollectorExample
 *
 * This class demonstrates how to create and use a custom collector to accumulate
 * stream elements into a TreeSet, maintaining sorted order and eliminating duplicates.
 */
public class Ex03CustomCollectorExample {
    public static void main(String[] args) {
        // Creating a stream of names with duplicates
        Stream<String> namesStream = Stream.of("Charlie", "Alice", "Bob", "Alice", "Eve");

        // Creating a custom collector to collect names into a TreeSet
        Collector<String, ?, TreeSet<String>> toTreeSetCollector = Collector.of(
                TreeSet::new,           // Supplier: Provides a new TreeSet
                TreeSet::add,           // Accumulator: Adds an element to the TreeSet
                (left, right) -> {      // Combiner: Merges two TreeSets
                    left.addAll(right);
                    return left;
                },
                Collector.Characteristics.UNORDERED // Characteristics: Indicates unordered collection
        );

        // Using the custom collector to collect sorted and unique names
        TreeSet<String> sortedNames = namesStream.collect(toTreeSetCollector);

        // Displaying the sorted and unique names
        System.out.println("Sorted Names: " + sortedNames); // Output: [Alice, Bob, Charlie, Eve]
    }
}
