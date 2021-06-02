package ru.itis.rest_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rest_api.dto.EmailPasswordDto;
import ru.itis.rest_api.dto.TokenDto;
import ru.itis.rest_api.services.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody EmailPasswordDto emailPassword) throws Throwable {
        return ResponseEntity.ok(loginService.login(emailPassword));
    }

}
