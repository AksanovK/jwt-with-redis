package ru.itis.rest_api.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository {
    void save(String token);

    boolean exists(String token);
}
