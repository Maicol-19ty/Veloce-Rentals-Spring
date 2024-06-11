package cue.edu.co.velocerentals.mapping.dto;

import cue.edu.co.velocerentals.domain.enums.PaymentMethods;
import cue.edu.co.velocerentals.domain.models.Reservation;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public record TransactionsDTO(Integer id, Reservation reservation, BigDecimal amount,
                              Instant transactionDate, PaymentMethods paymentMethods) {
}
