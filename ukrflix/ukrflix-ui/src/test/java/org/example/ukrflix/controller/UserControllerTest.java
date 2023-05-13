package org.example.ukrflix.controller;

import org.example.ukrflix.model.Film;
import org.example.ukrflix.model.User;
import org.example.ukrflix.service.FilmService;
import org.example.ukrflix.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Mock
    private FilmService filmService;
    @Mock
    private MockHttpSession session;
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();
    }

    @Test
    void catalog() throws Exception {
        when(filmService.getAll(1,2)).thenReturn(new PageImpl<Film>(List.of(new Film())));
        this.mockMvc.perform(get("/catalog").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().isOk());
        verify(filmService,times(1)).getAll(1,2);
    }

    @Test
    void testCatalog() throws Exception {
        this.mockMvc.perform(get("/").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    void topUpPage() throws Exception {
        User user = new User();
        user.setId(4);
        when(session.getAttribute("login")).thenReturn("user4");
        when(userService.findByLogin("user4")).thenReturn(user);
        this.mockMvc.perform(get("/user/topUp").characterEncoding("utf-8").session(session)).
                andDo(print()).andExpect(status().isOk());
        verify(userService, times(1)).findByLogin("user4");
    }

    @Test
    void purchases() throws Exception {
        User user = new User();
        user.setId(4);
        when(session.getAttribute("login")).thenReturn("user4");
        when(userService.findByLogin("user4")).thenReturn(user);
        this.mockMvc.perform(get("/user/purchases").characterEncoding("utf-8").session(session)).
                andDo(print()).andExpect(status().isOk());
        verify(userService, times(1)).findByLogin("user4");
    }

    @Test
    void topUp() throws Exception {
        User user = new User();
        user.setLogin("user5");
        user.setPassword("1234");
        when(session.getAttribute("login")).thenReturn("user5");
        when(userService.findByLogin("user5")).thenReturn(user);
        this.mockMvc.perform(post("/user/topUp").
                        param("money","33").
                        session(session)
                        .characterEncoding("utf-8")).
                andDo(print()).andExpect(status().is3xxRedirection());
        verify(userService,times(1)).findByLogin("user5");
        verify(session,times(1)).getAttribute("login");
    }

    @Test
    void profile() throws Exception {
        User user = new User();
        user.setId(4);
        when(session.getAttribute("login")).thenReturn("user4");
        when(userService.findByLogin("user4")).thenReturn(user);
        this.mockMvc.perform(get("/user/profile").characterEncoding("utf-8").session(session)).
                andDo(print()).andExpect(status().isOk());
        verify(userService, times(1)).findByLogin("user4");
    }
}