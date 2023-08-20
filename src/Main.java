import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        long count = persons.stream()
                .filter(x -> x.getAge() > 18)
                .count();
        System.out.println("Несовершеннолетних людей - " + count);


        List<String> conscript = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 27)
                .map(v -> v.getFamily() + "")
                .collect(Collectors.toList());


        List<Person> worker = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> (x.getSex().equals(Sex.WOMAN) && x.getAge() < 60
                        || (x.getSex().equals(Sex.MAN) && x.getAge() < 65)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

    }
}