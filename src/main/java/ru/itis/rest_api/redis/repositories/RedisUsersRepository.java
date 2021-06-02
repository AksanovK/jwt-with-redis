package ru.itis.rest_api.redis.repositories;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;
import ru.itis.rest_api.redis.models.RedisUser;

@EnableRedisRepositories
public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}
