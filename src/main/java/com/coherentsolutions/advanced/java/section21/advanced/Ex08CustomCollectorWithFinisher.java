package com.coherentsolutions.advanced.java.section21.advanced;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Ex08CustomCollectorWithFinisher
 *
 * This class demonstrates a custom collector that accumulates elements into a mutable structure
 * and then transforms it into a different form using a finisher function.
 * Specifically, it collects integers into a Set and then transforms the Set into a sorted List.
 */
public class Ex08CustomCollectorWithFinisher {
    public static void main(String[] args) {
        // Creating a stream of numbers with duplicates
        Stream<Integer> numbersStream = Stream.of(5, 3, 1, 4, 2, 5, 3, 2);

        // Creating a custom collector to collect numbers into a Set and then into a sorted List
        Collector<Integer, Set<Integer>, List<Integer>> toSortedListCollector = Collector.of(
                HashSet::new,                         // Supplier: Provides a new HashSet
                Set::add,                             // Accumulator: Adds an element to the HashSet
                (set1, set2) -> {                     // Combiner: Merges two HashSets
                    set1.addAll(set2);
                    return set1;
                },
                set -> {                              // Finisher: Transforms the Set into a sorted List
                    List<Integer> sortedList = new ArrayList<>(set);
                    Collections.sort(sortedList);
                    return sortedList;
                },
                Collector.Characteristics.UNORDERED    // Characteristics: Indicates unordered collection
        );

        // Using the custom collector to collect and sort the numbers
        List<Integer> sortedUniqueNumbers = numbersStream.collect(toSortedListCollector);

        // Displaying the sorted unique numbers
        System.out.println("Sorted Unique Numbers: " + sortedUniqueNumbers); // Output: [1, 2, 3, 4, 5]
    }
}
