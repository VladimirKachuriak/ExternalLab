package org.example.ukrflix.service;

import org.example.ukrflix.models.Film;
import org.example.ukrflix.models.User;
import org.example.ukrflix.repo.FilmRepo;
import org.example.ukrflix.repo.PurchaseRepo;
import org.example.ukrflix.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseServiceTest {
    @Mock
    private PurchaseRepo purchaseRepo;
    @Mock
    private UserRepo userRepo;
    @Mock
    private FilmRepo filmRepo;
    @InjectMocks
    private PurchaseService purchaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makePurchase() {
        Film film = new Film();
        film.setPrice(22);
        when(filmRepo.findById(2)).thenReturn(Optional.of(film));
        User user = new User();
        user.setAccount(22);
        when(userRepo.findUserByLogin("user")).thenReturn(user);
        assertEquals(true, purchaseService.makePurchase("user", 2));
        verify(userRepo, times(1)).findUserByLogin("user");
        verify(filmRepo, times(1)).findById(2);
    }
}