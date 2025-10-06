package com.thedisciplineprogram.services.auth;

import com.thedisciplineprogram.configurations.auth.TokenProvider;
import com.thedisciplineprogram.exceptions.auth.SignUpException;
import com.thedisciplineprogram.exceptions.user.UserAlreadyExistsException;
import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import com.thedisciplineprogram.models.dtos.UserPlanDTO;
import com.thedisciplineprogram.models.dtos.UserRoleDTO;
import com.thedisciplineprogram.models.dtos.auth.JwtDTO;
import com.thedisciplineprogram.models.dtos.auth.SignInDTO;
import com.thedisciplineprogram.models.dtos.auth.SignUpDTO;
import com.thedisciplineprogram.models.entities.TrainingLevel;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.models.entities.UserPlan;
import com.thedisciplineprogram.models.entities.UserRole;
import com.thedisciplineprogram.repositories.UserRepository;
import com.thedisciplineprogram.services.traininglevel.TrainingLevelService;
import com.thedisciplineprogram.services.userPlan.UserPlanService;
import com.thedisciplineprogram.utils.mappers.TrainingLevelMapper;
import com.thedisciplineprogram.utils.mappers.UserPlanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final UserRepository userRepository;
    private final TrainingLevelService trainingLevelService;
    private final UserPlanService userPlanService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TrainingLevelMapper trainingLevelMapper;
    private final UserPlanMapper userPlanMapper;

    @Autowired
    public AuthServiceImpl(
            UserRepository userRepository,
            TrainingLevelService trainingLevelService,
            UserPlanService userPlanService,
            TokenProvider tokenProvider,
            @Lazy AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            TrainingLevelMapper trainingLevelMapper,
            UserPlanMapper userPlanMapper
    ) {
        this.userRepository = userRepository;
        this.trainingLevelService = trainingLevelService;
        this.userPlanService = userPlanService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.trainingLevelMapper = trainingLevelMapper;
        this.userPlanMapper = userPlanMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void signUp(SignUpDTO data) throws UserAlreadyExistsException {
        /// Normalizing email
        data.setUsername(data.getUsername().toLowerCase());

        if (userRepository.findByUsername(data.getUsername()) != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        String encryptedPassword = passwordEncoder.encode(data.getPassword());

        User newUser = new User(data.getUsername(), encryptedPassword);

        if (data.getTrainingLevel() != null) {
            TrainingLevel userTrainingLevel = trainingLevelMapper.toEntity(data.getTrainingLevel());
            TrainingLevelDTO existingTrainingLevelDTO = trainingLevelService.getTrainingLevelById(data.getTrainingLevel().getId());
            TrainingLevel existingTrainingLevel = trainingLevelMapper.toEntity(existingTrainingLevelDTO);

            if (userTrainingLevel.equals(existingTrainingLevel)) {
                newUser.setTrainingLevel(userTrainingLevel);
                log.info("New User will be created with TrainingLevel: {}", userTrainingLevel);
            }
        }

        if (data.getUserPlan() != null) {
            UserPlan userPlan = userPlanMapper.toEntity(data.getUserPlan());
            UserPlanDTO existingUserPlanDTO = userPlanService.getUserPlanById(data.getUserPlan().getId());
            UserPlan existingUserPlan = userPlanMapper.toEntity(existingUserPlanDTO);

            if (userPlan.equals(existingUserPlan)) {
                newUser.setUserPlan(userPlan);
                log.info("New User will be created with UserPlan: {}", userPlan);
            }
        }

        newUser.setUserRole(new UserRole(1L, "USER"));

        try {
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new SignUpException("Can't sign up user: ", e);
        }
    }

    @Override
    public JwtDTO signIn(SignInDTO data) {
        /// Normalizing email
        data.setUsername(data.getUsername().toLowerCase());

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var user = (User) auth.getPrincipal();

        if (!user.isEnabled()) {
            throw new DisabledException("User account is disabled");
        }

        var accessToken = tokenProvider.generateAccessToken(user);
        var role = new UserRoleDTO(user.getUserRole().getId(), user.getUserRole().getName());
        var plan = new UserPlanDTO(user.getUserPlan().getId(), user.getUserPlan().getName());
        return new JwtDTO(
                user.getId(),
                accessToken,
                role,
                plan
        );
    }
}
