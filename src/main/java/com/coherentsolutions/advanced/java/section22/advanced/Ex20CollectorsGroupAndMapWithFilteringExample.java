package com.coherentsolutions.advanced.java.section22.advanced;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex20CollectorsGroupAndMapWithFilteringExample
 *
 * This class demonstrates how to combine Collectors.groupingBy() with filtering operations
 * to perform selective grouping and mapping of stream elements.
 */
public class Ex20CollectorsGroupAndMapWithFilteringExample {
    public static void main(String[] args) {
        // Creating a list of books with genres and ratings
        List<Book> books = Arrays.asList(
                new Book("The Hobbit", "Fantasy", 4.8),
                new Book("1984", "Dystopian", 4.7),
                new Book("To Kill a Mockingbird", "Classic", 4.9),
                new Book("The Great Gatsby", "Classic", 4.3),
                new Book("Brave New World", "Dystopian", 4.5),
                new Book("Harry Potter", "Fantasy", 4.9)
        );

        // Grouping books by genre and collecting titles of highly rated books (rating >= 4.5)
        Map<String, List<String>> highRatedBooksByGenre = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre,
                        Collectors.mapping(Book::getTitle,
                                Collectors.filtering(title -> {
                                    // Fetching the book's rating based on the title
                                    // Note: In a real scenario, it's better to map to the Book object first
                                    return books.stream()
                                            .filter(book -> book.getTitle().equals(title))
                                            .findFirst()
                                            .map(Book::getRating)
                                            .orElse(0.0) >= 4.5;
                                }, Collectors.toList()))));

        // Displaying the high-rated books grouped by genre
        System.out.println("High-rated Books by Genre: " + highRatedBooksByGenre);
        // Output:
        // High-rated Books by Genre: {Fantasy=[The Hobbit, Harry Potter], Dystopian=[1984, Brave New World], Classic=[To Kill a Mockingbird]}
    }
}

/**
 * Book
 *
 * A simple class representing a book with a title, genre, and rating.
 */
class Book {
    private String title;
    private String genre;
    private double rating; // Rating out of 5.0

    public Book(String title, String genre, double rating) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public String getGenre() { return genre; }
    public String getTitle() { return title; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return title + " (" + genre + ") - Rating: " + rating;
    }
}
