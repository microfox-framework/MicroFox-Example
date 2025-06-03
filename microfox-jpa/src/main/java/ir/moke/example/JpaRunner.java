package ir.moke.example;

import ir.moke.example.entity.Person;
import ir.moke.microfox.db.jpa.MicroFoxJpa;
import jakarta.persistence.EntityManager;

public class JpaRunner {
    static {
        DB.initializeJPA();
    }
    public static void main(String[] args) {

        Person person = new Person("Mahdi", "Sheikh Hosseini");

        EntityManager em = MicroFoxJpa.getEntityManager("h2-connection");
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();

        Person dbPerson = em.find(Person.class, 1L);
        System.out.println(dbPerson);
    }
}
