package dev.lpa;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class MainMapping {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass", 50);
        Course jmc= new Course("JMC", "Java Masterclass", 100);
        Course cgj = new Course("CGJ", "Creating Games In Java");

        List<Student> myStudents = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(5000)
                .toList();


        var mappedStudents = myStudents.stream()
                .collect(groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));


        System.out.println("--------------------");


        int minAge = 25;
        var youngerSet = myStudents.stream()
                .collect(groupingBy(Student::getCountryCode,
                        filtering(s -> s.getAge() <= minAge, toList())));


        youngerSet.forEach((k, v) -> System.out.println(k + " " + v.size()));



        var experienced = myStudents.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience));
        System.out.println("Experienced students = " + experienced.get(true).size());


        var expCount = myStudents.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience, counting()));
        System.out.println("Experienced students = " + expCount.get(true));



        var experiencedAndActive = myStudents.stream()
                .collect(partitioningBy(
                        s -> s.hasProgrammingExperience()
                        && s.getMonthsSinceActive() == 0,
                        counting()));
        System.out.println("Experienced students = " + experiencedAndActive.get(true));





        var multiLevel = myStudents.stream()
                .collect(groupingBy(Student::getCountryCode,
                        groupingBy(Student::getGender)
                        ));

        multiLevel.forEach((key, value) ->{
            System.out.println(key);
            value.forEach((key1, value1) ->
                    System.out.println("\t" + key1 + " " +  value1.size()));
        });



        // We don't want to use streams for fxnlty that can be easily deduced without them

        // Clunky way to do this
       long studentBodyCount = 0;
       for (var list : experienced.values()){
           studentBodyCount += list.size();
       }
       System.out.println("studentBodyCount = " + studentBodyCount);


       studentBodyCount = experienced.values().stream()
               .mapToInt(l -> l.size())
               .sum();

        System.out.println("studentBodyCount = " + studentBodyCount);




        studentBodyCount = experienced.values().stream()
                .map(l -> l.stream()
                        .filter(s-> s.getMonthsSinceActive() <= 3)
                        .count()
                )
                .mapToLong(l -> l)
                .sum();

        System.out.println("studentBodyCount = " + studentBodyCount);



        long count = experienced.values().stream()
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students = " + count);



        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream()
                        .flatMap(l -> l.stream())
                )
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students in multilevel = " + count);


        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students in multilevel = " + count);


    }

}
