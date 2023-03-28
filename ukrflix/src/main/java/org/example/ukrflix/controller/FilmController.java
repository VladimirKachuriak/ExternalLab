package org.example.ukrflix.controller;

import org.example.ukrflix.models.Film;
import org.example.ukrflix.service.FilmService;
import org.example.ukrflix.service.PurchaseService;
import org.example.ukrflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;
    private final UserService userService;
    private final PurchaseService purchaseService;

    @Autowired
    public FilmController(FilmService filmService, UserService userService, PurchaseService purchaseService) {
        this.filmService = filmService;
        this.userService = userService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/new_film")
    public String new_film(@ModelAttribute("film") Film film) {
        return "newFilm";
    }

    @PostMapping("/new_film")
    public String addFilm(Model model, @ModelAttribute Film film, BindingResult bindingResult) {
        filmService.addFilm(film);
        return "redirect:/catalog";
    }
    @GetMapping("/{id}")
    public String watchFilm(@PathVariable int id, Model model) {
        Film film = filmService.findById(id);
        model.addAttribute("film", film);
        return "watchFilm";
    }
    @GetMapping("/{id}/buy")
    public String getEmployeesById(@PathVariable("id") int film_id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        String login = (String) session.getAttribute("login");
        boolean result = purchaseService.makePurchase(login, film_id);
        if (result) {
            redirectAttributes.addFlashAttribute("message", "the movie was bought");
        } else {
            redirectAttributes.addFlashAttribute("message", "we have some problem");
        }
        return "redirect:/catalog";
    }
}
