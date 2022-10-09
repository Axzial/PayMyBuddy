package fr.axzial.paymybuddy.service.user;

import fr.axzial.paymybuddy.auth.utils.JwtUtils;
import fr.axzial.paymybuddy.dto.user.PublicWebUserDTO;
import fr.axzial.paymybuddy.dto.user.WebUserDTO;
import fr.axzial.paymybuddy.exception.types.user.WebUserNotFoundException;
import fr.axzial.paymybuddy.exception.types.user.details.UserDetailsNotFoundException;
import fr.axzial.paymybuddy.mapper.user.WebUserMapper;
import fr.axzial.paymybuddy.mapper.user.WebUserPublicMapper;
import fr.axzial.paymybuddy.model.user.WebUser;
import fr.axzial.paymybuddy.repository.user.WebUserRepository;
import fr.axzial.paymybuddy.utils.SpringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WebUserServiceImpl implements WebUserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final WebUserRepository webUserRepository;
    private final WebUserMapper webUserMapper;
    private final WebUserPublicMapper webUserPublicMapper;

    @PostConstruct
    public void seed() {
        if (findByEmail("axzial@protonmail.com").isEmpty()) createDefaultWebUser("axzial@protonmail.com", "Axzial", "12345678");
        if (findByEmail("axzial+test@protonmail.com").isEmpty()) createDefaultWebUser("axzial+test@protonmail.com", "AxzialTest", "12345678");
    }

    @Override
    public WebUser getSelfUser() {
        UUID uuid = UUID.fromString(JwtUtils.getCurrentUserDetails().getUsername());
        return findById(uuid).orElseThrow(WebUserNotFoundException::new);
    }

    @Override
    public Optional<WebUser> findByEmail(String email) {
        return webUserRepository.findByEmail(email);
    }

    @Override
    public Optional<WebUser> findByUUID(UUID uuid) {
        return webUserRepository.findById(uuid);
    }

    @Override
    public Optional<WebUser> findByUsername(String username) {
        return webUserRepository.findByName(username);
    }

    @Override
    public Optional<WebUser> findByUsernameOrEmail(String username, String email) {
        return webUserRepository.findByNameOrEmail(username, email);
    }

    public Optional<WebUser> findById(UUID id) {
        log.info("Finding by id : {}", id);
        log.info(webUserRepository.findAll().toString());
        return webUserRepository.findById(id);
    }

    @Override
    public WebUser save(WebUser webUser) {
        return webUserRepository.save(webUser);
    }

    // Creators
    @Override
    public WebUser createDefaultWebUser(String email, String username, String password) {
        WebUser webUser = new WebUser();
        webUser.setName(username);
        webUser.setEmail(email);
        webUser.setPassword(passwordEncoder.encode(password));
        webUser.setEnabled(true);
        webUser.setAuthority(WebUser.WebUserAuthority.USER);
        webUser.setWalletUsd(new BigDecimal("0.00"));
        return save(webUser);
    }

    @Override
    public Page<PublicWebUserDTO> searchContacts(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WebUser> results = webUserRepository.findAllByNameLikeOrEmailLike(pageable, SpringUtils.likeContains(query), SpringUtils.likeContains(query));
        return results.map(webUserPublicMapper::toDto);
    }

    @Override
    public void deleteByEmail(String email){
        webUserRepository.deleteByEmail(email);
    }

    // DTO

    @Override
    public WebUserDTO getSelfUserDTO() {
        return webUserMapper.toDto(getSelfUser());
    }

    @Override
    public WebUserDTO getUserDTO(UUID uuid) {
        WebUser webUser = findById(uuid).orElseThrow(WebUserNotFoundException::new);
        return webUserMapper.toDto(webUser);
    }

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        log.info(uuid);
        return findById(UUID.fromString(uuid)).orElseThrow(UserDetailsNotFoundException::new);
    }


}
