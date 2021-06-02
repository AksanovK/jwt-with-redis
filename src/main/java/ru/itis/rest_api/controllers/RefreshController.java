package ru.itis.rest_api.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.itis.rest_api.models.User;
import ru.itis.rest_api.repositories.UsersRepository;
import ru.itis.rest_api.services.RefreshService;


@RestController
public class RefreshController {

    @Autowired
    public RefreshService refreshService;

    @Autowired
    public UsersRepository usersRepository;

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("REFRESH-TOKEN") String requestToken) {
        if (requestToken != null) {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret")).build().verify(requestToken);
            String id = decodedJWT.getSubject();
            User user = usersRepository.findById(Long.parseLong(id))
                    .orElseThrow(() -> new UsernameNotFoundException(id));
            return ResponseEntity.ok(refreshService.generateTokens(user));
        } else {
            return ResponseEntity.ok("Bad token");
        }
    }


}
