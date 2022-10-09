package fr.axzial.paymybuddy.integration.user;

import com.google.gson.Gson;
import fr.axzial.paymybuddy.auth.utils.JwtUtils;
import fr.axzial.paymybuddy.dto.auth.AuthTokenDTO;
import fr.axzial.paymybuddy.dto.auth.login.AuthLoginDTO;
import fr.axzial.paymybuddy.dto.user.PublicWebUserDTO;
import fr.axzial.paymybuddy.dto.user.WebUserDTO;
import fr.axzial.paymybuddy.mock.user.UserMock;
import fr.axzial.paymybuddy.model.user.WebUser;
import fr.axzial.paymybuddy.repository.user.WebUserRepository;
import fr.axzial.paymybuddy.service.user.WebUserService;
import io.jsonwebtoken.Header;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class UserTest {

    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtUtils jwtUtils;

    @BeforeEach
    void beforeAll() {
        webUserRepository.deleteAll();
    }

    @SneakyThrows
    @Test
    void getSelfUser() {
        List<WebUser> userMocks = webUserRepository.saveAll(UserMock.users);

        WebUser mockUser = userMocks.get(0);
        log.info(mockUser.toString());
        String jwt = jwtUtils.generateJwtToken(mockUser.getUuid());
        log.info(jwt);
        log.info(webUserRepository.findAll().toString());
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(mockUser.getEmail()))
                .andReturn();
    }

}
