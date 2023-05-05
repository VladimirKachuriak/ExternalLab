package org.example.ukrflix.controller;

import org.example.ukrflix.config.WebMvcConfig;
import org.example.ukrflix.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration
class RegistrationControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private RegistrationController registrationController;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

/*    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    }*/
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    void registration() throws Exception {
        this.mockMvc.perform(get("/registration")).
                andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("registration")));
    }

    @Test
    void addUser() throws Exception {
        when(userService.findByLogin(any(String.class))).thenReturn(null);
        //System.out.println(userService.findByLogin("user5"));
        this.mockMvc.perform(post("/registration").
                        param("login","user5").
                        param("password","12345").
                        param("firstname","Kiril").
                        param("lastname","Nestor").
                        param("email","my@gmail.com").
                        param("birthday","2022-02-03").
                        param("phone_num","+1234567")).
                andDo(print()).andExpect(status().is3xxRedirection());
        verify(userService,times(1)).findByLogin("user5");
    }
}
