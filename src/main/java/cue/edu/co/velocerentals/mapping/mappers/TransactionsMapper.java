package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.DTO.TransactionsDto;
import cue.edu.co.velocerentals.models.Transaction;

// Mapper class for converting between Transactions and TransactionsDto objects.
public class TransactionsMapper {

    // Method to map from Transactions model to TransactionsDto DTO.
    public static TransactionsDto mapFromModel(Transaction transactions) {
        return new TransactionsDto(transactions.getId(), transactions.getReservation(), transactions.getAmount(),
                transactions.getTransactionDate(), transactions.getPaymentMethod());
    }

    // Method to map from TransactionsDto DTO to Transactions model.
    public static Transaction mapFromDTO(TransactionsDto transactionsDto) {
        return Transaction.builder()
                .id(transactionsDto.id())
                .reservation(transactionsDto.reservation())
                .amount(transactionsDto.amount())
                .transactionDate(transactionsDto.transactionDate())
                .paymentMethod(transactionsDto.paymentMethods())
                .build();
    }

}
