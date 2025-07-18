package ru.leafall.accountservice.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var roles = user.getRoles();
        var authority = new ArrayList<SimpleGrantedAuthority>(roles.size());
        roles.forEach(role -> authority.add(new SimpleGrantedAuthority(role.name())));
        return authority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    public Long getId() {
        return user.getId();
    }
}
