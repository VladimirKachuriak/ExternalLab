package org.example.ukrflix.repo;

import org.example.ukrflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
}