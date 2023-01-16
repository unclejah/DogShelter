package ru.tt.DogShelter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tt.DogShelter.model.Dog;
import ru.tt.DogShelter.model.PersonCat;
import ru.tt.DogShelter.repository.DogRepository;
import ru.tt.DogShelter.repository.PersonCatRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonCatServiceTest {

    private PersonCatRepository repository = Mockito.mock(PersonCatRepository.class);;

    private PersonCatService personCatService = new PersonCatService(repository);


    private List<PersonCat> expected = Arrays.asList(
            new PersonCat(1L, "Cat1", 1, "123", "123@123", "123", 1L),
            new PersonCat(2L, "Cat2", 2, "123", "123@123", "123", 1L),
            new PersonCat(3L, "Cat3", 3, "123", "123@123", "123", 1L)
    );
    Set<PersonCat> setExpected = new HashSet<>();
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

        assertThat(personCatService.getById(2L)).isEqualTo(expected.get(1));
    }
    @Test
    public void create(){
        when(personCatService.create( expected.get(0))).thenReturn(
                expected.get(0)
        );
        assertThat(personCatService.create( expected.get(0))).isEqualTo(expected.get(0));
    }

    @Test
    public void update(){
        when(personCatService.update( expected.get(2))).thenReturn(
                expected.get(2)
        );
        assertThat(personCatService.update( expected.get(2))).isEqualTo(expected.get(2));
    }

    @Test
    public void getAll(){
        when(personCatService.getAll()).thenReturn(expected);
        assertThat(personCatService.getAll()).isEqualTo(expected);

    }
    @Test
    public void getByChatId(){
        Set<PersonCat> setExpected2 = new HashSet<>();
        setExpected2.add(expected.get(0));
        setExpected2.add(expected.get(1));
        setExpected2.add(expected.get(2));

        assertThat(personCatService.getByChatId(2L)).isEqualTo(setExpected2);
    }

}
