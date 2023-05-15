package org.example.ukrflix.service;

import org.apache.log4j.Logger;
import org.example.ukrflix.model.Actor;
import org.example.ukrflix.model.ActorAssociation;
import org.example.ukrflix.model.Film;
import org.example.ukrflix.repo.ActorAssociationRepo;
import org.example.ukrflix.repo.ActorRepo;
import org.example.ukrflix.repo.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorAssociationService {
    private static final Logger LOGGER = Logger.getLogger(ActorAssociationService.class);
    private final ActorAssociationRepo actorAssociationRepo;
    private final FilmRepo filmRepo;
    private final ActorRepo actorRepo;

    @Autowired
    public ActorAssociationService(ActorAssociationRepo actorAssociationRepo, FilmRepo filmRepo, ActorRepo actorRepo) {
        this.actorAssociationRepo = actorAssociationRepo;
        this.filmRepo = filmRepo;
        this.actorRepo = actorRepo;
    }

    public List<ActorAssociation> getAllByFilmId(int filmId) {
        return actorAssociationRepo.findActorAssociationByFilm_Id(filmId);
    }

    public boolean addActorToFilm(int filmId, int actorId, String role) {
        Film film = filmRepo.findById(filmId).orElse(null);
        Actor actor = actorRepo.findById(actorId).orElse(null);
        if (film == null || actor == null) {
            return false;
        }
        if (film.getActorAssociations().stream().anyMatch(x -> x.getActor().getId() == actorId)) {
            return false;
        }
        ActorAssociation actorAssociation = new ActorAssociation();
        actorAssociation.setActor(actor);
        actorAssociation.setFilm(film);
        actorAssociation.setRole(role);
        actorAssociationRepo.save(actorAssociation);
        return true;
    }

    public boolean deleteActorFromFilm(int filmId, int actorId) {
        ActorAssociation actorAssociation = actorAssociationRepo.findByFilm_IdAndActor_Id(filmId, actorId);
        if (actorAssociation == null) {
            return false;
        }
        actorAssociationRepo.delete(actorAssociation);
        return true;
    }
}