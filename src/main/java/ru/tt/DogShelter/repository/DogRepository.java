package ru.tt.DogShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tt.DogShelter.model.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

}
