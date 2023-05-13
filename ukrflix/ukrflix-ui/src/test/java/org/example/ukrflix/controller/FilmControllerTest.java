package org.example.ukrflix.controller;

import org.example.ukrflix.model.Film;
import org.example.ukrflix.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FilmControllerTest {

    @Mock
    private FilmService filmService;
    @Mock
    private ActorAssociationService actorAssociationService;
    @Mock
    private PurchaseService purchaseService;
    @Mock
    private ActorService actorService;
    @InjectMocks
    private FilmController filmController;
    @Mock
    private MockHttpSession session;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    public void setUp() {
        session = new MockHttpSession();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmController).setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();
    }
    @Test
    void newFilm() throws Exception {
        this.mockMvc.perform(get("/film/newFilm").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().isOk());
    }

    @Test
    void addFilm() throws Exception {
        this.mockMvc.perform(post("/film/newFilm").
                        param("name","Avatar").
                        param("releaseDate","2022-02-03").
                        param("price","22").
                        param("imgSrc","link").
                        param("ytSrc","link").
                        param("descriptionEn","En").
                        param("descriptionUk","UK")
                        .characterEncoding("utf-8")).
                andDo(print()).andExpect(status().is3xxRedirection());
        verify(filmService,times(1)).addFilm(any(Film.class),any(String.class),any(String.class));
    }

    @Test
    void watchFilm() throws Exception {
        this.mockMvc.perform(get("/film/1/watch").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().isOk());
        verify(filmService,times(1)).findById(1);
    }

    @Test
    void filmDescription() throws Exception {
        Film film = new Film();
        film.setId(1);
        when(filmService.findById(1)).thenReturn(film);
        when(actorService.getActorsNotInFilm(1,"")).thenReturn(Arrays.asList());
        this.mockMvc.perform(get("/film/1").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().isOk());
        verify(filmService,times(1)).findById(1);
        verify(actorService,times(1)).getActorsNotInFilm(1,null);
    }

    @Test
    void makePurchase() throws Exception {
        when(purchaseService.makePurchase("user4", 1)).thenReturn(true);
        this.mockMvc.perform(get("/film/1/buy").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteActorFromFilm() throws Exception {
        this.mockMvc.perform(post("/film/3/deleteActor").
                        param("actorId","2")
                        .characterEncoding("utf-8")).
                andDo(print()).andExpect(status().is3xxRedirection());
        verify(actorAssociationService,times(1)).deleteActorFromFilm(3, 2);
    }

    @Test
    void addActorToFilm() throws Exception {
        this.mockMvc.perform(post("/film/3/addActor").
                        param("actorId","2").
                        param("role","Sully")
                        .characterEncoding("utf-8")).
                andDo(print()).andExpect(status().is3xxRedirection());
        verify(actorAssociationService,times(1)).addActorToFilm(3, 2, "Sully");
    }
}