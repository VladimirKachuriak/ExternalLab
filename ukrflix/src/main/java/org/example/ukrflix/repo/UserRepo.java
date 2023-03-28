package org.example.ukrflix.repo;

import org.example.ukrflix.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
    //void deleteByLogin(String login);
}
