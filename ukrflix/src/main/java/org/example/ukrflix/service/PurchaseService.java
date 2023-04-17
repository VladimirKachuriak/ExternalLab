package org.example.ukrflix.service;

import org.example.ukrflix.models.Film;
import org.example.ukrflix.models.Purchase;
import org.example.ukrflix.models.User;
import org.example.ukrflix.repo.FilmRepo;
import org.example.ukrflix.repo.PurchaseRepo;
import org.example.ukrflix.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepo purchaseRepo;
    private final UserRepo userRepo;
    private final FilmRepo filmRepo;

    @Autowired
    public PurchaseService(PurchaseRepo purchaseRepo, UserRepo userRepo, FilmRepo filmRepo) {
        this.purchaseRepo = purchaseRepo;
        this.userRepo = userRepo;
        this.filmRepo = filmRepo;
    }

    public boolean makePurchase(String login, int filmId) {
        User user = userRepo.findUserByLogin(login);
        Film film = filmRepo.findById(filmId).orElse(null);
        if (purchaseRepo.findByFilmAndUser(film, user) != null) {
            return false;
        }
        if(film.getPrice()>user.getAccount()){
            return false;
        }
        Purchase purchase = new Purchase(user, film);
        user.setAccount(user.getAccount() - film.getPrice());
        purchaseRepo.save(purchase);
        userRepo.save(user);
        return true;
    }
}
