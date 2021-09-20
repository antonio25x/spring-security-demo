package dev.inca.springsecuritydemo.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        MissingFieldError error = new MissingFieldError();
        String errorDescription = ex.getFieldErrors()
                .stream()
                .map(field -> String.format("%s %s", field.getField(), field.getDefaultMessage()))
                .sorted()
                .collect(Collectors.joining(", "));

        error.setErrorDescription(errorDescription);

        return new ResponseEntity<>(error, status);
    }
}
