package com.coherentsolutions.advanced.java.section22;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ex06CollectorsAveragingExample
 *
 * This class demonstrates how to use the built-in averaging collectors
 * to compute the average of numerical data in a stream.
 */
public class Ex06CollectorsAveragingExample {
    public static void main(String[] args) {
        // Creating a stream of integers from 1 to 100
        Stream<Integer> numbersStream = Stream.iterate(1, n -> n + 1).limit(100);

        // Using Collectors.averagingInt() to compute the average
        double average = numbersStream.collect(Collectors.averagingInt(Integer::intValue));

        // Displaying the average
        System.out.println("Average using Collectors.averagingInt(): " + average); // Output: 50.5

        // Since the stream is consumed, we need to recreate it
        numbersStream = Stream.iterate(1, n -> n + 1).limit(100);

        // Alternatively, using IntStream.average()
        double averageIntStream = numbersStream.mapToInt(Integer::intValue).average().orElse(0.0);
        System.out.println("Average using IntStream.average(): " + averageIntStream); // Output: 50.5
    }
}
