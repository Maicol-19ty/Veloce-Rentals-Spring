package cue.edu.co.velocerentals.controllers;

import cue.edu.co.velocerentals.mapping.dto.LoginDTO;
import cue.edu.co.velocerentals.mapping.dto.UserDTO;
import cue.edu.co.velocerentals.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController { // Public class handling REST endpoints for user-related operations

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public Map<String, String> createUser(@RequestBody @Valid UserDTO usersDTO, BindingResult result) {
        // Creates a new user, returns validation errors if any, or success message
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return errors;
        }
        userService.createUser(usersDTO);
        return Map.of("message", "User created successfully");
    }

    @GetMapping("search/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        // Retrieves a user by their ID
        UserDTO user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        // Retrieves all users
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO usersDTO) {
        // Updates an existing user, returns the updated user or not found status
        UserDTO updatedUser = userService.updateUser(id, usersDTO);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        // Deletes a user by their ID
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody @Valid LoginDTO loginDTO) {
        // Authenticates a user with username and password
        UserDTO userDTO = userService.login(loginDTO.username(), loginDTO.password());
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
