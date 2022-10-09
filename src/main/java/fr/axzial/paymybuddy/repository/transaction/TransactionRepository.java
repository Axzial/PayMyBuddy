package fr.axzial.paymybuddy.repository.transaction;

import fr.axzial.paymybuddy.model.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Page<Transaction> findAllByWebUserInitiatorUuidOrWebUserReceiverUuid(Pageable pageable, UUID initiator, UUID receiver);
}
