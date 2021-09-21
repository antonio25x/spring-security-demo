package dev.inca.springsecuritydemo.hello.exception;

import lombok.Data;

@Data
public class MissingFieldError {

    private String errorDescription;
}
