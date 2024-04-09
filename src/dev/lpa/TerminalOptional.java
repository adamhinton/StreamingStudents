package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOptional {

    public static void main(String[] args) {
        Course pymc= new Course("PYMC", "Python Masterclass", 50);
        Course jmc= new Course("JMC", "Java Masterclass", 100);

        List<Student> myStudents = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(5000)
                // Doing it this way so we can modify this list
                .collect(Collectors.toList());

        int minAge = 18;

        myStudents.stream()
                .filter(student -> student.getAge() <= minAge)
                // Don't count on findAny to retun the first stream el
                .findAny()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                        s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));


        myStudents.stream()
                .filter(student -> student.getAge() <= minAge)
                // findFirst() always return s the first, as opposed to findAny()
                .findFirst()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));


        // findFirst() always return s the first, as opposed to findAny()
        myStudents.stream()
                .filter(student -> student.getAge() <= minAge)
                .min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));


        myStudents.stream()
                .filter(student -> student.getAge() <= minAge)
                .max(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));


        myStudents.stream()
                .filter(student -> student.getAge() <= minAge)
                .mapToInt(Student::getAge)
                .average()
                .ifPresentOrElse(a -> System.out.printf("Avg age under 21: %.2f%n",a),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        myStudents.stream()
                .filter(student -> student.getAge() <= minAge)
                .map(Student::getCountryCode)
                .distinct()
                .reduce((a, b) -> String.join(",", a, b))
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("None found"));


        myStudents.stream()
                .map(Student::getCountryCode)
                .distinct()
                .map(l -> String.join(",", l))
                .filter(l -> l.contains("AU"))
                .findAny()
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Missing AU"));

    }

}
