package org.example.ukrflix.controller;

import org.example.ukrflix.model.Actor;
import org.example.ukrflix.service.ActorAssociationService;
import org.example.ukrflix.service.ActorService;
import org.example.ukrflix.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ActorControllerTest {
    @Mock
    private ActorService actorService;
    @InjectMocks
    private ActorController actorController;
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(actorController).setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();
    }
    @Test
    void getAll() throws Exception {
        this.mockMvc.perform(get("/actor").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().isOk());
        verify(actorService, times(1)).getAll();
    }

    @Test
    void newActor() throws Exception {
        this.mockMvc.perform(get("/actor/newActor").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().isOk());
    }

    @Test
    void addActor() throws Exception {
        this.mockMvc.perform(post("/actor/newActor").
                        param("firstname","Tom").
                        param("lastname","Hanks").
                        param("birthday","2022-02-03")
                        .characterEncoding("utf-8")).
                andDo(print()).andExpect(status().is3xxRedirection());
        verify(actorService, times(1)).addActor(any(Actor.class));
    }

    @Test
    void getActor() throws Exception {
        this.mockMvc.perform(get("/actor/1").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().isOk());
        verify(actorService, times(1)).findById(1);
    }
}