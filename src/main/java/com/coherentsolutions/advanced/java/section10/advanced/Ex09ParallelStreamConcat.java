package com.coherentsolutions.advanced.java.section10.advanced;

import java.util.stream.Stream;

/**
 * Ex09ParallelStreamConcat
 *
 * This class demonstrates how to combine streams in parallel using Stream.concat() and parallel streams.
 * It shows the impact of parallel processing on stream concatenation and processing.
 */
public class Ex09ParallelStreamConcat {
    public static void main(String[] args) {
        // Creating two separate parallel streams of numbers
        Stream<Integer> stream1 = Stream.iterate(1, n -> n + 1).limit(5).parallel();
        Stream<Integer> stream2 = Stream.iterate(6, n -> n + 1).limit(5).parallel();

        // Combining the two parallel streams into one
        Stream<Integer> combinedStream = Stream.concat(stream1, stream2);

        // Processing the combined stream in parallel by printing each element
        combinedStream.parallel().forEach(n -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " - Number: " + n);
        });
    }
}
