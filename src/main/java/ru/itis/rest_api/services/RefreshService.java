package ru.itis.rest_api.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import ru.itis.rest_api.models.User;
import ru.itis.rest_api.security.token.TokensUtil;

import java.util.List;

public interface RefreshService {

    boolean timeCheck(DecodedJWT decodedJWT, long limit);

    boolean accessCheck(String accessToken);
    boolean refreshCheck(String refreshToken);

    List<String> generateTokens(User user);

}
