package dev.lpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {

    public static void main(String[] args) {
        Course pymc= new Course("PYMC", "Python Masterclass", 50);
        Course jmc= new Course("JMC", "Java Masterclass", 100);

        List<Student> myStudents = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(5000)
                // Doing it this way so we can modify this list
                .collect(Collectors.toList());


        Optional<Student> o1 = getStudent(new ArrayList<>(),"first");
        System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
        System.out.println(o1);


        // prints nothing because this optional is empty
        // this is good
        o1.ifPresentOrElse(System.out::println, () -> System.out.println("----> Emptye"));

        Optional<Student> o2 = getStudent(myStudents,"first");
        System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
        System.out.println(o2);

        o2.ifPresent(System.out::println);

//        Student firstStudent = (o2.orElse(getDummyStudent(jmc)));
        Student firstStudent = o2.orElseGet(() -> getDummyStudent(jmc));
        long id = firstStudent.getStudentId();

        System.out.println("firstStudent id : " + id);



        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        List<String> countries= myStudents.stream()
                .map(Student::getCountryCode).distinct()
                .toList();

        Optional.of(countries)
                .map(l -> String.join(",", l))
                .filter(l -> l.contains("FR"))
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Missing FR"));
    }

    // `type` is type of retrieval, which is first, last or any
    // Any method retuning an Optional should never return null. Instead it should return an empty Optional.
    private static Optional<Student> getStudent(List<Student> list, String type){

        if (list == null || list.size() == 0){
            return Optional.empty();
        }
        else if (type.equals("first")){
            return Optional.ofNullable(list.get(0));
        }
        else if (type.equals("last")){
            return Optional.ofNullable(list.get(list.size() - 1));
        }

        return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
    }


    private static Student getDummyStudent (Course ... courses){
        System.out.println("Getting dummy student");
        return new Student ("NO", 1, 1, "U", false, courses);
    }

}
