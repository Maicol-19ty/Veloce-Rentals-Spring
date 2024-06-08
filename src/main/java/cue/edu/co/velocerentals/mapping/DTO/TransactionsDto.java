package cue.edu.co.velocerentals.mapping.DTO;

import cue.edu.co.velocerentals.enums.PaymentMethods;
import cue.edu.co.velocerentals.models.Reservation;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionsDto(Integer id, Reservation reservation, BigDecimal amount,
                              Instant transactionDate, String paymentMethods) {
}
