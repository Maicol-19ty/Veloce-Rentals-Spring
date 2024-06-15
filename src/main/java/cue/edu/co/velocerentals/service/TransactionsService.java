package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.domain.models.Transaction;
import cue.edu.co.velocerentals.mapping.dto.TransactionsDTO;
import cue.edu.co.velocerentals.mapping.mappers.TransactionsMapper;
import cue.edu.co.velocerentals.repository.ReservationsRepository;
import cue.edu.co.velocerentals.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionsService { // Public service class for managing transaction operations

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private ReservationsRepository reservationsRepository;

    // Creates a new transaction based on DTO data
    public TransactionsDTO createTransaction(TransactionsDTO transactionsDTO) {
        Transaction transaction = TransactionsMapper.mapFromDTO(transactionsDTO, reservationsRepository);
        transaction = transactionsRepository.save(transaction);
        return TransactionsMapper.mapFromModel(transaction);
    }

    // Retrieves a transaction by its ID
    public TransactionsDTO getTransactionById(Integer id) {
        Optional<Transaction> transaction = transactionsRepository.findById(id);
        return transaction.map(TransactionsMapper::mapFromModel).orElse(null);
    }

    // Retrieves all transactions
    public List<TransactionsDTO> getAllTransactions() {
        return transactionsRepository.findAll().stream()
                .map(TransactionsMapper::mapFromModel)
                .collect(Collectors.toList());
    }

    // Updates an existing transaction based on ID and DTO data
    public TransactionsDTO updateTransaction(Integer id, TransactionsDTO transactionsDTO) {
        Optional<Transaction> transactionOptional = transactionsRepository.findById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            transaction.setReservation(reservationsRepository.findById(transactionsDTO.reservationId()).orElseThrow());
            transaction.setAmount(transaction.getReservation().getTotalCost());
            transaction.setTransactionDate(Instant.now());
            transaction.setPaymentMethod(transactionsDTO.paymentMethods());
            transaction = transactionsRepository.save(transaction);
            return TransactionsMapper.mapFromModel(transaction);
        } else {
            return null;
        }
    }

    // Deletes a transaction by its ID
    public void deleteTransaction(Integer id) {
        transactionsRepository.deleteById(id);
    }

}
