package com.coherentsolutions.advanced.java.section22;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ex04CollectorsJoiningExample
 *
 * This class demonstrates how to use the built-in collector Collectors.joining()
 * to concatenate string elements from a stream into a single string with optional delimiters, prefixes, and suffixes.
 */
public class Ex04CollectorsJoiningExample {
    public static void main(String[] args) {
        // Creating a stream of words
        Stream<String> wordsStream = Stream.of("Java", "Streams", "Are", "Powerful");

        // Simple joining without delimiter
        String joinedWords = wordsStream.collect(Collectors.joining());
        System.out.println("Joined Words: " + joinedWords);
        // Output: Joined Words: JavaStreamsArePowerful

        // Since the stream is consumed, we need to recreate it
        wordsStream = Stream.of("Java", "Streams", "Are", "Powerful");

        // Joining with a delimiter (space)
        String joinedWithSpaces = wordsStream.collect(Collectors.joining(" "));
        System.out.println("Joined with Spaces: " + joinedWithSpaces);
        // Output: Joined with Spaces: Java Streams Are Powerful

        // Recreate the stream
        wordsStream = Stream.of("Java", "Streams", "Are", "Powerful");

        // Joining with delimiter, prefix, and suffix
        String fancyJoined = wordsStream.collect(Collectors.joining(" | ", "Start: ", " :End"));
        System.out.println("Fancy Joined: " + fancyJoined);
        // Output: Fancy Joined: Start: Java | Streams | Are | Powerful :End
    }
}
