package fr.axzial.paymybuddy.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SuperBuilder
public class WebUser implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID uuid;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String name;

    private String password;

    @JsonIgnore
    private transient String rawPassword;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private WebUserAuthority authority;

    private BigDecimal walletUsd;

    private String picture;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(authority.name()));
    }

    @Override
    public String getUsername() {
        return getUuid().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public enum WebUserAuthority {
        ADMIN,
        USER
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WebUser webUser = (WebUser) o;
        return uuid != null && Objects.equals(uuid, webUser.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
