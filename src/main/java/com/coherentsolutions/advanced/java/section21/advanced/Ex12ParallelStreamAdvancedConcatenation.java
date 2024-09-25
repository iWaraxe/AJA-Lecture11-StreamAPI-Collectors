package com.coherentsolutions.advanced.java.section21.advanced;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Ex12ParallelStreamAdvancedConcatenation
 *
 * This class demonstrates advanced techniques for combining streams in parallel.
 * It shows how to concatenate multiple parallel streams and handle thread safety during processing.
 */
public class Ex12ParallelStreamAdvancedConcatenation {
    public static void main(String[] args) {
        // Creating multiple parallel streams of numbers
        Stream<Integer> stream1 = Stream.iterate(1, n -> n + 1).limit(5).parallel();
        Stream<Integer> stream2 = Stream.iterate(6, n -> n + 1).limit(5).parallel();
        Stream<Integer> stream3 = Stream.iterate(11, n -> n + 1).limit(5).parallel();

        // Combining multiple parallel streams using flatMap()
        Stream<Integer> combinedStream = Stream.of(stream1, stream2, stream3)
                .flatMap(Function.identity());

        // Processing the combined stream in parallel by summing all numbers
        int sum = combinedStream.parallel()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum of all numbers: " + sum); // Output: Sum of all numbers: 75
    }
}
