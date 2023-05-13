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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
/*
    @BeforeEach
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");
        viewResolver.setContentType("text/html; charset=UTF-8");
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).setViewResolvers(viewResolver).build();
    }
*/
@BeforeEach
public void setUp() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setCharacterEncoding("UTF-8");

    /*((GenericWebApplicationContext) context).refresh();*/
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(registrationController).setViewResolvers(new StandaloneMvcTestViewResolver())
            .build();
}

    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }
    @Test
    void registration() throws Exception {
        this.mockMvc.perform(get("/registration/").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().isOk());
    }

    @Test
    void addUser() throws Exception {
        when(userService.findByLogin(any(String.class))).thenReturn(null);
        //System.out.println(userService.findByLogin("user5"));
        this.mockMvc.perform(post("/registration").
                        param("login","user5").
                        param("password","12345").
                        param("passwordRepeat","12345").
                        param("firstname","Kiril").
                        param("lastname","Nestor").
                        param("email","my@gmail.com").
                        param("birthday","2022-02-03").
                        param("phone_num","+1234567").characterEncoding("utf-8")).
                andDo(print()).andExpect(status().is3xxRedirection());
        verify(userService,times(1)).findByLogin("user5");
    }
}
