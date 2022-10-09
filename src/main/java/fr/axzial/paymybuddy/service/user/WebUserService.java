package fr.axzial.paymybuddy.service.user;

import fr.axzial.paymybuddy.dto.user.PublicWebUserDTO;
import fr.axzial.paymybuddy.dto.user.WebUserDTO;
import fr.axzial.paymybuddy.model.user.WebUser;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface WebUserService {
    WebUser getSelfUser();

    Optional<WebUser> findByEmail(String email);

    Optional<WebUser> findByUUID(UUID uuid);

    Optional<WebUser> findByUsername(String username);

    Optional<WebUser> findByUsernameOrEmail(String username, String email);

    WebUser save(WebUser webUser);

    WebUser createDefaultWebUser(String email, String username, String password);

    Page<PublicWebUserDTO> searchContacts(String query, int page, int size);

    void deleteByEmail(String email);

    WebUserDTO getSelfUserDTO();

    WebUserDTO getUserDTO(UUID id);
}
