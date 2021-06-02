package ru.itis.rest_api.services;

import ru.itis.rest_api.dto.EmailPasswordDto;
import ru.itis.rest_api.dto.TokenDto;

public interface LoginService {
    TokenDto login(EmailPasswordDto emailPassword) throws Throwable;
}
