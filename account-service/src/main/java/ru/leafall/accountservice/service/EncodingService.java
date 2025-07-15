package ru.leafall.accountservice.service;

public interface EncodingService {
    String hash(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
