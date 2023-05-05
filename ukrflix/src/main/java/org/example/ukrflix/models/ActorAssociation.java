package org.example.ukrflix.models;

import javax.persistence.*;

@Entity
public class ActorAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "actor_id")
    private Actor actor;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "film_id")
    private Film film;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
