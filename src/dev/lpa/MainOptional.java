package dev.lpa;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {

    Course pymc= new Course("PYMC", "Python Masterclass", 50);
    Course jmc= new Course("JMC", "Java Masterclass", 100);

    List<Student> myStudents = Stream
            .generate(() -> Student.getRandomStudent(jmc, pymc))
            .limit(5000)
            // Doing it this way so we can modify this list
            .collect(Collectors.toList());



}
