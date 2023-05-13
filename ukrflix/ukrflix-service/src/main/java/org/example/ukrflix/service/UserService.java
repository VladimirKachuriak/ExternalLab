package org.example.ukrflix.service;

import org.apache.log4j.Logger;

import org.example.ukrflix.model.User;
import org.example.ukrflix.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
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
        LOGGER.info("added"+user);
        return true;
    }
    public User findByLogin(String login){
        LOGGER.info("find user by login = "+login);
        return userRepo.findUserByLogin(login);
    }
    public boolean topUpAccount(int id,int cash){
        User userFromDb = userRepo.findById(id).orElse(null);
        if (userFromDb == null || cash<=0) {
            LOGGER.info("user topUp account +"+cash);
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
        LOGGER.info("update user id:"+user.getId());
        return true;
    }
}
