package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.dto.UserDTO;
import cue.edu.co.velocerentals.domain.models.User;

// Mapper class for converting between Users and UsersDTo objects.
public class UserMapper {

    // Method to map from Users model to UsersDTo DTO.
    public static UserDTO mapFromModel(User user) {
        return new UserDTO(user.getUsername(), user.getPassword(), user.getEmail(),
                user.getFullName(), user.getCreatedAt(), user.getLastLogin());
    }

    // Method to map from UsersDTo DTO to Users model.
    public static User mapFromDTO(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.username())
                .password(userDTO.password())
                .email(userDTO.email())
                .fullName(userDTO.fullName())
                .createdAt(userDTO.createdAt())
                .lastLogin(userDTO.lastLogin())
                .build();
    }

}
