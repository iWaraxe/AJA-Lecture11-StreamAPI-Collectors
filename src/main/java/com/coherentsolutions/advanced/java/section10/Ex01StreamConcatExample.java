package com.coherentsolutions.advanced.java.section10;

import java.util.stream.Stream;

/**
 * Ex01StreamConcatExample
 *
 * This class demonstrates how to merge two streams into one using Stream.concat().
 * It creates two separate streams of fruits and combines them into a single stream.
 * The combined stream is then processed by printing each element.
 */
public class Ex01StreamConcatExample {
    public static void main(String[] args) {
        // Creating two separate streams of fruits
        Stream<String> stream1 = Stream.of("Apple", "Banana", "Cherry");
        Stream<String> stream2 = Stream.of("Date", "Elderberry", "Fig");

        // Combining the two streams into one using Stream.concat()
        Stream<String> combinedStream = Stream.concat(stream1, stream2);

        // Processing the combined stream by printing each element
        combinedStream.forEach(System.out::println);
    }
}
