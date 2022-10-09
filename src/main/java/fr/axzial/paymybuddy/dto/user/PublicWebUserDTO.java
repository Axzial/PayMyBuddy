package fr.axzial.paymybuddy.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class PublicWebUserDTO {

    private UUID uuid;

    private String email;

    private String name;

    private String picture;

}
