package dh.meli.reviewtests.service;

import dh.meli.reviewtests.model.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    Person save(Person person);
    List<Person> getAll();
    Optional<Person> getById(long id);
    Person update(Person person);
    void delete(long id);
}
