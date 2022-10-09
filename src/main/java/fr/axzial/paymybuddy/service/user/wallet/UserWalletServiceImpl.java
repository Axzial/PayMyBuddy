package fr.axzial.paymybuddy.service.user.wallet;

import fr.axzial.paymybuddy.model.user.WebUser;
import fr.axzial.paymybuddy.repository.user.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class UserWalletServiceImpl implements UserWalletService {

    private final WebUserRepository webUserRepository;

    @Override
    public WebUser sub(WebUser webUser, BigDecimal bigDecimal){
        webUser.setWalletUsd(webUser.getWalletUsd().subtract(bigDecimal));
        return webUserRepository.save(webUser);
    }

    @Override
    public WebUser add(WebUser webUser, BigDecimal bigDecimal){
        webUser.setWalletUsd(webUser.getWalletUsd().add(bigDecimal));
        return webUserRepository.save(webUser);
    }

}
