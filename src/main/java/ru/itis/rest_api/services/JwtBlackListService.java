package ru.itis.rest_api.services;

public interface JwtBlackListService {
    void add(String token);

    boolean exists(String token);

    void deleteToken(String header);
}
