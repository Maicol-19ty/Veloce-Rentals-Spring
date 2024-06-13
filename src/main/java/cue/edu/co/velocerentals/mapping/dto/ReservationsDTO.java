package cue.edu.co.velocerentals.mapping.dto;

import cue.edu.co.velocerentals.domain.models.User;
import cue.edu.co.velocerentals.domain.models.Vehicle;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ReservationsDTO(@NotNull(message = "User cannot be null")
                              User user,

                              @NotNull(message = "Vehicle cannot be null")
                              Vehicle vehicle,

                              @NotNull(message = "Start date cannot be null")
                              @FutureOrPresent(message = "Start date must be in the present or future")
                              LocalDate startDate,

                              @NotNull(message = "End date cannot be null")
                              @Future(message = "End date must be in the future")
                              LocalDate endDate,

                              @NotNull(message = "Total cost cannot be null")
                              @Positive(message = "Total cost must be positive")
                              BigDecimal totalCost ) {
}
