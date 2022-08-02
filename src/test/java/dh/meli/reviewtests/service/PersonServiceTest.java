package dh.meli.reviewtests.service;

import dh.meli.reviewtests.exception.InvalidPersonParam;
import dh.meli.reviewtests.model.Person;
import dh.meli.reviewtests.repository.PersonRepository;
import dh.meli.reviewtests.util.GeneratePerson;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

// MOKITO É UMA DEPENDÊNCIA, QUE VAI PERMITIR USAR MOCK
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    // INDICA QUE VAI RECEBER UMA INJEÇÃO DE DEPENDÊNCIA QUE SERÁ UM MOCK
    @InjectMocks
    private PersonService personService;

    // GERA UM MOCK DO REPOSITÓRIO QUE SERÁ USADO DENTRO DO SERVIÇO
    // CRIA UMA SIMULAÇÃO DO REPOSITÓRIO REAL
    @Mock
    private PersonRepository personRepository;


    @Test
    public void save_savePerson_whenValidPerson() {
        // QUANDO CHAMAR O MÉTODO SAVE COM QUALQUER ARGUMENTO/OBJETO DO TIPO PERSON
        BDDMockito.when(personRepository.save(ArgumentMatchers.any(Person.class)))
            // RETORNA A PESSOA 1
            .thenReturn(GeneratePerson.validPerson1());

        Person person = GeneratePerson.newPerson1ToSave();

        // O SERVICE VAI CHAMAR O MÉTODO save() DO REPOSITORY E QUANDO ISSO ACONTECER VAI CHAMAR A SIMULAÇÃO (O MOCK)
        Person personSaved = personService.save(person);

        assertThat(personSaved).isNotNull();
        assertThat(personSaved.getId()).isPositive();
        assertThat(personSaved.getName()).isEqualTo(person.getName());

        /* ESSE MÉTODO VERIFY RECEBE 2 PARÂMETROS:
        * - O QUE SERÁ VERIFICADO: personRepository
        * - A QUANTIDADE DE VEZES QUE O MÉTODO SERÁ CHAMADO: 1
        * A SEGUIR USAMOS O .save() QUE É O MÉTODO QUE SERÁ VERIFICADO SE ELE FOI CHAMADO 1 VEZ */
        Mockito.verify(personRepository, Mockito.times(1)).save(person);
    }


    @Test
    public void save_throwException_whenPersonHasId() {
        Person person = GeneratePerson.validPerson2();

        // Person personSaved = personService.save(person); // ESSA CHAMADA DARÁ ERRO PORTANTO ELA DEVE FICAR DENTRO DO ASSERT THROWS

        assertThrows(InvalidPersonParam.class, () -> {
            personService.save(person);
        });

        Mockito.verify(personRepository, Mockito.never()).save(person);
    }

    public void getAll_listPerson_whenGet() {
        BDDMockito.when(personRepository.findAll())
            .thenReturn(GeneratePerson.listPersons());


    }
}
