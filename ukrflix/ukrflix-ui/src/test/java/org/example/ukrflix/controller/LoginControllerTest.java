package org.example.ukrflix.controller;

import org.example.ukrflix.model.User;
import org.example.ukrflix.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;
    @Mock
    private UserService userService;
    @Mock
    private MockHttpSession session;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    public void setUp(){
        session = new MockHttpSession();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();
    }
    @Test
    void loginPage() throws Exception {
        this.mockMvc.perform(get("/login")
                        .characterEncoding("utf-8")).
                andDo(print()).andExpect(status().isOk());

    }

    @Test
    void signUpUser() throws Exception {
        User user = new User();
        user.setLogin("user5");
        user.setPassword("1234");
        when(userService.findByLogin("user5")).thenReturn(user);
        this.mockMvc.perform(post("/login").
                        param("login","user5").
                        param("password","1234")
                        .session(session)
                        .characterEncoding("utf-8")).
                andDo(print()).andExpect(status().is3xxRedirection());
        verify(userService,times(1)).findByLogin("user5");
        verify(session,times(1)).setAttribute("login","user5");
    }
}