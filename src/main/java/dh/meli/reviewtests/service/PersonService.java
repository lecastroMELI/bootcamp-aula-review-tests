package dh.meli.reviewtests.service;

import dh.meli.reviewtests.exception.InvalidPersonParam;
import dh.meli.reviewtests.exception.PersonNotFoundException;
import dh.meli.reviewtests.model.Person;
import dh.meli.reviewtests.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {

    private final PersonRepository repository;

    public Person save(Person person) {
        if(person == null || person.getId() != 0) {
            throw new InvalidPersonParam("Person não pode ser null e não pode ter Id definido");
        }
        return repository.save(person);
    }

    @Override
    public List<Person> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Person> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Person update(Person person) {
        if(person == null || person.getId() == 0) {
            throw new InvalidPersonParam("Person não pode ser null e deve ter Id definido");
        }
        Optional<Person> personFound = repository.findById(person.getId());
        if(personFound.isEmpty()){
            throw new PersonNotFoundException("Person não existe");
        }
        return repository.save(person);
    }

    @Override
    public void delete(long id) {
        Optional<Person> person = repository.findById(id);
        if(person.isEmpty()){
            throw new PersonNotFoundException("Id not found");
        }
        repository.deleteById(id);
    }

}
