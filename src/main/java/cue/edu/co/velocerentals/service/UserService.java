package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.mapping.dto.UserDTO;
import cue.edu.co.velocerentals.mapping.mappers.UserMapper;
import cue.edu.co.velocerentals.domain.models.User;
import cue.edu.co.velocerentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO usersDTo) {
        User user = UserMapper.mapFromDTO(usersDTo);
        user = userRepository.save(user);
        return UserMapper.mapFromModel(user);
    }

    public UserDTO getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::mapFromModel).orElse(null);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::mapFromModel)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Integer id, UserDTO usersDTo) {
        Optional<User> userOptional = userRepository.findById((id));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(usersDTo.username());
            user.setPassword(usersDTo.password());
            user.setEmail(usersDTo.email());
            user.setFullName(usersDTo.fullName());
            user.setCreatedAt(usersDTo.createdAt());
            user.setLastLogin(usersDTo.lastLogin());
            user = userRepository.save(user);
            return UserMapper.mapFromModel(user);
        } else {
            return null;
        }
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
