package fr.axzial.paymybuddy.dto.transaction;

import fr.axzial.paymybuddy.model.transaction.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionDTO {

    BigDecimal amountUsd;

    String receiver;

    TransactionType transactionType;

}
