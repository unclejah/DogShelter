package ru.tt.DogShelter.controller;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.tt.DogShelter.model.PersonCat;
import ru.tt.DogShelter.service.PersonCatService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonCatController.class)
class PersonCatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonCatService personCatService;

    @Test
    void getById() throws Exception {
        PersonCat personCat = new PersonCat();
        personCat.setId(1L);

        when(personCatService.getById(anyLong())).thenReturn(personCat);

        mockMvc.perform(
                        get("/person-cat/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(personCatService).getById(1L);
    }

    @Test
    void save() throws Exception {
        PersonCat personCat = new PersonCat();
        personCat.setId(1L);
        personCat.setName("person");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "person");

        when(personCatService.create(personCat)).thenReturn(personCat);

        mockMvc.perform(
                        post("/person-cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(personCatService).create(personCat);
    }

    @Test
    void update() throws Exception {
        PersonCat personCat = new PersonCat();
        personCat.setId(1L);
        personCat.setName("person");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "person");

        when(personCatService.update(personCat)).thenReturn(personCat);

        mockMvc.perform(
                        put("/person-cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(personCatService).update(personCat);
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("/person-cat/{id}", 1))
                .andExpect(status().isOk());
        verify(personCatService).removeById(1L);
    }

    @Test
    void getAll() throws Exception {
        when(personCatService.getAll()).thenReturn(List.of(new PersonCat()));

        mockMvc.perform(
                        get("/person-cat/all"))
                .andExpect(status().isOk());
    }
}