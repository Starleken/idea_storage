package ru.leafall.tokenstarter.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final Collection<SimpleGrantedAuthority> authorities;
    private final String username;
    private final Long id;

    public UserDetailsImpl(Collection<SimpleGrantedAuthority> authorities, String username, Long id) {
        this.authorities = authorities;
        this.username = username;
        this.id = id;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
}
