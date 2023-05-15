package org.example.ukrflix.repo;

import org.example.ukrflix.model.ActorAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorAssociationRepo extends JpaRepository<ActorAssociation, Integer> {
    List<ActorAssociation> findActorAssociationByFilm_Id(int id);
    ActorAssociation findByFilm_IdAndActor_Id(int filmId, int ActorId);
}