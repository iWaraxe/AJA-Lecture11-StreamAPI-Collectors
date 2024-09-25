package com.coherentsolutions.advanced.java.section21;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ex04CollectorAnatomy
 *
 * This class breaks down the anatomy of a Collector by implementing a custom collector
 * that accumulates strings into a concatenated single string with a delimiter.
 */
public class Ex04CollectorAnatomy {
    public static void main(String[] args) {
        // Creating a stream of words
        Stream<String> wordsStream = Stream.of("Java", "Streams", "Collectors", "Are", "Powerful");

        // Creating a custom collector to concatenate words with a space delimiter
        Collector<String, StringBuilder, String> concatenator = Collector.of(
                StringBuilder::new,                     // Supplier: Provides a new StringBuilder
                (sb, word) -> {
                    if (sb.length() > 0) {
                        sb.append(" ");
                    }
                    sb.append(word);
                },                  // Accumulator: Appends each word to the StringBuilder
                (sb1, sb2) -> sb1.append(" ").append(sb2), // Combiner: Merges two StringBuilders with a space
                StringBuilder::toString                // Finisher: Converts StringBuilder to String
                //Collector.Characteristics.IDENTITY_FINISH // Characteristics: Indicates finisher is identity
        );

        // Using the custom collector to concatenate words
        String sentence = wordsStream.collect(concatenator);
        // Standard Collector from Java Library
        //String sentence = wordsStream.collect(Collectors.joining(" "));
        // Displaying the concatenated sentence
        System.out.println("Concatenated Sentence: " + sentence); // Output: Java Streams Collectors Are Powerful
    }
}
