package com.protobin.authservice.service.impl;

import com.protobin.authservice.entity.UserDetailsImpl;
import com.protobin.authservice.entity.UserEntity;
import com.protobin.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.protobin.authservice.utils.ExceptionUtils.getEntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var found = userRepository.findByEmail(username)
                .orElseThrow(() -> getEntityNotFoundException(UserEntity.class));

        return new UserDetailsImpl(found);
    }
}
