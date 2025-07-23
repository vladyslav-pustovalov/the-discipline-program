package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.ChangePasswordDTO;
import com.thedisciplineprogram.models.dtos.user.UserDTO;
import com.thedisciplineprogram.models.dtos.user.UserRequestDTO;
import com.thedisciplineprogram.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequestDTO> getUserById(@PathVariable Long id) {
        UserRequestDTO result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO result = userService.createUser(userDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<UserRequestDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("user update {}", userRequestDTO.getId());
        UserRequestDTO result = userService.updateUser(userRequestDTO.getId(), userRequestDTO);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/changePassword")
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
