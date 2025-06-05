package ir.moke.example.repository;

import ir.moke.example.entity.Person;
import ir.moke.microfox.db.jpa.annotation.*;
import jakarta.validation.constraints.Size;

import java.util.List;

public interface PersonRepository {
    @Persist
    void save(Person person);

    @Query(value = "select p from Person p")
    List<Person> find(@Offset int offset, @Size int size);

    @Query(value = "select p from Person p")
    List<Person> find();

    @Find
    Person find(long id);

    @Query(value = "select p from Person p where p.name=:name")
    Person findByName(@QueryParam("name") String name);

    @Merge
    void update(Person person);

    @Query("select p from Person p where p.id=:id")
    Person findById(@QueryParam("id") long id);

    @Remove
    void delete(Person person);

    @Criteria(provider = PersonCriteriaProvider.class)
    List<Person> find(@QueryParam("id") Long id,
                      @QueryParam("name") String name,
                      @QueryParam("family") String family,
                      @Offset int offset,
                      @MaxResults int maxResult);
}
