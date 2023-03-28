package org.example.ukrflix.controller;

import org.example.ukrflix.models.Film;
import org.example.ukrflix.models.Purchase;
import org.example.ukrflix.models.User;
import org.example.ukrflix.service.FilmService;
import org.example.ukrflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final FilmService filmService;

    @Autowired
    public UserController(UserService userService, FilmService filmService) {
        this.userService = userService;
        this.filmService = filmService;
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        List<Film> filmList = filmService.getAll();
        model.addAttribute("films", filmList);
        return "catalog";
    }
    @GetMapping("/")
    public String catalog() {
        return "redirect:/catalog";
    }

    @GetMapping("/user/topUp")
    public String topUpPage(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");
        User user = userService.findByLogin(login);
        model.addAttribute("user", user);
        return "topUp";
    }

    /*@GetMapping("/user/purchases")
    public String purchases(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");
        User user = userService.findByLogin(login);
        Set<Purchase> filmList = user.getPurchases();
        model.addAttribute("films", filmList);
        System.out.println(filmList);
        return "topUp";
    }*/

    @PostMapping("/user/topUp")
    public String topUp(@RequestParam("money") int cash, HttpSession session, RedirectAttributes redirectAttributes) {
        String login = (String) session.getAttribute("login");
        User user = userService.findByLogin(login);
        boolean result = userService.topUpAccount(user.getId(), cash);
        if (result) {
            redirectAttributes.addFlashAttribute("message", "account was replenished successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "we have some problem");
        }
        return "redirect:/catalog";
    }


}
