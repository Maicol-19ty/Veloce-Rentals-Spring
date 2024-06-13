package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.domain.models.Reservation;
import cue.edu.co.velocerentals.mapping.dto.TransactionsDTO;
import cue.edu.co.velocerentals.domain.models.Transaction;
import cue.edu.co.velocerentals.repository.ReservationsRepository;


import java.time.Instant;

public class TransactionsMapper {

    public static Transaction mapFromDTO(TransactionsDTO transactionsDTO, ReservationsRepository reservationsRepository) {
        Reservation reservation = reservationsRepository.findById(transactionsDTO.reservationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation ID"));

        return Transaction.builder()
                .reservation(reservation)
                .amount(reservation.getTotalCost())
                .transactionDate(Instant.now())
                .paymentMethod(transactionsDTO.paymentMethods())
                .build();
    }

    public static TransactionsDTO mapFromModel(Transaction transaction) {
        return TransactionsDTO.builder()
                .reservationId(transaction.getReservation().getId())
                .paymentMethods(transaction.getPaymentMethod())
                .build();
    }
}