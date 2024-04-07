package dev.lpa;

import java.util.List;
import java.util.stream.Stream;

public class MainChallenge {

    Course pymc= new Course("PYMC", "Python Masterclass", 50);
    Course jmc= new Course("JMC", "Java Masterclass", 100);
    Course cgj = new Course("CGJ", "Creating Games In Java");

    List<Student> myStudents = Stream
            .generate(() -> Student.getRandomStudent())
            .limit(5000)
            .toList();



}

// Specs

// main method does:
    // Copy two courses, passing both an addtl arg for lecture count; 50 pymc, 100jmc

