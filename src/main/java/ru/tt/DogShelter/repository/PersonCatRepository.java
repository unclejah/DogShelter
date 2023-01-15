package ru.tt.DogShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tt.DogShelter.model.PersonCat;

import java.util.Set;

public interface PersonCatRepository extends JpaRepository<PersonCat, Long> {
    Set<PersonCat> findByChatId(Long chatId);

}