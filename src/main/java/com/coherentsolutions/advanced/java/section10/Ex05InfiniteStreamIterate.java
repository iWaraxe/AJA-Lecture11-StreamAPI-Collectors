package com.coherentsolutions.advanced.java.section10;

import java.util.stream.Stream;

/**
 * Ex05InfiniteStreamIterate
 *
 * This class demonstrates how to create an infinite stream using Stream.iterate().
 * It generates an infinite stream of even numbers starting from 0 and limits the stream to a finite number of elements.
 */
public class Ex05InfiniteStreamIterate {
    public static void main(String[] args) {
        // Generating an infinite stream of even numbers starting from 0
        Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2);

        // Limiting the stream to 10 elements to handle the infinite nature safely
        evenNumbers.limit(10)
                .forEach(System.out::println);
    }
}
