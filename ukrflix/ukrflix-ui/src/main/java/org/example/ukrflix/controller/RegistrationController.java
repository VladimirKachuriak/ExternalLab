package org.example.ukrflix.controller;

import org.apache.log4j.Logger;
import org.example.ukrflix.dto.UserDTO;
import org.example.ukrflix.model.User;
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

@Controller
public class RegistrationController {
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") UserDTO user) {
        LOGGER.info("Entered /registration");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Model model, @ModelAttribute("user") @Valid UserDTO userDTO,
                          BindingResult bindingResult) {
        User userDB = userService.findByLogin(userDTO.getLogin());
        if (userDB != null) {
            model.addAttribute("message", "label.warning.userAlreadyExist");
            return "registration";
        }
        if (bindingResult.hasErrors()) {
            System.out.println("ok");
            return "registration";
        }
        if (!userDTO.getPassword().equals(userDTO.getPasswordRepeat())) {
            bindingResult.rejectValue("passwordRepeat", "label.warning.passwordRepeat");
            System.out.println("blaf");
            return "registration";
        }
        User user = userDTO.toUser();
        userService.addUser(user);
        LOGGER.info("user was added successfully");
        LOGGER.info("redirect /login");
        return "redirect:/login";
    }

}
