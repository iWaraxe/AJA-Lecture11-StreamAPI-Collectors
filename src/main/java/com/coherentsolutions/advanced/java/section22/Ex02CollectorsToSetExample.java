package com.coherentsolutions.advanced.java.section22;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ex02CollectorsToSetExample
 *
 * This class demonstrates how to use the built-in collector Collectors.toSet()
 * to accumulate stream elements into a Set, which automatically removes duplicates.
 */
public class Ex02CollectorsToSetExample {
    public static void main(String[] args) {
        // Creating a stream of names with duplicates
        Stream<String> namesStream = Stream.of("Alice", "Bob", "Charlie", "David", "Alice");

        // Using Collectors.toSet() to collect names into a Set, eliminating duplicates
        Set<String> namesSet = namesStream.collect(Collectors.toSet());

        // Displaying the Set of names
        System.out.println("Set: " + namesSet);
        // Output: Set: [Alice, Bob, Charlie, David]
    }
}
