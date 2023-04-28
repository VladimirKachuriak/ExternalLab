package org.example.ukrflix.controller;

import org.apache.log4j.Logger;
import org.example.ukrflix.models.User;
import org.example.ukrflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String loginPage(){
        LOGGER.info("Entered /login");
        return "login";
    }
    @PostMapping("/login")
    public String signUpUser(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password,
                             HttpServletRequest request, RedirectAttributes redirectAttributes){
        User user = userService.findByLogin(login);
        if(user ==null){
            redirectAttributes.addFlashAttribute("message","label.warning.incorrectLogin");
            LOGGER.debug("user entered incorrect login");
            return "redirect:/login";
        }
        if(!user.getPassword().equals(password)){
            redirectAttributes.addFlashAttribute("message","label.warning.incorrectPassword");
            LOGGER.debug("user entered incorrect password");
            return "redirect:/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("login",login);
        redirectAttributes.addFlashAttribute("message","label.message.loginSuccess");
        LOGGER.info("user was logged successfully");
        return "redirect:/catalog";
    }
}
