package com.coherentsolutions.advanced.java.section10;

import java.util.stream.Stream;

/**
 * Ex02StreamConcatLimitation
 *
 * This class demonstrates the limitations of Stream.concat(), specifically that it can only combine two streams at a time.
 * It shows an incorrect approach that would cause a compilation error and the correct approach using multiple concat calls.
 */
public class Ex02StreamConcatLimitation {
    public static void main(String[] args) {
        // Creating three separate streams of letters
        Stream<String> stream1 = Stream.of("A", "B");
        Stream<String> stream2 = Stream.of("C", "D");
        Stream<String> stream3 = Stream.of("E", "F");

        // Attempting to combine three streams directly (Incorrect Approach)
        // This will cause a compilation error because Stream.concat() only accepts two streams.
        // Stream<String> combinedStream = Stream.concat(stream1, stream2, stream3);

        // Correct Approach: Combining multiple streams using multiple Stream.concat() calls
        Stream<String> tempCombined = Stream.concat(stream1, stream2);
        Stream<String> combinedStream = Stream.concat(tempCombined, stream3);

        // Processing the combined stream by printing each element
        combinedStream.forEach(System.out::println);
    }
}
