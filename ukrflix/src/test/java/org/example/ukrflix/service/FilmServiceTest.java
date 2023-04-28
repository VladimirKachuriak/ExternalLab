package org.example.ukrflix.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.ukrflix.models.Actor;
import org.example.ukrflix.models.Film;
import org.example.ukrflix.repo.ActorRepo;
import org.example.ukrflix.repo.FilmRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @Mock
    private FilmRepo filmRepo;
    @Mock
    private ActorRepo actorRepo;
    @InjectMocks
    private FilmService filmService;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAll() {
        when(filmRepo.findAll(PageRequest.of(0, 1))).thenReturn(new PageImpl<Film>(List.of(new Film())));
        assertEquals(1, filmService.getAll(0).getTotalElements());
    }

    @Test
    void findById() throws JsonProcessingException {
        Film film = new Film();
        film.setId(2);
        when(filmRepo.findById(2)).thenReturn(Optional.of(film));
        assertEquals(2,filmService.findById(2).getId());
    }

    @Test
    void addFilm() {
        Film film = new Film();
        film.setId(2);
        assertEquals(true, filmService.addFilm(film,"bla","bla"));
        verify(filmRepo, times(1)).save(film);
    }

    @Test
    void testGetAll() {
    }

    @Test
    void deleteActorFromFilm() {
        Film film = new Film();
        Actor actor = new Actor();
        actor.setId(3);
        film.setActors(new ArrayList<>(List.of(actor)));
        when(filmRepo.findById(1)).thenReturn(Optional.of(film));
        assertEquals(true,filmService.deleteActorFromFilm(1,3));
    }

    @Test
    void addActorFromFilm() {
        Film film = new Film();
        film.setActors(new ArrayList<>());
        when(filmRepo.findById(1)).thenReturn(Optional.of(film));
        when(actorRepo.findById(3)).thenReturn(Optional.of(new Actor()));
        assertEquals(true,filmService.addActorFromFilm(1,3));
    }
}