package ru.tt.DogShelter.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.tt.DogShelter.model.PersonDog;
import ru.tt.DogShelter.service.PersonDogService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonDogController.class)
class PersonDogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonDogService personDogService;

    @Test
    void getById() throws Exception {
        PersonDog personDog = new PersonDog();
        personDog.setId(1L);

        when(personDogService.getById(anyLong())).thenReturn(personDog);

        mockMvc.perform(
                        get("/person-dog/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(personDogService).getById(1L);
    }

    @Test
    void save() throws Exception {
        PersonDog personDog = new PersonDog();
        personDog.setId(1L);
        personDog.setName("person");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "person");

        when(personDogService.create(personDog)).thenReturn(personDog);

        mockMvc.perform(
                        post("/person-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(personDogService).create(personDog);
    }

    @Test
    void update() throws Exception {
        PersonDog personDog = new PersonDog();
        personDog.setId(1L);
        personDog.setName("person");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "person");

        when(personDogService.update(personDog)).thenReturn(personDog);

        mockMvc.perform(
                        put("/person-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(personDogService).update(personDog);
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("/person-dog/{id}", 1))
                .andExpect(status().isOk());
        verify(personDogService).removeById(1L);
    }

    @Test
    void getAll() throws Exception {
        when(personDogService.getAll()).thenReturn(List.of(new PersonDog()));

        mockMvc.perform(
                        get("/person-dog/all"))
                .andExpect(status().isOk());
    }
}