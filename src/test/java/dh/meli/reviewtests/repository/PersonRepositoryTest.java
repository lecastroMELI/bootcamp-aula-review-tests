package dh.meli.reviewtests.repository;

import dh.meli.reviewtests.model.Person;
import dh.meli.reviewtests.util.GeneratePerson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// IMPORTE ESTÁTICO PARA NÃO PRECISAR FICAR DIGITANDO O NOME DA CLASSE
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

// INDICA QUE SERÁ UM TESTE
@DataJpaTest
// INFORMA QUE É PARA USAR O BANCO DE DADOS REAL. IMPORTANTE ALTERAR O application.properties PARA 'dev'
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {

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

    @Test
    public void getAll_returnListPerson_whenGet() {
        Person person1 = GeneratePerson.validPerson1();
        Person person2 = GeneratePerson.validPerson2();

        repository.save(person1);
        repository.save(person2);

        List<Person> listPerson = repository.findAll();

        assertThat(listPerson.size()).isEqualTo(2);
    }
}
