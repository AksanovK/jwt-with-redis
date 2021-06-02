package ru.itis.rest_api.redis.services;

import ru.itis.rest_api.models.Token;
import ru.itis.rest_api.models.User;

public interface RedisUsersService {
    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);

    boolean checkToken(String token, User user);
}
