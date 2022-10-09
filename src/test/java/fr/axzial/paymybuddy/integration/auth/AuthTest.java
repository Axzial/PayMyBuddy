package fr.axzial.paymybuddy.integration.auth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.axzial.paymybuddy.auth.utils.JwtUtils;
import fr.axzial.paymybuddy.dto.auth.AuthTokenDTO;
import fr.axzial.paymybuddy.dto.auth.login.AuthLoginDTO;
import fr.axzial.paymybuddy.dto.auth.register.AuthRegisterDTO;
import fr.axzial.paymybuddy.mock.user.UserMock;
import fr.axzial.paymybuddy.model.user.WebUser;
import fr.axzial.paymybuddy.repository.user.WebUserRepository;
import fr.axzial.paymybuddy.service.user.WebUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class AuthTest {

    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private WebUserService webUserService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @BeforeEach
    void beforeAll() {
        webUserRepository.deleteAll();
        webUserRepository.saveAll(UserMock.users);
    }

    @SneakyThrows
    @Test
    void login() {
        WebUser mockUser = UserMock.users.get(0);
        AuthLoginDTO dto = new AuthLoginDTO(mockUser.getEmail(), mockUser.getRawPassword());
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(dto)))
                .andExpect(status().isAccepted());
    }

    @SneakyThrows
    @Test
    void loginBadCredentials() {
        WebUser mockUser = UserMock.users.get(0);
        AuthLoginDTO dto = new AuthLoginDTO(mockUser.getEmail(), "notmypassword");
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(dto)))
                .andExpect(status().isUnauthorized());
    }

    @SneakyThrows
    @Test
    void register() {
        WebUser mockUser = UserMock.users.get(0);
        AuthRegisterDTO dto = new AuthRegisterDTO(mockUser.getEmail(), mockUser.getRawPassword(), mockUser.getUsername());
        webUserService.deleteByEmail(mockUser.getEmail());
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(dto)))
                .andExpect(status().isAccepted());
    }


}
