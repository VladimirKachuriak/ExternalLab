package org.example.ukrflix.repo;


import org.example.ukrflix.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Integer> {
    @Query("SELECT a FROM Actor a WHERE (CONCAT(a.firstname,' ',a.lastname) LIKE %:search%) AND a.id NOT IN (SELECT af.actor.id FROM ActorAssociation af WHERE af.film.id = :filmId)")
    List<Actor> findActorsByFirstnameOrLastname(@Param("search") String search, @Param("filmId") int filmId);
}
