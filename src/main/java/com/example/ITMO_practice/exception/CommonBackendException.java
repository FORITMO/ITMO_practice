package com.example.ITMO_practice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class CommonBackendException extends RuntimeException {
    private final String message;
    private final HttpStatus status;

}
