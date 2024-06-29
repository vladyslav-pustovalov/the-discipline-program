package com.thedisciplineprogram.services;

import com.thedisciplineprogram.models.db_entities.User;
import com.thedisciplineprogram.repositories.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserById(Long id) {
        return userDAO.findUserById(id);
    }

    public Boolean createUser(User user) {
        return userDAO.createUser(user);
    }

    public Boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public Boolean deleteUserById(Long id) {
        return userDAO.deleteUserById(id);
    }
}
