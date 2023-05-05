package org.example.ukrflix.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.example.ukrflix.models.Film;
import org.example.ukrflix.service.ActorService;
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
import javax.validation.Valid;

@Controller
@RequestMapping("/film")
public class FilmController {
    private static final Logger LOGGER = Logger.getLogger(FilmController.class);
    private final FilmService filmService;
    private final UserService userService;
    private final PurchaseService purchaseService;
    private final ActorService actorService;

    @Autowired
    public FilmController(FilmService filmService, UserService userService, PurchaseService purchaseService, ActorService actorService) {
        this.filmService = filmService;
        this.userService = userService;
        this.purchaseService = purchaseService;
        this.actorService = actorService;
    }

    @GetMapping("/newFilm")
    public String newFilm(@ModelAttribute("film") Film film) {
        LOGGER.info("Entered /new_film");
        return "newFilm";
    }

    @PostMapping("/newFilm")
    public String addFilm(Model model, @ModelAttribute("film") @Valid Film film, BindingResult bindingResult,
                          @RequestParam(name = "descriptionEn", defaultValue = "") String descriptionEn,
                          @RequestParam(name = "descriptionUk", defaultValue = "") String descriptionUk) {
        if (bindingResult.hasErrors()) {
            return "newFilm";
        }
        filmService.addFilm(film, descriptionEn, descriptionUk);
        LOGGER.info("Film " + film.getName() + " was added");
        return "redirect:/catalog";
    }

    @GetMapping("/{id}/watch")
    public String watchFilm(@PathVariable int id, Model model) throws JsonProcessingException {
        Film film = filmService.findById(id);
        model.addAttribute("film", film);
        return "watchFilm";
    }

    @GetMapping("/{id}")
    public String filmDescription(@PathVariable int id, @RequestParam(name = "search", required = false) String search, Model model) throws JsonProcessingException {
        Film film = filmService.findById(id);
        System.out.println(search);
        model.addAttribute("film", film);
        model.addAttribute("actors", actorService.getActorsNotInFilm(film.getId(), search));
        return "filmDescription";
    }

    @GetMapping("/{id}/buy")
    public String getEmployeesById(@PathVariable("id") int filmId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        String login = (String) session.getAttribute("login");
        boolean result = purchaseService.makePurchase(login, filmId);
        /*if(login==null){
            redirectAttributes.
        }*/
        if (result) {
            redirectAttributes.addFlashAttribute("message", "label.message.buyMovieSuccess");
            LOGGER.info("Film " + filmId + " was added by the user login = " + login);
        } else {
            redirectAttributes.addFlashAttribute("message", "label.message.buyMovieFail");
            LOGGER.info("User login = " + login + " have not enough money");
        }
        return "redirect:/catalog";
    }

    @PostMapping("/{id}/deleteActor")
    public String deleteActorFromFilm(@PathVariable("id") int filmId, @RequestParam(name = "actorId") int actorId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        boolean result = filmService.deleteActorFromFilm(filmId, actorId);
        if (result) {
            redirectAttributes.addFlashAttribute("message", "label.message.deleteActorSuccess");
            LOGGER.info("deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "label.message.fail");
            LOGGER.info("problem");
        }
        return "redirect:/film/" + filmId;
    }

    @PostMapping("/{id}/addActor")
    public String addActorToFilm(@PathVariable("id") int filmId, @RequestParam(name = "actorId") int actorId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        boolean result = filmService.addActorFromFilm(filmId, actorId);
        if (result) {
            redirectAttributes.addFlashAttribute("message", "label.message.addActorToFilm");
            LOGGER.info("actor id = " + actorId + " was added successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "label.message.fail");
            LOGGER.info("problem");
        }
        /*UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/film/"+filmId)
                .queryParam("search", se);
        String url = builder.toUriString();*/
        return "redirect:/film/" + filmId;
    }
}
