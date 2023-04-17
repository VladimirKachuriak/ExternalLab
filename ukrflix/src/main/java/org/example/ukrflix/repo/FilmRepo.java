package org.example.ukrflix.repo;

import org.example.ukrflix.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepo extends JpaRepository<Film, Integer> {
}
