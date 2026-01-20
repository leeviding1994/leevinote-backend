package com.leeviding.leevinote.infrastructure.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    @Getter
    private final HttpStatus status;

    public CustomException(String message) {
        this(message, HttpStatus.OK);
    }

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
