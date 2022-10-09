package fr.axzial.paymybuddy.service.user.wallet;

import fr.axzial.paymybuddy.model.user.WebUser;

import java.math.BigDecimal;

public interface UserWalletService {
    WebUser sub(WebUser webUser, BigDecimal bigDecimal);

    WebUser add(WebUser webUser, BigDecimal bigDecimal);
}
