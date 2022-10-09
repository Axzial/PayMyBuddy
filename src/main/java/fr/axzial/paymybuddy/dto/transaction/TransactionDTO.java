package fr.axzial.paymybuddy.dto.transaction;

import fr.axzial.paymybuddy.dto.user.PublicWebUserDTO;
import fr.axzial.paymybuddy.model.transaction.TransactionType;
import lombok.Data;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TransactionDTO {

    UUID uuid;

    @Nullable
    PublicWebUserDTO webUserInitiator;

    @Nullable
    String bankAddressInitiator;

    BigDecimal amountUsd;

    @Nullable
    PublicWebUserDTO webUserReceiver;

    @Nullable
    String bankAddressReceiver;

    TransactionType transactionType;

    String at;

}
