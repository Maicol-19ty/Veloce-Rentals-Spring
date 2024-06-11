package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.dto.TransactionsDTO;
import cue.edu.co.velocerentals.domain.models.Transaction;

// Mapper class for converting between Transactions and TransactionsDto objects.
public class TransactionsMapper {

    // Method to map from Transactions model to TransactionsDto DTO.
    public static TransactionsDTO mapFromModel(Transaction transactions) {
        return new TransactionsDTO(transactions.getId(), transactions.getReservation(), transactions.getAmount(),
                transactions.getTransactionDate(), transactions.getPaymentMethod());
    }

    // Method to map from TransactionsDto DTO to Transactions model.
    public static Transaction mapFromDTO(TransactionsDTO transactionsDto) {
        return Transaction.builder()
                .id(transactionsDto.id())
                .reservation(transactionsDto.reservation())
                .amount(transactionsDto.amount())
                .transactionDate(transactionsDto.transactionDate())
                .paymentMethod(transactionsDto.paymentMethods())
                .build();
    }

}
