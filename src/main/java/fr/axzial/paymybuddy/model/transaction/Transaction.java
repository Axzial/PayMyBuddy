package fr.axzial.paymybuddy.model.transaction;

import fr.axzial.paymybuddy.model.user.WebUser;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * PaymentTransaction Entity
 * Supports Features :
 * BANK -> USER (CASHIN)
 * USER -> USER (PAYMENT)
 * USER -> BANK (CASHOUT)
 */
@Entity
@Data
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    UUID uuid;

    // FROM WEB USER (Could be null if Bank Add)
    @OneToOne
    WebUser webUserInitiator;

    // FROM BANK RIB (Could be null if Web User)
    String bankAddressInitiator;

    // AMOUNT
    BigDecimal amountUsd;

    // TO WEB USER (Could be null if Bank Acc)
    @OneToOne
    WebUser webUserReceiver;

    // TO BANK RIB (Could be null if Web User)
    String bankAddressReceiver;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    Timestamp at;

}
