package cue.edu.co.velocerentals.controllers;

import cue.edu.co.velocerentals.mapping.dto.TransactionsDTO;
import cue.edu.co.velocerentals.service.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController { // Public class handling REST endpoints for transaction-related operations

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("create")
    public Map<String, String> createTransaction(@RequestBody @Valid TransactionsDTO transactionsDTO, BindingResult result) {
        // Creates a new transaction, returns validation errors if any, or success message
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return errors;
        }
        transactionsService.createTransaction(transactionsDTO);
        return Map.of("message", "Transaction created successfully");
    }

    @GetMapping("search/{id}")
    public ResponseEntity<TransactionsDTO> getTransactionById(@PathVariable Integer id) {
        // Retrieves a transaction by its ID
        TransactionsDTO transaction = transactionsService.getTransactionById(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<TransactionsDTO>> getAllTransactions() {
        // Retrieves all transactions
        List<TransactionsDTO> transactions = transactionsService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TransactionsDTO> updateTransaction(@PathVariable Integer id, @RequestBody TransactionsDTO transactionsDTO) {
        // Updates an existing transaction, returns the updated transaction or not found status
        TransactionsDTO updatedTransaction = transactionsService.updateTransaction(id, transactionsDTO);
        return updatedTransaction != null ? ResponseEntity.ok(updatedTransaction) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        // Deletes a transaction by its ID
        transactionsService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
