package com.thedisciplineprogram.services.auth;

import com.thedisciplineprogram.configurations.auth.TokenProvider;
import com.thedisciplineprogram.exceptions.user.UserAlreadyExistsException;
import com.thedisciplineprogram.models.dtos.JwtDTO;
import com.thedisciplineprogram.models.dtos.SignInDTO;
import com.thedisciplineprogram.models.dtos.SignUpDTO;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.models.entities.UserRole;
import com.thedisciplineprogram.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    public UserDetails signUp(SignUpDTO data) throws UserAlreadyExistsException {
        if (userRepository.findByLogin(data.getLogin()) != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getLogin(), encryptedPassword, new UserRole(1L, "USER"));

        return userRepository.save(newUser);
    }

    @Override
    public JwtDTO signIn(SignInDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var user = (User) auth.getPrincipal();
        var accessToken = tokenProvider.generateAccessToken(user);
        return new JwtDTO(user.getId().toString(), accessToken);
    }
}
