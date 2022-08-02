package dh.meli.reviewtests.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.meli.reviewtests.model.Person;
import dh.meli.reviewtests.repository.PersonRepository;
import dh.meli.reviewtests.util.GeneratePerson;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// VAI PEGAR QUALQUER PORTA QUE ESTEJA LIVRE
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// PARA FAZER AS CONFIGURAÇÕES PADRÕES
@AutoConfigureMockMvc
public class PersonControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    // VAI USAR O REPOSITORY DIRETAMENTE PARA MANIPULAR O BD DIRETO. O BD É O DE TESTE
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // COMO O BANCO É O DE TESTE, POSSO ZERAR ELE SEMPRE ANTES DOS TESTES
        // LEMBRANDO QUE ESSE BD FOI CONFIGURADO NO ARQUIVO application-dev.properties LINHA 5
        personRepository.deleteAll();
    }


    // ESSE É O MESMO MÉTODO IMPLEMENTADO NO TESTE UNITÁRIO, POREM SEM O MOCK.
    // AQUI NÃO SERÁ FEITO ROLLBACK, OU SEJA, VAI INSERIR E VAI FICAR LÁ, POR ISSO TEMOS O BEFOREACH.
    // OS TESTES AQUI ESTÃO SENDO FEITOS EM UM BD DE TESTE, CONFIGURADO NO application-dev.properties LINHA 5
    @Test
    public void create_insertNewPerson_whenNewPerson() throws Exception {
        Person person = GeneratePerson.newPerson1ToSave();

        ResultActions response = mockMvc.perform(post("/person")
            .content(objectMapper.writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
            .andExpect(jsonPath("$.name",
                CoreMatchers.is(person.getName())));
    }

}
