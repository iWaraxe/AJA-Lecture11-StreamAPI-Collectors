package com.coherentsolutions.advanced.java.section21;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ex02CollectorsReduceVsCollect
 *
 * This class demonstrates the difference between mutable reduction using collect()
 * and immutable reduction using reduce().
 */
public class Ex02CollectorsReduceVsCollect {
    public static void main(String[] args) {
        // Creating a stream of integers
        Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5);

        // Immutable Reduction: Summing numbers using reduce()
        int sum = numberStream
                .reduce(0, Integer::sum); // Starting with 0, summing each element

        System.out.println("Sum using reduce(): " + sum); // Output: Sum: 15

        // Creating another stream of integers for collect()
        Stream<Integer> collectStream = Stream.of(1, 2, 3, 4, 5);

        // Mutable Reduction: Collecting elements into a List using collect()
        List<Integer> numberList = collectStream
                .collect(Collectors.toList()); // Collecting elements into a List

        System.out.println("Number List using collect(): " + numberList); // Output: [1, 2, 3, 4, 5]
    }
}
