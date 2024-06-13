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
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private ReservationsRepository reservationsRepository;

    public TransactionsDTO createTransaction(TransactionsDTO transactionsDTO) {
        Transaction transaction = TransactionsMapper.mapFromDTO(transactionsDTO, reservationsRepository);
        transaction = transactionsRepository.save(transaction);
        return TransactionsMapper.mapFromModel(transaction);
    }

    public TransactionsDTO getTransactionById(Integer id) {
        Optional<Transaction> transaction = transactionsRepository.findById(id);
        return transaction.map(TransactionsMapper::mapFromModel).orElse(null);
    }

    public List<TransactionsDTO> getAllTransactions() {
        return transactionsRepository.findAll().stream()
                .map(TransactionsMapper::mapFromModel)
                .collect(Collectors.toList());
    }

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

    public void deleteTransaction(Integer id) {
        transactionsRepository.deleteById(id);
    }

}
