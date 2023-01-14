package ru.tt.DogShelter.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tt.DogShelter.model.Dog;
import ru.tt.DogShelter.repository.DogRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DogServiceTest {

    private DogRepository repository = Mockito.mock(DogRepository.class);;

    private DogService dogService = new DogService(repository);


    private List<Dog> expected = Arrays.asList(
            new Dog(1L, "1", "Dog1", 10, "Opis1"),
            new Dog(2L, "2", "Dog2", 20, "Opis2"),
            new Dog(3L, "3", "Dog3", 30, "Opis3")
    );
    @BeforeEach
    void setUp(){
        when(repository.findById(anyLong())).thenReturn(
                Optional.ofNullable(expected.get(1)));
    }
    @Test
    public void getById(){

        assertThat(dogService.getById(2L)).isEqualTo(expected.get(1));
    }
    @Test
    public void create(){
        when(dogService.create( expected.get(0))).thenReturn(
                expected.get(0)
        );
        assertThat(dogService.create( new Dog(1L, "1", "Dog1", 10, "Opis1"))).isEqualTo(expected.get(0));
    }

    @Test
    public void update(){
        when(dogService.update( expected.get(2))).thenReturn(
                expected.get(2)
        );
        assertThat(dogService.update( new Dog(3L, "3", "Dog3", 30, "Opis3"))).isEqualTo(expected.get(2));
    }

    @Test
    public void getAll(){
        when(dogService.getAll()).thenReturn(expected);
      assertThat(dogService.getAll()).isEqualTo(expected);

    }


}
