package ru.tt.DogShelter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.tt.DogShelter.model.PersonDog;
import ru.tt.DogShelter.repository.PersonDogRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonDogServiceTest {
    private PersonDogRepository repository = Mockito.mock(PersonDogRepository.class);;

    private PersonDogService personDogService = new PersonDogService(repository);


    private List<PersonDog> expected = Arrays.asList(
            new PersonDog(1L, "Dog1", 1, "123", "123@123", "123", 1L),
            new PersonDog(2L, "Dog2", 2, "123", "123@123", "123", 1L),
            new PersonDog(3L, "Dog3", 3, "123", "123@123", "123", 1L)
    );
    Set<PersonDog> setExpected = new HashSet<>();
    @BeforeEach
    void setUp(){
        when(repository.findById(anyLong())).thenReturn(
                Optional.ofNullable(expected.get(1)));

        setExpected.add(expected.get(0));
        setExpected.add(expected.get(1));
        setExpected.add(expected.get(2));
        when(repository.findByChatId(anyLong())).thenReturn(
                setExpected);
    }
    @Test
    public void getById(){

        assertThat(personDogService.getById(2L)).isEqualTo(expected.get(1));
    }
    @Test
    public void create(){
        when(personDogService.create( expected.get(0))).thenReturn(
                expected.get(0)
        );
        assertThat(personDogService.create( expected.get(0))).isEqualTo(expected.get(0));
    }

    @Test
    public void update(){
        when(personDogService.update( expected.get(2))).thenReturn(
                expected.get(2)
        );
        assertThat(personDogService.update( expected.get(2))).isEqualTo(expected.get(2));
    }

    @Test
    public void getAll(){
        when(personDogService.getAll()).thenReturn(expected);
        assertThat(personDogService.getAll()).isEqualTo(expected);

    }
    @Test
    public void getByChatId(){
        Set<PersonDog> setExpected2 = new HashSet<>();
        setExpected2.add(expected.get(0));
        setExpected2.add(expected.get(1));
        setExpected2.add(expected.get(2));

        assertThat(personDogService.getByChatId(2L)).isEqualTo(setExpected2);
    }

}
