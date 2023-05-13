package org.example.ukrflix.controller;

import org.apache.log4j.Logger;
import org.example.ukrflix.model.Film;
import org.example.ukrflix.model.Purchase;
import org.example.ukrflix.model.User;
import org.example.ukrflix.service.FilmService;
import org.example.ukrflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.IntStream;


@Controller
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    private final UserService userService;
    private final FilmService filmService;

    @Autowired
    public UserController(UserService userService, FilmService filmService) {
        this.userService = userService;
        this.filmService = filmService;
    }
    @GetMapping("/catalog")
    public String catalog(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(value = "size", required = false, defaultValue = "2") int size) {
        LOGGER.info("Enter /catalog");
        Page<Film> filmList = filmService.getAll(page, size);
        if(page > filmList.getTotalPages())return "redirect:/catalog";
        model.addAttribute("numbers", IntStream.range(0, filmList.getTotalPages()).toArray());
        model.addAttribute("size", size);
        model.addAttribute("films", filmList);
        return "catalog";
    }
    @GetMapping("/")
    public String catalog() {
        LOGGER.info("Redirect from / to the /catalog path");
        return "redirect:/catalog";
    }

    @GetMapping("/user/topUp")
    public String topUpPage(Model model, HttpSession session) {
        LOGGER.info("Enter /user/topUP");
        String login = (String) session.getAttribute("login");
        User user = userService.findByLogin(login);
        if(user == null)return "redirect:/login";
        //System.out.println(user.getFilms());
        model.addAttribute("user", user);
        return "topUp";
    }

    @GetMapping("/user/purchases")
    public String purchases(Model model, HttpSession session) {
        LOGGER.info("Entered /user/purchases");
        String login = (String) session.getAttribute("login");
        User user = userService.findByLogin(login);
        if(user == null)return "redirect:/login";
        List<Purchase> filmList = user.getPurchases();
        model.addAttribute("purchases", filmList);
        //System.out.println(filmList);
        return "userPurchases";
    }

    @PostMapping("/user/topUp")
    public String topUp(@RequestParam("money") int cash, HttpSession session, RedirectAttributes redirectAttributes) {
        String login = (String) session.getAttribute("login");
        User user = userService.findByLogin(login);
        if(user == null)return "redirect:/login";
        boolean result = userService.topUpAccount(user.getId(), cash);
        if (result) {
            redirectAttributes.addFlashAttribute("message", "label.message.topUpAccount");
            LOGGER.info("account was replenished successfully, account+="+cash);
        } else {
            redirectAttributes.addFlashAttribute("message", "label.message.notEnoughMoney");
            LOGGER.info("this user doesn't exist");
        }
        return "redirect:/catalog";
    }
    @GetMapping("/user/profile")
    public String profile(Model model, HttpSession session) {
        LOGGER.info("Entered /user/profile");
        String login = (String) session.getAttribute("login");
        User user = userService.findByLogin(login);
        if(user == null)return "redirect:/login";
        model.addAttribute("user", user);
        return "profile";
    }

}
