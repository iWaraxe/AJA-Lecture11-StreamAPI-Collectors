package com.coherentsolutions.advanced.java.section10;

import java.util.stream.Stream;

/**
 * Ex03StreamFlatMapExample
 *
 * This class demonstrates an alternative method to combine multiple streams using Stream.of() and flatMap().
 * It combines three separate streams of beverages into a single stream and processes the combined stream.
 */
public class Ex03StreamFlatMapExample {
    public static void main(String[] args) {
        // Creating three separate streams of beverages
        Stream<String> stream1 = Stream.of("Grape", "Honeydew");
        Stream<String> stream2 = Stream.of("Iced Tea", "Jackfruit");
        Stream<String> stream3 = Stream.of("Kiwi", "Lemon");

        // Combining multiple streams using Stream.of() and flatMap()
        Stream<String> combinedStream = Stream.of(stream1, stream2, stream3)
                .flatMap(s -> s);

        // Processing the combined stream by printing each element
        combinedStream.forEach(System.out::println);
    }
}
