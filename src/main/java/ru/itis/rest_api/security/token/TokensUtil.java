package ru.itis.rest_api.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.rest_api.models.User;
import ru.itis.rest_api.repositories.TokensRepository;

import java.util.Date;


@Data
public class TokensUtil {

    private String accessToken;

    private Date accessTime;

    private Date refreshTime;

    private String refreshToken;

    public TokensUtil(User user) {


        Date date = new Date();

        this.accessToken = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("state", user.getState().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(new Date(date.getTime() + 3))
                .sign(Algorithm.HMAC256("secret"));


        this.refreshToken = JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(new Date(date.getTime() + 604800000))
                .sign(Algorithm.HMAC256("secret"));

        refreshTime = new Date();

    }

}
