package org.example.ukrflix.service;

import org.example.ukrflix.config.WebMvcConfig;
import org.example.ukrflix.models.Actor;
import org.example.ukrflix.repo.ActorRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration
class ActorServiceTest {
    @Mock
    private ActorRepo actorRepo;
    @InjectMocks
    private ActorService actorService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAll() {
        when(actorRepo.findAll()).thenReturn(Arrays.asList(new Actor(),new Actor()));
        assertEquals(2, actorService.getAll().size());
        verify(actorRepo,times(1)).findAll();
    }

    @Test
    void findById() {
        Actor actor = new Actor();
        actor.setId(2);
        when(actorRepo.findById(2)).thenReturn(Optional.of(actor));
        assertEquals(2, actorService.findById(2).getId());
        verify(actorRepo,times(1)).findById(2);
    }

    @Test
    void addActor() {
        assertEquals(true, actorService.addActor(new Actor()));
    }

    @Test
    void getActorsNotInFilm() {
        when(actorRepo.findActorsByFirstnameOrLastname("Bill",2)).thenReturn(new ArrayList<>(List.of(new Actor(), new Actor())));
        assertEquals(2, actorService.getActorsNotInFilm(2, "Bill").size());
    }
}