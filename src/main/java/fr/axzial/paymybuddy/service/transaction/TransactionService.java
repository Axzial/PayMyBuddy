package fr.axzial.paymybuddy.service.transaction;

import fr.axzial.paymybuddy.dto.transaction.CreateTransactionDTO;
import fr.axzial.paymybuddy.dto.transaction.TransactionDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TransactionService {
    TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO);

    Page<TransactionDTO> getSelfTransactions(int page, int size);

    TransactionDTO getTransaction(UUID uuid);
}
