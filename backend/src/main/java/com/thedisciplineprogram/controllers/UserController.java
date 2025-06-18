package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.UserDTO;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.services.user.UserService;
import com.thedisciplineprogram.utils.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserMapper mapper = UserMapper.INSTANCE;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO result = mapper.userToUserDTO(userService.getUserById(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User result = userService.createUser(mapper.userDTOToUser(userDTO));
        return ResponseEntity.ok(mapper.userToUserDTO(result));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        User result = userService.updateUser(userDTO.getId(), mapper.userDTOToUser(userDTO));
        return ResponseEntity.ok(mapper.userToUserDTO(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
