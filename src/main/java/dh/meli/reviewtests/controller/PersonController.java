package dh.meli.reviewtests.controller;

import dh.meli.reviewtests.model.Person;
import dh.meli.reviewtests.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private IPersonService service;

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person newPerson) {
        Person person = service.save(newPerson);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable long id) {
        Optional<Person> person = service.getById(id);
        if(person.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Person> update(@RequestBody Person person) {
        return new ResponseEntity<>(service.update(person), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
