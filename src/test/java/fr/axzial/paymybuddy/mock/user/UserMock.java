package fr.axzial.paymybuddy.mock.user;

import fr.axzial.paymybuddy.model.user.WebUser;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.assertj.core.util.Sets;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class UserMock {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public List<WebUser> users = Arrays.asList(
            WebUser.builder()
                    .uuid(UUID.randomUUID())
                    .email("user1@test.com")
                    .name("User1")
                    .password(bCryptPasswordEncoder.encode("testing1"))
                    .rawPassword("testing1")
                    .enabled(true)
                    .authority(WebUser.WebUserAuthority.USER)
                    .walletUsd(new BigDecimal("859.00"))
                    .build(),
            WebUser.builder()
                    .uuid(UUID.randomUUID())
                    .email("user2@test.com")
                    .name("User2")
                    .password(bCryptPasswordEncoder.encode("testing2"))
                    .enabled(true)
                    .authority(WebUser.WebUserAuthority.USER)
                    .walletUsd(new BigDecimal("548.00"))
                    .build(),
            WebUser.builder()
                    .uuid(UUID.randomUUID())
                    .email("user3@test.com")
                    .name("User3")
                    .password(bCryptPasswordEncoder.encode("testing3"))
                    .enabled(true)
                    .authority(WebUser.WebUserAuthority.USER)
                    .walletUsd(new BigDecimal("658.00"))
                    .build(),
            WebUser.builder()
                    .uuid(UUID.randomUUID())
                    .email("admin1@test.com")
                    .name("Admin1")
                    .password(bCryptPasswordEncoder.encode("administrating"))
                    .enabled(true)
                    .authority(WebUser.WebUserAuthority.ADMIN)
                    .walletUsd(new BigDecimal("742.00"))
                    .build()
    );

}
