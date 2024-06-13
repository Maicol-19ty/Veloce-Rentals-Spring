package cue.edu.co.velocerentals.controllers;

import cue.edu.co.velocerentals.mapping.dto.ReservationsDTO;
import cue.edu.co.velocerentals.mapping.dto.TransactionsDTO;
import cue.edu.co.velocerentals.service.ReservationsService;
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
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("create")
    public Map<String, String> createTransactions(@RequestBody @Valid TransactionsDTO transactionsDTO, BindingResult result) {
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
        TransactionsDTO transaction = transactionsService.getTransactionById(id);
        return transaction!= null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<TransactionsDTO>> getAllTransactions() {
        List<TransactionsDTO> transactions = transactionsService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TransactionsDTO> updateTransaction(@PathVariable Integer id, @RequestBody TransactionsDTO TransactionDTO) {
        TransactionsDTO updatedTransaction = transactionsService.updateTransaction(id, TransactionDTO);
        return updatedTransaction != null ? ResponseEntity.ok(updatedTransaction) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        transactionsService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

}
