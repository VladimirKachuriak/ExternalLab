package org.example.ukrflix.service;

import org.example.ukrflix.models.Actor;
import org.example.ukrflix.models.User;
import org.example.ukrflix.repo.ActorRepo;
import org.example.ukrflix.repo.FilmRepo;
import org.example.ukrflix.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void addUser() {
        User user = new User();
        user.setId(2);
        user.setLogin("user");
        user.setPassword("password");
        when(userRepo.save(user)).thenReturn(user);
        assertEquals(true, userService.addUser(user));
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void findByLogin() {
        User user = new User();
        user.setId(2);
        user.setLogin("user");
        user.setPassword("password");
        when(userRepo.findUserByLogin("user")).thenReturn(user);
        assertEquals(user, userService.findByLogin("user"));
        verify(userRepo, times(1)).findUserByLogin("user");
    }

    @Test
    void topUpAccount() {
        User user = new User();
        user.setId(2);
        user.setLogin("user");
        user.setPassword("password");
        when(userRepo.findById(2)).thenReturn(Optional.of(user));
        assertEquals(true, userService.topUpAccount(2,22));
        verify(userRepo, times(1)).findById(2);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(2);
        user.setLogin("user");
        user.setPassword("password");
        when(userRepo.findUserByLogin("user")).thenReturn(user);
        assertEquals(true, userService.updateUser(user));
        verify(userRepo, times(1)).save(user);

    }
}