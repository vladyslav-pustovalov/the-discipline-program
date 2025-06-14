package com.thedisciplineprogram.services.auth;

import com.thedisciplineprogram.exceptions.user.UserAlreadyExistsException;
import com.thedisciplineprogram.models.dtos.SignUpDTO;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.models.entities.UserRole;
import com.thedisciplineprogram.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user =  userRepository.findByLogin(username);
        return user;
    }

    public UserDetails signUp(SignUpDTO data) throws UserAlreadyExistsException {
        if (userRepository.findByLogin(data.getLogin()) != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getLogin(), encryptedPassword, new UserRole(1L, "USER"));
        log.info("User entity created: " + newUser.toString());

        return userRepository.save(newUser);
    }
}
