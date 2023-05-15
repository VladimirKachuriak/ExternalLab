package org.example.ukrflix.repo;

import org.example.ukrflix.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepo extends JpaRepository<Film, Integer> {
    Page<Film> findAll(Pageable pageable);
}