package cue.edu.co.velocerentals.mapping.DTO;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
public record UsersDTo(Integer id, String username, String password, String email,
                       String fullName, Instant createdAt, Instant lastLogin) {
}
