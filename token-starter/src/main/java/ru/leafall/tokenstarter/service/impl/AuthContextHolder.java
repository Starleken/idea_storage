package ru.leafall.tokenstarter.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.leafall.tokenstarter.model.UserDetailsImpl;

@Service
public class AuthContextHolder {
    private static final ThreadLocal<UserDetailsImpl> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void set(UserDetailsImpl context) {
        CONTEXT_HOLDER.set(context);
    }

    public static UserDetailsImpl get() {
        return CONTEXT_HOLDER.get();
    }

    public static void remove() {
        CONTEXT_HOLDER.remove();
    }
}
