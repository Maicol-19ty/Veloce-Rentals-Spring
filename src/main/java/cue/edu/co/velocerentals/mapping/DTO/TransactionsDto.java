package cue.edu.co.velocerentals.mapping.DTO;

import cue.edu.co.velocerentals.enums.PaymentMethods;
import cue.edu.co.velocerentals.models.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public record TransactionsDto(Integer id, Reservation reservation, BigDecimal amount,
                              Instant transactionDate, PaymentMethods paymentMethods) {
}
