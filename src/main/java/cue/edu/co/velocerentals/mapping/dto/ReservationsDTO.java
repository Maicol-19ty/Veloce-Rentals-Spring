package cue.edu.co.velocerentals.mapping.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReservationsDTO(@NotNull(message = "User ID cannot be null")
                              Integer userId,

                              @NotNull(message = "Vehicle ID cannot be null")
                              Integer vehicleId,

                              @NotNull(message = "Start date cannot be null")
                              @FutureOrPresent(message = "Start date must be in the present or future")
                              LocalDate startDate,

                              @NotNull(message = "End date cannot be null")
                              @Future(message = "End date must be in the future")
                              LocalDate endDate) {
}
