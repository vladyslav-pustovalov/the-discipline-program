package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.ChangePasswordDTO;
import com.thedisciplineprogram.models.dtos.user.UserDTO;
import com.thedisciplineprogram.models.dtos.user.UserRequestDTO;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.services.user.UserService;
import com.thedisciplineprogram.utils.mappers.UserMapper;
import com.thedisciplineprogram.utils.mappers.UserRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserRequestMapper userRequestMapper = UserRequestMapper.INSTANCE;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserRequestDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        log.info(user.toString());
        UserRequestDTO result = userRequestMapper.toDTO(user);
        log.info(result.toString());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User newUser = userMapper.toEntity(userDTO);
        log.info("New user entity: " + newUser);
        User saved = userService.createUser(newUser);
        log.info("Saved new user: " + saved);
        UserDTO result = userMapper.toDTO(saved);
        log.info("DTO new user: " + result);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<UserRequestDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("User received for update: " + userRequestDTO.toString());
        User result = userService.updateUser(userRequestDTO.getId(), userRequestMapper.toEntity(userRequestDTO));
        log.info("User updated: " + result.toString());
        return ResponseEntity.ok(userRequestMapper.toDTO(result));
    }

    @PatchMapping("/{id}/changePassword")
    public ResponseEntity<Void> changeUserPassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        userService.changeUserPassword(changePasswordDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
