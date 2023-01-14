package ru.tt.DogShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tt.DogShelter.model.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}