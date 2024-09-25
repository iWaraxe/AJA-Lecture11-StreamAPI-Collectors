package com.coherentsolutions.advanced.java.section23.advanced;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Ex07CustomCollectorWithCustomFinisher
 *
 * This class demonstrates a custom collector that accumulates elements into a mutable structure
 * and then transforms it into a different form using a finisher function.
 * Specifically, it collects strings into a TreeSet to remove duplicates and sort them,
 * and then transforms the TreeSet into a comma-separated String.
 */
public class Ex07CustomCollectorWithCustomFinisher {
    public static void main(String[] args) {
        // Creating a stream of strings with duplicates
        Stream<String> wordsStream = Stream.of("apple", "banana", "apple", "cherry", "banana", "date");

        // Using the custom collector to collect and transform the data
        String commaSeparated = wordsStream.collect(toSortedUniqueCommaSeparatedString());

        // Displaying the resulting string
        System.out.println("Comma Separated Sorted Unique Words: " + commaSeparated);
        // Output: Comma Separated Sorted Unique Words: apple, banana, cherry, date
    }

    /**
     * Creates a custom collector that accumulates String elements into a TreeSet to remove duplicates and sort them,
     * and then transforms the TreeSet into a comma-separated String.
     *
     * @return a Collector that collects String elements into a sorted, unique, comma-separated String
     */
    public static Collector<String, TreeSet<String>, String> toSortedUniqueCommaSeparatedString() {
        return Collector.of(
                TreeSet::new,                     // Supplier: Provides a new TreeSet
                TreeSet::add,                     // Accumulator: Adds an element to the TreeSet
                (set1, set2) -> {                // Combiner: Merges two TreeSets
                    set1.addAll(set2);
                    return set1;
                },
                set -> String.join(", ", set),    // Finisher: Transforms the TreeSet into a comma-separated String
                Collector.Characteristics.UNORDERED // Characteristics: Indicates unordered collection
        );
    }
}
