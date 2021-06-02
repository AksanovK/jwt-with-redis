package ru.itis.rest_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.rest_api.repositories.BlackListRepository;
import ru.itis.rest_api.repositories.TokensRepository;

import javax.transaction.Transactional;

@Service
public class JwtBlackListServiceImpl implements JwtBlackListService {

    @Autowired
    public BlackListRepository blackListRepository;

    @Autowired
    public TokensRepository tokensRepository;

    @Override
    public void add(String token) {
        blackListRepository.save(token);
    }

    @Override
    public boolean exists(String token) {
        return blackListRepository.exists(token);
    }

    @Override
    @Transactional
    public void deleteToken(String header) {
        tokensRepository.deleteByRefreshToken(header);
    }
}
