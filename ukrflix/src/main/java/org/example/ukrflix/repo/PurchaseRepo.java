package org.example.ukrflix.repo;

import org.example.ukrflix.models.Film;
import org.example.ukrflix.models.Purchase;
import org.example.ukrflix.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
    Purchase findByFilmAndUser(Film film, User user);
}
