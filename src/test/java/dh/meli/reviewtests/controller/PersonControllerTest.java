package dh.meli.reviewtests.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dh.meli.reviewtests.model.Person;
import dh.meli.reviewtests.service.PersonService;
import dh.meli.reviewtests.util.GeneratePerson;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc; // A FORMA QUE IREI ACIONAR O CONTROLLER DE FORMA SIMULADA

    @Autowired
    private ObjectMapper objectMapper; // PARA MANIPULAÇÃO DO Json para String

    @MockBean
    private PersonService service;


    @Test
    public void create_insertNewPerson_whenNewPerson() throws Exception {
        // CRIAR UM MOCK DO SERVICE - QUANDO CHAMAR O SAVE COM QQ OBJETO DO TIPO PERSON
        BDDMockito.given(service.save(ArgumentMatchers.any(Person.class)))
            // VAI RESPONDER DA SEGUINTE FORMA: O QUE RECEBER COMO PARÂMETRO É O QUE VOCÊ VAI DEVOLVER
            .willAnswer((invocation) -> invocation.getArgument(0));

        Person person = GeneratePerson.newPerson1ToSave();

        // ACIONAMENTO
        ResultActions response = mockMvc.perform(post("/person")
            .content(objectMapper.writeValueAsString(person)) // ENVIAR um json
            .contentType(MediaType.APPLICATION_JSON) // TIPO DE CONTEÚDO QUE ESTÁ SENDO ENVIADO
        );

        // VERIFICA QUE É UM JSON E SE DENTRO DELE TEM O NAME
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                    CoreMatchers.is(person.getName())));
    }
    /*
    * $ representa a raiz
    * .name é o nome da chave
    * */
}
