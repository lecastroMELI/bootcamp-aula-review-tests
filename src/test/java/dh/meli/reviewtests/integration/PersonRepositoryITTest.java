package dh.meli.reviewtests.integration;

import dh.meli.reviewtests.model.Person;
import dh.meli.reviewtests.repository.PersonRepository;
import dh.meli.reviewtests.util.GeneratePerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // INDICA QUE SERÁ UM TESTE
public class PersonRepositoryITTest {

    @Autowired
    private PersonRepository repository;

    @Test // INDICA QUE ESTE MÉTODO É UM TESTE
    public void save_returnSavedPerson_WhenValidPerson() {
        // CENÁRIO
        // CRIA UMA PESSOA PARA PODER SALVAR
        Person person = GeneratePerson.newPerson1ToSave();

        // EXECUTA
        Person personSaved = repository.save(person);

        // TESTA
        assertThat(personSaved.getName()).isEqualTo(person.getName());

    }

}
