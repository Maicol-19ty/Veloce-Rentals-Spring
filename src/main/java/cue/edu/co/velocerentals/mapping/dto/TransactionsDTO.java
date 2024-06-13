package cue.edu.co.velocerentals.mapping.dto;

import cue.edu.co.velocerentals.domain.enums.PaymentMethods;
import cue.edu.co.velocerentals.domain.models.Reservation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public record TransactionsDTO(@NotNull(message = "Reservation cannot be null")
                              Reservation reservation,

                              @NotNull(message = "Amount cannot be null")
                              @Positive(message = "Amount must be positive")
                              BigDecimal amount,

                              @NotNull(message = "Transaction date cannot be null")
                              Instant transactionDate,

                              @NotNull(message = "Payment method cannot be null")
                              PaymentMethods paymentMethods) {
}
