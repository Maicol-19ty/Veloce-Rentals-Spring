package cue.edu.co.velocerentals.controllers;

import cue.edu.co.velocerentals.mapping.DTO.UserDTo;
import cue.edu.co.velocerentals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<UserDTo> createUser(@RequestBody UserDTo usersDTo) {
        UserDTo createdUser = userService.createUser(usersDTo);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("search/{id}")
    public ResponseEntity<UserDTo> getUserById(@PathVariable Integer id) {
        UserDTo user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<UserDTo>> getAllUsers() {
        List<UserDTo> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UserDTo> updateUser(@PathVariable Integer id, @RequestBody UserDTo usersDTo) {
        UserDTo updatedUser = userService.updateUser(id, usersDTo);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
