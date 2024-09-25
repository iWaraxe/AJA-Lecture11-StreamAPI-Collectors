package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex09CollectorsMappingExample
 *
 * This class demonstrates how to use the built-in collector Collectors.mapping()
 * to apply a function to stream elements before collecting them.
 */
public class Ex09CollectorsMappingExample {
    public static void main(String[] args) {
        // Creating a list of words
        List<String> words = Arrays.asList("apple", "banana", "apricot", "blueberry", "cherry");

        // Grouping words by their first letter and collecting the lengths of the words
        Map<Character, List<Integer>> wordLengthByFirstLetter = words.stream()
                .collect(Collectors.groupingBy(word -> word.charAt(0),
                        Collectors.mapping(String::length, Collectors.toList())));

        // Displaying the word lengths by first letter
        System.out.println("Word lengths by first letter: " + wordLengthByFirstLetter);
        // Output: Word lengths by first letter: {a=[5, 7], b=[6, 9], c=[6]}
    }
}
