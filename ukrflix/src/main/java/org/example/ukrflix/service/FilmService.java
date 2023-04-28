package org.example.ukrflix.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.example.ukrflix.controller.ActorController;
import org.example.ukrflix.models.Actor;
import org.example.ukrflix.models.Film;
import org.example.ukrflix.repo.ActorRepo;
import org.example.ukrflix.repo.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class FilmService {
    private static final Logger LOGGER = Logger.getLogger(FilmService.class);
    private final FilmRepo filmRepo;
    private final ActorRepo actorRepo;

    @Autowired
    public FilmService(FilmRepo filmRepo, ActorRepo actorRepo) {
        this.filmRepo = filmRepo;
        this.actorRepo = actorRepo;
    }


    public List<Film> getAll() {
        return filmRepo.findAll();
    }

    public Film findById(int id) throws JsonProcessingException {
        return filmRepo.findById(id).orElse(null);
    }

    public boolean addFilm(Film film, String descriptionEn, String descriptionUk) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = new HashMap<>();
        data.put("en",descriptionEn);
        data.put("uk",descriptionUk);
        String description = "";
        try {
             description = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        film.setDescription(description);
        filmRepo.save(film);
        return true;
    }

    public Page<Film> getAll(int page) {
        return filmRepo.findAll(PageRequest.of(page, 2));
    }

    public boolean deleteActorFromFilm(int filmId, int actorId) {
        Film film = filmRepo.findById(filmId).orElse(null);
        if (film == null) return false;
        if (film.getActors().stream().noneMatch(x -> x.getId() == actorId)) return false;
        film.getActors().removeIf(x -> x.getId() == actorId);
        filmRepo.save(film);
        return true;
    }

    public boolean addActorFromFilm(int filmId, int actorId) {
        Film film = filmRepo.findById(filmId).orElse(null);
        Actor actor = actorRepo.findById(actorId).orElse(null);
        if (film == null || actor == null) return false;
        if (film.getActors().stream().anyMatch(x -> x.getId() == actorId)) return false;
        film.getActors().add(actor);
        filmRepo.save(film);
        return true;
    }
}
