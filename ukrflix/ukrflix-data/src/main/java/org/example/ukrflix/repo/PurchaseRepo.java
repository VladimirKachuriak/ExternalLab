package org.example.ukrflix.repo;

import org.example.ukrflix.model.Film;
import org.example.ukrflix.model.Purchase;
import org.example.ukrflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
    Purchase findByFilmAndUser(Film film, User user);
}