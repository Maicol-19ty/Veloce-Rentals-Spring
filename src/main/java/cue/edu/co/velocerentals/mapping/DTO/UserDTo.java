package cue.edu.co.velocerentals.mapping.DTO;

import lombok.Builder;

import java.time.Instant;

@Builder
public record UserDTo(Integer id, String username, String password, String email,
                      String fullName, Instant createdAt, Instant lastLogin) {
}
