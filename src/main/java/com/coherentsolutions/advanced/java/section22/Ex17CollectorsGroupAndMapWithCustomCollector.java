package com.coherentsolutions.advanced.java.section22;

import java.util.*;
import java.util.stream.Collector;

/**
 * Ex17CollectorsGroupAndMapWithCustomCollector
 *
 * This class demonstrates how to create a custom collector that combines grouping and mapping operations.
 * Specifically, it groups students by their grade and collects their names into a list for each grade.
 */
public class Ex17CollectorsGroupAndMapWithCustomCollector {
    public static void main(String[] args) {
        // Creating a list of students with grades
        List<Student> students = Arrays.asList(
                new Student("Alice", "A"),
                new Student("Bob", "B"),
                new Student("Charlie", "A"),
                new Student("David", "C"),
                new Student("Eve", "B"),
                new Student("Frank", "A")
        );

        // Creating a custom collector to group students by grade and collect their names
        Collector<Student, ?, Map<String, List<String>>> groupByGradeAndCollectNames = Collector.of(
                HashMap::new, // Supplier: Provides a new HashMap
                (map, student) -> { // Accumulator: Groups students by grade and collects names
                    map.computeIfAbsent(student.getGrade(), k -> new ArrayList<>()).add(student.getName());
                },
                (map1, map2) -> { // Combiner: Merges two maps by combining the lists of names
                    map2.forEach((grade, names) ->
                            map1.merge(grade, names, (existingNames, newNames) -> {
                                existingNames.addAll(newNames);
                                return existingNames;
                            })
                    );
                    return map1;
                },
                Collector.Characteristics.UNORDERED // Characteristics: Indicates unordered collection
        );

        // Using the custom collector to group students by grade and collect their names
        Map<String, List<String>> studentsByGrade = students.stream()
                .collect(groupByGradeAndCollectNames);

        // Displaying the grouped student names
        System.out.println("Students grouped by grade: " + studentsByGrade);
        // Output: Students grouped by grade: {A=[Alice, Charlie, Frank], B=[Bob, Eve], C=[David]}
    }
}

/**
 * Student
 *
 * A simple class representing a student with a name and grade.
 */
class Student {
    private String name;
    private String grade;

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getGrade() { return grade; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (" + grade + ")";
    }
}
