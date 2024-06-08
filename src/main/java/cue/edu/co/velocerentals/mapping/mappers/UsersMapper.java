package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.DTO.UsersDTo;
import cue.edu.co.velocerentals.models.User;

// Mapper class for converting between Users and UsersDTo objects.
public class UsersMapper {

    // Method to map from Users model to UsersDTo DTO.
    public static UsersDTo mapFromModel(User users) {
        return new UsersDTo(users.getId(), users.getUsername(), users.getPassword(), users.getEmail(),
                users.getFullName(), users.getCreatedAt(), users.getLastLogin());
    }

    // Method to map from UsersDTo DTO to Users model.
    public static User mapFromDTO(UsersDTo usersDTo) {
        return User.builder()
                .id(usersDTo.id())
                .username(usersDTo.username())
                .password(usersDTo.password())
                .email(usersDTo.email())
                .fullName(usersDTo.fullName())
                .createdAt(usersDTo.createdAt())
                .lastLogin(usersDTo.lastLogin())
                .build();
    }

}
