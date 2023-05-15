package org.example.ukrflix.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.ukrflix.model.Actor;
import org.example.ukrflix.model.Film;
import org.example.ukrflix.repo.ActorRepo;
import org.example.ukrflix.repo.FilmRepo;
import org.example.ukrflix.service.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @Mock
    private FilmRepo filmRepo;
    @InjectMocks
    private FilmService filmService;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAll() {
        when(filmRepo.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<Film>(List.of(new Film())));
        assertEquals(1, filmService.getAll(1, 2).getTotalElements());
    }

    @Test
    void findById() throws JsonProcessingException {
        Film film = new Film();
        film.setId(2);
        when(filmRepo.findById(2)).thenReturn(Optional.of(film));
        Assertions.assertEquals(2,filmService.findById(2).getId());
    }

    @Test
    void addFilm() {
        Film film = new Film();
        film.setId(2);
        assertEquals(true, filmService.addFilm(film,"bla","bla"));
        verify(filmRepo, times(1)).save(film);
    }


}