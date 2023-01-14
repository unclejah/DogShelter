package ru.tt.DogShelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.tt.DogShelter.exceptions.CatNotFoundException;
import ru.tt.DogShelter.model.Cat;
import ru.tt.DogShelter.repository.CatRepository;

import java.util.Collection;

@Service
public class CatService {

    private final CatRepository repository;

    private final Logger logger = LoggerFactory.getLogger(CatService.class);

    public CatService(CatRepository repository) {
        this.repository = repository;
    }

    public Cat getById(Long id) {
        logger.info("Was invoked method to get a cat by id={}", id);
        return repository.findById(id).orElseThrow(CatNotFoundException::new);
    }

    public Cat create(Cat cat) {
        logger.info("Was invoked method to create a cat");
        return repository.save(cat);
    }

    public Cat update(Cat cat) {
        logger.info("Was invoked method to update a cat");
        if (cat.getId() != null) {
            if (getById(cat.getId()) != null) {
                return repository.save(cat);
            }
        }
        throw new CatNotFoundException();
    }

    public Collection<Cat> getAll() {
        logger.info("Was invoked method to get all cats");
        return repository.findAll();
    }

    public void removeById(Long id) {
        logger.info("Was invoked method to remove a cat by id={}", id);
        repository.deleteById(id);
    }
}
