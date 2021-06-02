package ru.itis.rest_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessTokenIncorrect extends RuntimeException {
    public AccessTokenIncorrect() {
        super("Access token expired");
    }
}
