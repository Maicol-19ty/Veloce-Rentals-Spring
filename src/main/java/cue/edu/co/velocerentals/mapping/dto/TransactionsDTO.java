package cue.edu.co.velocerentals.mapping.dto;

import cue.edu.co.velocerentals.domain.enums.PaymentMethods;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record TransactionsDTO(
        @NotNull(message = "Reservation ID cannot be null")
        Integer reservationId,

        @NotNull(message = "Payment method cannot be null")
        PaymentMethods paymentMethods) {
}
