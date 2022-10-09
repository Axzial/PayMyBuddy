package fr.axzial.paymybuddy.dto.user;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WebUserDTO {

    private UUID uuid;

    private String email;

    private String name;

    private BigDecimal walletUsd;

    private String picture;

}
