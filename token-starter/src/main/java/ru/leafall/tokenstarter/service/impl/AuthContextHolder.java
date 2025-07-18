package ru.leafall.tokenstarter.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthContextHolder {
    private static final ThreadLocal<UserDetails> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void set(UserDetails context) {
        CONTEXT_HOLDER.set(context);
    }

    public static UserDetails get() {
        return CONTEXT_HOLDER.get();
    }

    public static void remove() {
        CONTEXT_HOLDER.remove();
    }
}
