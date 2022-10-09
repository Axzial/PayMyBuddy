package fr.axzial.paymybuddy.service.transaction;

import fr.axzial.paymybuddy.dto.transaction.CreateTransactionDTO;
import fr.axzial.paymybuddy.dto.transaction.TransactionDTO;
import fr.axzial.paymybuddy.exception.types.transaction.InsufficientFundsException;
import fr.axzial.paymybuddy.exception.types.transaction.TransactionNotFoundException;
import fr.axzial.paymybuddy.exception.types.transaction.UnauthorizedTransactionException;
import fr.axzial.paymybuddy.exception.types.user.WebUserNotFoundException;
import fr.axzial.paymybuddy.mapper.transaction.TransactionMapper;
import fr.axzial.paymybuddy.model.transaction.Transaction;
import fr.axzial.paymybuddy.model.transaction.TransactionType;
import fr.axzial.paymybuddy.model.user.WebUser;
import fr.axzial.paymybuddy.repository.transaction.TransactionRepository;
import fr.axzial.paymybuddy.service.user.WebUserService;
import fr.axzial.paymybuddy.service.user.wallet.UserWalletService;
import fr.axzial.paymybuddy.utils.MathUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WebUserService webUserService;
    private final TransactionMapper transactionMapper;
    private final UserWalletService userWalletService;

    @Override
    public TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO) {
        WebUser current = webUserService.getSelfUser();
        Transaction.TransactionBuilder rawTransaction = Transaction.builder()
                .amountUsd(createTransactionDTO.getAmountUsd())
                .transactionType(createTransactionDTO.getTransactionType())
                .at(Timestamp.from(Instant.now()));
        return switch (createTransactionDTO.getTransactionType()) {
            case CASH_IN -> createCashInTransaction(rawTransaction, current, createTransactionDTO);
            case CASH_OUT -> createCashOutTransaction(rawTransaction, current, createTransactionDTO);
            default -> createPaymentTransaction(rawTransaction, current, createTransactionDTO);
        };
    }

    public TransactionDTO createCashInTransaction(Transaction.TransactionBuilder rawTransaction, WebUser current, CreateTransactionDTO createTransactionDTO){
        Transaction transaction = rawTransaction
                .webUserReceiver(current)
                .build();
        userWalletService.add(current, createTransactionDTO.getAmountUsd());
        Transaction saved = transactionRepository.save(transaction);
        TransactionDTO transactionDTO = transactionMapper.toDto(saved);
        log.info(saved.toString());
        log.info(transactionDTO.toString());
        return transactionDTO;
    }

    public TransactionDTO createCashOutTransaction(Transaction.TransactionBuilder rawTransaction, WebUser current, CreateTransactionDTO createTransactionDTO){
        if (hasNotFunds(current, createTransactionDTO.getAmountUsd())) {
            throw new InsufficientFundsException();
        }
        Transaction transaction = rawTransaction
                .webUserReceiver(current)
                .build();
        userWalletService.sub(current, createTransactionDTO.getAmountUsd());
        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    public TransactionDTO createPaymentTransaction(Transaction.TransactionBuilder rawTransaction, WebUser current, CreateTransactionDTO createTransactionDTO){
        if (hasNotFunds(current, createTransactionDTO.getAmountUsd())) {
            throw new InsufficientFundsException();
        }
        WebUser receiver = webUserService.findByEmail(createTransactionDTO.getReceiver()).orElseThrow(WebUserNotFoundException::new);
        Transaction transaction = rawTransaction
                .webUserInitiator(current)
                .webUserReceiver(receiver)
                .build();
        userWalletService.sub(current, createTransactionDTO.getAmountUsd());
        userWalletService.add(receiver, createTransactionDTO.getAmountUsd());
        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    public boolean hasNotFunds(WebUser webUser, BigDecimal transactionAmount){
        return !MathUtils.isGreaterOrEqualThan(webUser.getWalletUsd(), transactionAmount);
    }

    @Override
    public Page<TransactionDTO> getSelfTransactions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        WebUser current = webUserService.getSelfUser();
        return transactionRepository
                .findAllByWebUserInitiatorUuidOrWebUserReceiverUuid(pageable, current.getUuid(), current.getUuid())
                .map(transactionMapper::toDto);
    }

    @Override
    public TransactionDTO getTransaction(UUID uuid) {
        WebUser current = webUserService.getSelfUser();
        Transaction transaction = transactionRepository.findById(uuid).orElseThrow(TransactionNotFoundException::new);
        if (transaction.getWebUserInitiator() != null
                && !transaction.getWebUserInitiator().getUuid().equals(current.getUuid())
                || transaction.getWebUserReceiver() != null
                && !transaction.getWebUserReceiver().getUuid().equals(current.getUuid())
        ) {
            throw new UnauthorizedTransactionException();
        }
        return transactionMapper.toDto(transaction);
    }

}
