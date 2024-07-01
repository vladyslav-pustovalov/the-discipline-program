package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.db_entities.User;
import com.thedisciplineprogram.models.dtos.UserDTO;
import com.thedisciplineprogram.models.mappers.UserMapper;
import com.thedisciplineprogram.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserMapper userMapper = new UserMapper();
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserById(@RequestParam(value = "id") Long id) {
        User resultEntity = userService.getUserById(id);
        if (resultEntity != null) {
            return ResponseEntity.ok(userMapper.mapUserToUserDTO(resultEntity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> createUser(@RequestBody UserDTO userDTO) {
        Boolean result = userService.createUser(userMapper.mapUserDTOToUser(userDTO));
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> updateUser(@RequestBody UserDTO userDTO) {
        Boolean result = userService.updateUser(userMapper.mapUserDTOToUser(userDTO));
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@RequestParam(value = "id") Long id) {
        Boolean result = userService.deleteUserById(id);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
