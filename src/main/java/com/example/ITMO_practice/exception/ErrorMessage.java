package com.example.ITMO_practice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorMessage {
    private String message;

    public ErrorMessage(String format) {
    }
}
