package dev.lpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");
//        Student tim = new Student("AU", 2019, 30, "M",
//                true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//        System.out.println(tim);

        int studentListLength = 1000;
        List<Student> myStudents = new ArrayList<>();
        for (int i=0; i<studentListLength; i++){
            myStudents.add(Student.getRandomStudent(jmc, pymc));
        }

        //[1] Count of male and female
        long femCount = myStudents.stream()
                .filter(student -> student.getGender() == "F")
                .count();
        long maleCount = myStudents.stream()
                .filter(student -> student.getGender() == "M")
                .count();

        System.out.println("MaleCount = " + maleCount + " FemCount " + femCount);




        // [2] How many in each age range: 0-30, 30-60, 60+

        long zeroToThirtyCount = myStudents.stream()
                .filter(student -> student.getAge() < 30)
                .count();

        long thirtyToSixtyCount = myStudents.stream()
                .filter(student -> student.getAge() >=30 && student.getAge() <=60)
                .count();

        long sixtyPlusCount = studentListLength - zeroToThirtyCount - thirtyToSixtyCount;

        System.out.println("0-29: " + zeroToThirtyCount);
        System.out.println("30-60: " + thirtyToSixtyCount);
        System.out.println("61+: " + sixtyPlusCount);



        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Starting #3 --------");

        // [3]

         var sumStat = myStudents.stream()
                 .mapToInt(student -> student.getAge())
                 .summaryStatistics();

         System.out.println("Age summary statistics: " + sumStat);



         // [4]
            // Print distinct list of country codes
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Starting #4 --------");


        var distinctCountryCodes = myStudents.stream()
                .map(student -> student.getCountryCode())
                .collect(Collectors.toSet());

        System.out.println("Distinct country codes = " + distinctCountryCodes);

    }
}
