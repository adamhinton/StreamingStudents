package dev.lpa;

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


        Optional<Student> o1 = getStudent(null, "first");
        System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
        System.out.println(o1);
    }

    // `type` is type of retrieval, which is first, last or any
    // Any method retuning an Optional should never return null. Instead it should return an Optional.
    private static Optional<Student> getStudent(List<Student> list, String type){

        if (list == null || list.size() == 0){
            return null;
        }
        else if (type.equals("first")){
            return Optional.of(list.get(0));
        }
        else if (type.equals("last")){
            return Optional.of(list.get(list.size() - 1));
        }

        return Optional.of(list.get(new Random().nextInt(list.size())));
    }

}
