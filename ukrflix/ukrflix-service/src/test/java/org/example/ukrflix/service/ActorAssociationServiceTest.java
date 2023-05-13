package org.example.ukrflix.service;

import org.example.ukrflix.model.Actor;
import org.example.ukrflix.model.ActorAssociation;
import org.example.ukrflix.model.Film;
import org.example.ukrflix.repo.ActorAssociationRepo;
import org.example.ukrflix.repo.ActorRepo;
import org.example.ukrflix.repo.FilmRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ActorAssociationServiceTest {
    @Mock
    private ActorAssociationRepo actorAssociationRepo;
    @Mock
    private ActorRepo actorRepo;
    @Mock
    private FilmRepo filmRepo;
    @InjectMocks
    private ActorAssociationService actorAssociationService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllByFilmId() {
        when(actorAssociationRepo.findActorAssociationByFilm_Id(1)).thenReturn(Arrays.asList(new ActorAssociation(),new ActorAssociation()));
        assertEquals(2, actorAssociationService.getAllByFilmId(1).size());
        verify(actorAssociationRepo, times(1)).findActorAssociationByFilm_Id(1);
    }

    @Test
    void addActorToFilm() {
        Film film = new Film();
        film.setActorAssociations(new ArrayList<>());
        when(filmRepo.findById(1)).thenReturn(Optional.of(film));
        when(actorRepo.findById(2)).thenReturn(Optional.of(new Actor()));
        assertEquals(true, actorAssociationService.addActorToFilm(1,2, "role"));
        verify(actorAssociationRepo, times(1)).save(any(ActorAssociation.class));
        verify(filmRepo, times(1)).findById(1);
        verify(actorRepo, times(1)).findById(2);
    }

    @Test
    void deleteActorFromFilm() {
        when(actorAssociationRepo.findByFilm_IdAndActor_Id(1,2)).thenReturn(new ActorAssociation());
        assertEquals(true, actorAssociationService.deleteActorFromFilm(1,2));
        verify(actorAssociationRepo, times(1)).delete(any(ActorAssociation.class));
        verify(actorAssociationRepo, times(1)).findByFilm_IdAndActor_Id(1,2);
    }

}