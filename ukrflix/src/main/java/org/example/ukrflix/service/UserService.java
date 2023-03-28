package org.example.ukrflix.service;

import org.example.ukrflix.models.User;
import org.example.ukrflix.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public boolean addUser(User user){
        User userFromDb = userRepo.findUserByLogin(user.getLogin());
        if (userFromDb != null) {
            return false;
        }
        userRepo.save(user);
        return true;
    }
    public User findByLogin(String login){
        return userRepo.findUserByLogin(login);
    }
    public boolean topUpAccount(int id,int cash){
        User userFromDb = userRepo.findById(id).orElse(null);
        if (userFromDb == null || cash<=0) {
            return false;
        }
        userFromDb.setAccount(userFromDb.getAccount()+cash);
        userRepo.save(userFromDb);
        return true;
    }
    public boolean updateUser(User user){
        User userFromDb = userRepo.findUserByLogin(user.getLogin());
        if (userFromDb == null) {
            return false;
        }
        user.setId(user.getId());
        userRepo.save(user);
        return true;
    }
}
