package ru.leafall.tokenstarter.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.leafall.tokenstarter.model.Role;
import ru.leafall.tokenstarter.model.UserDetailsImpl;
import ru.leafall.tokenstarter.service.TokenService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
public class AuthContextResolver {

    private final TokenService tokenService;

    public AuthContextResolver(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public UserDetails getContextFromToken(String token) {
        var accessToken = excludeToken(token);

        if (accessToken != null) {
            tokenService.validateToken(accessToken);
            var claims = tokenService.getClaims(accessToken);
            Set<Role> roles = claims.get("rol", Set.class);
            long uid = claims.get("uid", Long.class);
            return new UserDetailsImpl(convert(roles), claims.getSubject(), uid);
        }

        return null;
    }

    private Collection<SimpleGrantedAuthority> convert(Set<Role> roles) {
        var authority = new ArrayList<SimpleGrantedAuthority>(roles.size());
        roles.forEach(role -> authority.add(new SimpleGrantedAuthority(role.name())));
        return authority;
    }

    private String excludeToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }
}