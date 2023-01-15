package ru.tt.DogShelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.tt.DogShelter.exceptions.PersonCatNotFoundException;
import ru.tt.DogShelter.model.PersonCat;
import ru.tt.DogShelter.repository.PersonCatRepository;

import java.util.Collection;

@Service
public class PersonCatService {
    private final PersonCatRepository repository;

    private final Logger logger = LoggerFactory.getLogger(PersonCatService.class);

    public PersonCatService(PersonCatRepository repository) {
        this.repository = repository;
    }

    public PersonCat getById(Long id) {
        logger.info("Was invoked method to get a personCat by id={}", id);
        return repository.findById(id).orElseThrow(PersonCatNotFoundException::new);
    }

    public PersonCat create(PersonCat personCat) {
        logger.info("Was invoked method to create a personCat");
        return repository.save(personCat);
    }

    public PersonCat update(PersonCat personCat) {
        logger.info("Was invoked method to update a personCat");
        if (personCat.getId() != null) {
            if (getById(personCat.getId()) != null) {
                return repository.save(personCat);
            }
        }
        throw new PersonCatNotFoundException();
    }

    public void removeById(Long id) {
        logger.info("Was invoked method to remove a personCat by id={}", id);
        repository.deleteById(id);
    }

    public Collection<PersonCat> getAll() {
        logger.info("Was invoked method to get all personsCat");
        return repository.findAll();
    }

    public Collection<PersonCat> getByChatId(Long chatId) {
        logger.info("Was invoked method to remove a personsCat by chatId={}", chatId);
        return repository.findByChatId(chatId);
    }
}