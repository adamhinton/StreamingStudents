package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class MainChallenge {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass", 50);
        Course jmc= new Course("JMC", "Java Masterclass", 100);
        Course cgj = new Course("CGJ", "Creating Games In Java");

        List<Student> myStudents = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(5000)
                .toList();

        // [1] get avg percent complete
        // for just JMC
        // use reduce
        // Not sure if this is just for students who have started JMC
        double totalPctCompleteJMC = myStudents.stream()
                .mapToDouble(student -> student.getPercentComplete("JMC"))
                .reduce((double) 0, (total, percentComplete) -> total + percentComplete);

        double avgPercentComplete = totalPctCompleteJMC / myStudents.size();


        System.out.println("avgPercentCompleteJMC : " + avgPercentComplete);




        // [2]
        myStudents
                .stream()
                .filter(student ->
                                (student.getPercentComplete("JMC") > avgPercentComplete * 1.25)
                                    && student.getMonthsSinceActive() > 6)
                .sorted(Comparator.comparing(Student::getYearsSinceEnrolled).reversed())
                .limit(10)
                .forEach(student -> {
                    student.addCourse(cgj);
                    System.out.println(student);
                });



    }

}

// Specs

// main method does:
    // Copy two courses, passing both an addtl arg for lecture count; 50 pymc, 100jmc

