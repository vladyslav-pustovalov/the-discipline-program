package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.ChangePasswordDTO;
import com.thedisciplineprogram.models.dtos.UserDTO;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.services.user.UserService;
import com.thedisciplineprogram.utils.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    private final UserMapper mapper = UserMapper.INSTANCE;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        log.info(user.toString());
        UserDTO result = mapper.userToUserDTO(user);
        log.info(result.toString());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User result = userService.createUser(mapper.userDTOToUser(userDTO));
        return ResponseEntity.ok(mapper.userToUserDTO(result));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        log.info("User received for update: " + userDTO.toString());
        User result = userService.updateUser(userDTO.getId(), mapper.userDTOToUser(userDTO));
        log.info("User updated: " + result.toString());
        return ResponseEntity.ok(mapper.userToUserDTO(result));
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
