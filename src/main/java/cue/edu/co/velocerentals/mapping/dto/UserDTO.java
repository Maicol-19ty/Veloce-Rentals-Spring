package cue.edu.co.velocerentals.mapping.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserDTO(@NotNull(message = "Username cannot be null")
                      @NotEmpty(message = "This user already exists")
                      String username,

                      @NotEmpty(message = "Password cannot be empty")
                      String password,

                      @Email(message = "Invalid email")
                      @NotEmpty(message = "This email already exists")
                      String email,

                      @NotEmpty(message = "Full name cannot be empty")
                      String fullName,

                      @NotNull(message = "Created at cannot be null")
                      Instant createdAt,

                      Instant lastLogin) {
}
