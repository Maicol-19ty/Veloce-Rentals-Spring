package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.DTO.UserDTo;
import cue.edu.co.velocerentals.models.User;

// Mapper class for converting between Users and UsersDTo objects.
public class UserMapper {

    // Method to map from Users model to UsersDTo DTO.
    public static UserDTo mapFromModel(User user) {
        return new UserDTo(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
                user.getFullName(), user.getCreatedAt(), user.getLastLogin());
    }

    // Method to map from UsersDTo DTO to Users model.
    public static User mapFromDTO(UserDTo userDTo) {
        return User.builder()
                .id(userDTo.id())
                .username(userDTo.username())
                .password(userDTo.password())
                .email(userDTo.email())
                .fullName(userDTo.fullName())
                .createdAt(userDTo.createdAt())
                .lastLogin(userDTo.lastLogin())
                .build();
    }

}
