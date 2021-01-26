package ru.tkachenko.ecare.models.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.tkachenko.ecare.models.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum Role {
    ADMIN,
    USER;

    Role() {
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Client client) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(client.getRole().name()));
        return list;
    }
}
