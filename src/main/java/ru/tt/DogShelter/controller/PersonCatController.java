package ru.tt.DogShelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tt.DogShelter.model.PersonCat;
import ru.tt.DogShelter.service.PersonCatService;

import java.util.Collection;

@RestController
@RequestMapping("person-cat")
public class PersonCatController {

    private final PersonCatService service;

    public PersonCatController(PersonCatService service) {
        this.service = service;
    }
    @Operation(summary = "Получение пользователя по id")
    @GetMapping("{id}")
    public PersonCat getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @Operation(summary = "Создание пользователя")
    @PostMapping()
    public PersonCat save(@RequestBody PersonCat personCat) {
        return service.create(personCat);
    }

    @Operation(summary = "Изменение данных пользователя")
    @PutMapping
    public PersonCat update(@RequestBody PersonCat personCat) {
        return service.update(personCat);
    }
    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @Operation(summary = "Просмотр всех пользователей", description = "Просмотр всех пользователей, либо определенного пользователя по chat_id")
    @GetMapping("all")
    public Collection<PersonCat> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return service.getByChatId(chatId);
        }
        return service.getAll();
    }
}