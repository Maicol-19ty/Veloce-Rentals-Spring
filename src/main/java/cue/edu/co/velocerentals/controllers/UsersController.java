package cue.edu.co.velocerentals.controllers;

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
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public Map<String, String> createUser(@RequestBody @Valid UserDTO usersDTo, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return errors;
        }
        userService.createUser(usersDTo);
        return Map.of("message", "User created successfully");
    }

    @GetMapping("search/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        UserDTO user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO usersDTo) {
        UserDTO updatedUser = userService.updateUser(id, usersDTo);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
