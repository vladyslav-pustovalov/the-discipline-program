package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.ChangePasswordDTO;
import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import com.thedisciplineprogram.models.dtos.UserPlanDTO;
import com.thedisciplineprogram.models.dtos.user.UserDTO;
import com.thedisciplineprogram.models.dtos.user.UserRequestDTO;
import com.thedisciplineprogram.services.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: refactor to /users
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
    @SecurityRequirement(
            name = "bearerAuth"
    )
    public ResponseEntity<UserRequestDTO> getUserById(@PathVariable Long id) {
        UserRequestDTO result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<UserRequestDTO>> getUsers(@RequestParam(required = false) Long userPlanId) {
        if (userPlanId == null) {
            return ResponseEntity.ok(userService.getAllUsers());
        } else {
            return ResponseEntity.ok(userService.getAllUsersByUserPlanId(userPlanId));
        }
    }

    @PostMapping
    @SecurityRequirement(
            name = "bearerAuth"
    )
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO result = userService.createUser(userDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @SecurityRequirement(
            name = "bearerAuth"
    )
    public ResponseEntity<UserRequestDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("user update {}", userRequestDTO.getId());
        UserRequestDTO result = userService.updateUser(userRequestDTO.getId(), userRequestDTO);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<Void> changeUserPassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        userService.changeUserPassword(changePasswordDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/changeTrainingLevel")
    public ResponseEntity<Void> changeTrainingLevel(@PathVariable Long id, @RequestBody TrainingLevelDTO trainingLevelDTO) {
        userService.changeTrainingLevel(id, trainingLevelDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/changeUserPlan")
    public ResponseEntity<Void> changeUserPlan(@PathVariable Long id, @RequestBody UserPlanDTO userPlanDTO) {
        userService.changeUserPlan(id, userPlanDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(
            name = "bearerAuth"
    )
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
