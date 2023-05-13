package org.example.ukrflix.service;


import org.apache.log4j.Logger;
import org.example.ukrflix.model.Actor;


import org.example.ukrflix.repo.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private static final Logger LOGGER = Logger.getLogger(ActorService.class);
    private final ActorRepo actorRepo;

    @Autowired
    public ActorService(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }
    public boolean addActor(Actor actor){
        actorRepo.save(actor);
        return true;
    }
    public List<Actor> getAll(){
        return actorRepo.findAll();
    }
    public Actor findById(int id){
        return actorRepo.findById(id).orElse(null);
    }
    public List<Actor> getActorsNotInFilm(int filmId, String search){
        return actorRepo.findActorsByFirstnameOrLastname(search, filmId);
    }
}
