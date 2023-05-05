package org.example.ukrflix.controller;

import org.apache.log4j.Logger;
import org.example.ukrflix.models.Actor;
import org.example.ukrflix.models.Film;
import org.example.ukrflix.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/actor")
public class ActorController {
    private static final Logger LOGGER = Logger.getLogger(ActorController.class);
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @GetMapping("")
    public String getAll(Model model){
        List<Actor> actors = actorService.getAll();
        model.addAttribute("actors", actors);
        LOGGER.info("get all actors");
        return "actors";
    }

    @GetMapping("/newActor")
    public String newActor(@ModelAttribute Actor actor){
        LOGGER.info("entered /newActor");
        return "newActor";
    }
    @PostMapping("/newActor")
    public String addActor(@ModelAttribute @Valid Actor actor, BindingResult bindingResult,
                           Model model,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "newActor";
        }
        redirectAttributes.addFlashAttribute("message","label.message.addActorSuccess");
        actorService.addActor(actor);
        return "redirect:/catalog";
    }

    @GetMapping("/{id}")
    public String getActor(@PathVariable int id, Model model) {
        Actor actor = actorService.findById(id);
        model.addAttribute("actor", actor);
        LOGGER.info("get all actor by id");
        return "actor";
    }
}
