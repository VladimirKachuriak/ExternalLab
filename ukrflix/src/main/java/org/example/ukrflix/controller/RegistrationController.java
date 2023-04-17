package org.example.ukrflix.controller;

import org.example.ukrflix.models.User;
import org.example.ukrflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(Model model, @ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult){
        User userDB = userService.findByLogin(user.getLogin());
        if(userDB!=null){
            model.addAttribute("loginMessage","label.warning.userAlreadyExist");
            return "registration";
        }
        if(bindingResult.hasErrors()){
            return "registration";
        }

        userService.addUser(user);

        return "redirect:/login";
    }

}
