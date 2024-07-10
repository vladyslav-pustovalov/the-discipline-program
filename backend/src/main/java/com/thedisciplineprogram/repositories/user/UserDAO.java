package com.thedisciplineprogram.repositories.user;

import com.thedisciplineprogram.models.db_entities.User;

public interface UserDAO {
    User getUserById(Long id);
    User getUserByEmail(String email);
    Boolean createUser(User user);
    Boolean updateUser(User user);
    Boolean deleteUserById(Long id);
}
