package ru.tt.DogShelter.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.tt.DogShelter.model.Cat;

import ru.tt.DogShelter.repository.CatRepository;
import ru.tt.DogShelter.service.CatService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatServiceTest {

    private CatRepository repository = Mockito.mock(CatRepository.class);;

    private CatService catService = new CatService(repository);

    private List<Cat> expected = Arrays.asList(
            new Cat(1L, "1", "Cat1", 10, "Opis1"),
            new Cat(2L, "2", "Cat2", 20, "Opis2"),
            new Cat(3L, "3", "Cat3", 30, "Opis3")
    );
    @BeforeEach
    void setUp(){
        when(repository.findById(anyLong())).thenReturn(
                Optional.ofNullable(expected.get(1)));
    }
    @Test
    public void getById(){
        assertThat(catService.getById(anyLong())).isEqualTo(expected.get(1));
    }
    @Test
    public void create(){
        when(catService.create( expected.get(0))).thenReturn(
                expected.get(0)
        );
        assertThat(catService.create( new Cat(1L, "1", "Cat1", 10, "Opis1"))).isEqualTo(expected.get(0));
    }

    @Test
    public void update(){
        when(catService.update( expected.get(2))).thenReturn(
                expected.get(2)
        );
        assertThat(catService.update( new Cat(3L, "3", "Cat3", 30, "Opis3"))).isEqualTo(expected.get(2));
    }

    @Test
    public void getAll(){
        when(catService.getAll()).thenReturn(expected);
        assertThat(catService.getAll()).isEqualTo(expected);

    }
}
