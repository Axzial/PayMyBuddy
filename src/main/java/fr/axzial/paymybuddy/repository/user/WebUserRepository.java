package fr.axzial.paymybuddy.repository.user;

import fr.axzial.paymybuddy.model.user.WebUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WebUserRepository extends JpaRepository<WebUser, UUID> {
    Optional<WebUser> findByEmail(String email);

    Optional<WebUser> findByName(String username);

    Optional<WebUser> findByNameOrEmail(String username, String email);

    Page<WebUser> findAllByNameLikeOrEmailLike(Pageable pageable, String queryUsername, String queryEmail);

    void deleteByEmail(String email);
}
