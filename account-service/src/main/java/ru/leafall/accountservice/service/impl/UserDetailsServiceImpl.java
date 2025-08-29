package ru.leafall.accountservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.leafall.accountservice.entity.UserDetailsImpl;
import ru.leafall.accountservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var foundUser = userRepository.findByEmail(username).orElseThrow(()->
                new UsernameNotFoundException(String.format("User with mail '%s' is not found", username))
        );

        return new UserDetailsImpl(foundUser);
    }
}
