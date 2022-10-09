package fr.axzial.paymybuddy.controller.transaction;

import fr.axzial.paymybuddy.dto.transaction.CreateTransactionDTO;
import fr.axzial.paymybuddy.dto.transaction.TransactionDTO;
import fr.axzial.paymybuddy.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionDTO createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO) {
        return transactionService.createTransaction(createTransactionDTO);
    }

    @GetMapping
    public Page<TransactionDTO> getSelfTransactions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        return transactionService.getSelfTransactions(page, size);
    }

    @GetMapping("/{uuid}")
    public TransactionDTO getSelfTransaction(@PathVariable UUID uuid) {
        return transactionService.getTransaction(uuid);
    }

}
