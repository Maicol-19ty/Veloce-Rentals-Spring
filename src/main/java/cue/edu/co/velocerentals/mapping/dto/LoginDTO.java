package cue.edu.co.velocerentals.mapping.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginDTO(@NotEmpty(message = "Username cannot be empty")
                       String username,

                       @NotEmpty(message = "Password cannot be empty")
                       String password ) {}
