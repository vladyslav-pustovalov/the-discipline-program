package com.thedisciplineprogram.repositories.user;

import com.thedisciplineprogram.models.db_entities.User;

public interface UserDAO {
    User findUserById(Long id);
    User findUserByEmail(String email);
    Boolean createUser(User user);
    Boolean updateUser(User user);
    Boolean deleteUserById(Long id);
}
