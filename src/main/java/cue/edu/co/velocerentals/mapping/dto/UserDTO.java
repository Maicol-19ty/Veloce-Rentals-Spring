package cue.edu.co.velocerentals.mapping.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserDTO(@NotNull @NotEmpty(message = "This user already exists") String username, String password,
                      @Email(message = "Invalid email") @NotEmpty(message = "This email already exists") String email,
                      String fullName, Instant createdAt, Instant lastLogin) {
}
