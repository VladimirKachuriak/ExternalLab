package org.example.ukrflix.service;

import org.example.ukrflix.models.Film;
import org.example.ukrflix.repo.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepo filmRepo;

    @Autowired
    public FilmService(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    public List<Film> getAll(){
        return filmRepo.findAll();
    }
    public Film findById(int id){
        return filmRepo.findById(id).orElse(null);
    }
    public boolean addFilm(Film film){
        filmRepo.save(film);
        return true;
    }


}
