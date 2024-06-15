package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.domain.models.Reservation;
import cue.edu.co.velocerentals.mapping.dto.TransactionsDTO;
import cue.edu.co.velocerentals.domain.models.Transaction;
import cue.edu.co.velocerentals.repository.ReservationsRepository;

import java.time.Instant;

public class TransactionsMapper {

    // Maps from TransactionsDTO to Transaction model
    public static Transaction mapFromDTO(TransactionsDTO transactionsDTO, ReservationsRepository reservationsRepository) {
        // Retrieves the reservation from the repository based on DTO data
        Reservation reservation = reservationsRepository.findById(transactionsDTO.reservationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation ID"));

        // Maps DTO data to Transaction model attributes
        return Transaction.builder()
                .reservation(reservation)
                .amount(reservation.getTotalCost())
                .transactionDate(Instant.now())
                .paymentMethod(transactionsDTO.paymentMethods())
                .build();
    }

    // Maps from Transaction model to TransactionsDTO
    public static TransactionsDTO mapFromModel(Transaction transaction) {
        // Maps Transaction model attributes to TransactionsDTO
        return TransactionsDTO.builder()
                .reservationId(transaction.getReservation().getId())
                .paymentMethods(transaction.getPaymentMethod())
                .build();
    }
}
