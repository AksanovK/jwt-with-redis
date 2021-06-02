package ru.itis.rest_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.rest_api.models.User;
import ru.itis.rest_api.redis.services.RedisUsersService;
import ru.itis.rest_api.repositories.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RedisUsersService redisUsersService;

    @Override
    public void blockUser(Long userId) {
        User user = usersRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        redisUsersService.addAllTokensToBlackList(user);
    }
}
