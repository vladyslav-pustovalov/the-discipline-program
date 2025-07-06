package com.thedisciplineprogram.services.auth;

import com.thedisciplineprogram.configurations.auth.TokenProvider;
import com.thedisciplineprogram.exceptions.auth.SignUpException;
import com.thedisciplineprogram.exceptions.user.UserAlreadyExistsException;
import com.thedisciplineprogram.models.dtos.UserRoleDTO;
import com.thedisciplineprogram.models.dtos.auth.JwtDTO;
import com.thedisciplineprogram.models.dtos.auth.SignInDTO;
import com.thedisciplineprogram.models.dtos.auth.SignUpDTO;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, TokenProvider tokenProvider, @Lazy AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void signUp(SignUpDTO data) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(data.getUsername()) != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        User newUser = new User(data.getUsername(), encryptedPassword);

        try {
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new SignUpException("Can't sign up user: ", e);
        }
    }

    @Override
    public JwtDTO signIn(SignInDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var user = (User) auth.getPrincipal();
        var accessToken = tokenProvider.generateAccessToken(user);
        var role = new UserRoleDTO(user.getUserRole().getId(), user.getUserRole().getName());
        return new JwtDTO(
                user.getId(),
                accessToken,
                role
        );
    }
}
