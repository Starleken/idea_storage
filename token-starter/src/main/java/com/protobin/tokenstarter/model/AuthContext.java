package com.protobin.tokenstarter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
public class AuthContext {

    private final String userLogin;
    private final String accessToken;

    public AuthContext(String userLogin, String accessToken) {
        this.userLogin = userLogin;
        this.accessToken = accessToken;
    }
}
