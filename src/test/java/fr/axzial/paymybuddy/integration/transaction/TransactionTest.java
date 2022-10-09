package fr.axzial.paymybuddy.integration.transaction;

import fr.axzial.paymybuddy.auth.utils.JwtUtils;
import fr.axzial.paymybuddy.mock.user.UserMock;
import fr.axzial.paymybuddy.model.transaction.Transaction;
import fr.axzial.paymybuddy.model.transaction.TransactionType;
import fr.axzial.paymybuddy.model.user.WebUser;
import fr.axzial.paymybuddy.repository.transaction.TransactionRepository;
import fr.axzial.paymybuddy.repository.user.WebUserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionTest {

    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtUtils jwtUtils;

    List<WebUser> userMocks;

    @BeforeEach
    void beforeEach(){
        webUserRepository.deleteAll();
        userMocks = webUserRepository.saveAll(UserMock.users);
        transactionRepository.deleteAll();
        transactionRepository.saveAll(Arrays.asList(Transaction.builder()
                        .transactionType(TransactionType.PAYMENT)
                        .webUserInitiator(userMocks.get(0))
                        .webUserReceiver(userMocks.get(1))
                        .amountUsd(BigDecimal.valueOf(22.78))
                        .build(),
                Transaction.builder()
                        .transactionType(TransactionType.PAYMENT)
                        .webUserInitiator(userMocks.get(1))
                        .webUserReceiver(userMocks.get(2))
                        .amountUsd(BigDecimal.valueOf(22.78))
                        .build(),
                Transaction.builder()
                        .transactionType(TransactionType.PAYMENT)
                        .webUserInitiator(userMocks.get(2))
                        .webUserReceiver(userMocks.get(0))
                        .amountUsd(BigDecimal.valueOf(22.78))
                        .build()));
    }

    @SneakyThrows
    @Test
    void getTransactions(){
        WebUser mockUser = userMocks.get(0);
        String jwt = jwtUtils.generateJwtToken(mockUser.getUuid());
        mockMvc.perform(get("/transactions").header("Authorization", "Bearer " + jwt))
                .andExpect(status().isOk());
    }

}
