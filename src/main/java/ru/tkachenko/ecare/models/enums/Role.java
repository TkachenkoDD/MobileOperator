package ru.tkachenko.ecare.models.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum Role {
    ADMIN,
    USER;

    Role() { }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(Role.ADMIN.name()));
        list.add(new SimpleGrantedAuthority(Role.USER.name()));
        return list;
    }
}
