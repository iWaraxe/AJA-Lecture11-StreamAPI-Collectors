package com.coherentsolutions.advanced.java.section10;

import java.util.stream.Stream;

/**
 * Ex06FibonacciSequence
 *
 * This class demonstrates how to create a Fibonacci sequence using Stream.iterate().
 * It generates an infinite stream of Fibonacci number pairs and maps them to extract the first number in each pair.
 * The stream is limited to a finite number of elements to prevent an infinite loop.
 */
public class Ex06FibonacciSequence {
    public static void main(String[] args) {
        // Generating an infinite stream of Fibonacci number pairs
        Stream<long[]> fibonacci = Stream.iterate(
                new long[]{0, 1},                  // Initial pair {0, 1}
                f -> new long[]{f[1], f[0] + f[1]} // Next pair {current, previous + current}
        );

        // Printing the first 10 Fibonacci numbers
        fibonacci.limit(10)
                .map(f -> f[0]) // Extracting the first number from each pair
                .forEach(System.out::println);
    }
}
