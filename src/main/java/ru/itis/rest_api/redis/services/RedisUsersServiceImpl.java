package ru.itis.rest_api.redis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.rest_api.models.User;
import ru.itis.rest_api.redis.models.RedisUser;
import ru.itis.rest_api.redis.repositories.RedisUsersRepository;
import ru.itis.rest_api.repositories.UsersRepository;
import ru.itis.rest_api.services.JwtBlackListService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RedisUsersServiceImpl implements RedisUsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RedisUsersRepository redisUsersRepository;

    @Autowired
    private JwtBlackListService blackListService;

    @Override
    public void addTokenToUser(User user, String token) {
        String redisId = user.getRedisId();
        RedisUser redisUser;
        if (redisId != null) {
            redisUser = redisUsersRepository.findById(redisId).orElseThrow(IllegalArgumentException::new);
            if (redisUser.getTokens() == null) {
                redisUser.setTokens(new ArrayList<>());
            }
            redisUser.getTokens().add(token);
        } else {
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Collections.singletonList(token))
                    .build();
        }
        redisUsersRepository.save(redisUser);
        user.setRedisId(redisUser.getId());
        usersRepository.save(user);
    }

    @Override
    public void addAllTokensToBlackList(User user) {
        if (user.getRedisId() != null) {
            RedisUser redisUser = redisUsersRepository.findById(user.getRedisId())
                    .orElseThrow(IllegalArgumentException::new);
            List<String> tokens = redisUser.getTokens();
            for (String token : tokens) {
                blackListService.add(token);
            }
            redisUser.getTokens().clear();
            redisUsersRepository.save(redisUser);
        }
    }

    @Override
    public boolean checkToken(String token, User user) {
        RedisUser redisUser = redisUsersRepository.findById(user.getRedisId())
                .orElseThrow(IllegalArgumentException::new);
        boolean result = false;
        if (redisUser != null) {
            List<String> list = redisUser.getTokens();
            for (String str : list) {
                if (str.equals(token)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
