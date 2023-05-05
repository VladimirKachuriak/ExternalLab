package org.example.ukrflix.controller;

import org.apache.log4j.Logger;
import org.example.ukrflix.models.User;
import org.example.ukrflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.util.Objects;

@Controller
public class RegistrationController {
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        LOGGER.info("Entered /registration");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Model model, @ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult
            , @RequestParam(name = "passwordRepeat", required = false) String password) {
        User userDB = userService.findByLogin(user.getLogin());
        if (userDB != null) {
            model.addAttribute("loginMessage", "label.warning.userAlreadyExist");
            return "registration";
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!user.getPassword().equals(password)) {
            model.addAttribute("passwordRepeatError", "label.warning.passwordRepeat");
            return "registration";
        }
        userService.addUser(user);
        LOGGER.info("user was added successfully");
        LOGGER.info("redirect /login");
        return "redirect:/login";
    }

}
