package org.example.ukrflix.repo;

import org.example.ukrflix.models.ActorAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorAssociationRepo extends JpaRepository<ActorAssociation, Integer> {
}
