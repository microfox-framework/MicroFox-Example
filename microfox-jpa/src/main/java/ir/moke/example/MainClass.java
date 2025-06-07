package ir.moke.example;

import ir.moke.example.entity.Person;
import ir.moke.example.repository.PersonRepository;

import java.util.List;

import static ir.moke.microfox.MicroFox.jpa;
import static ir.moke.microfox.MicroFox.jpaTx;

public class MainClass {
    static {
        DB.initializeJPA();
    }

    public static void main(String[] args) {

        Person person1 = new Person("Mahdi", "Sheikh Hosseini");
        Person person2 = new Person("Javad", "Alikhani");
        Person person3 = new Person("Ali", "Mohammadi");

        // Save all
        jpaTx(PersonRepository.class, "h2", personRepository -> {
            personRepository.save(person1);
            personRepository.save(person2);
            personRepository.save(person3);
        });

        // Print all
        List<Person> list = jpa(PersonRepository.class, "h2", PersonRepository::find);
        list.forEach(System.out::println);

        // Update
        person1.setName("Sina");
        person1.setFamily("Zoheir");
        jpaTx(PersonRepository.class, "h2", personRepository -> personRepository.update(person1));

        // Check update item
        Person p = jpa(PersonRepository.class, "h2", personRepository -> personRepository.find(1));
        System.out.println(p);

        // Delete item
        jpaTx(PersonRepository.class, "h2", personRepository -> personRepository.delete(personRepository.find(1)));

        // Print all
        List<Person> list2 = jpa(PersonRepository.class, "h2", PersonRepository::find);
        list2.forEach(System.out::println);

        // Criteria Query
        List<Person> list3 = jpa(PersonRepository.class, "h2", personRepository -> personRepository.find(2L, null, null, 0, 100));
        list3.forEach(System.out::println);
    }
}
