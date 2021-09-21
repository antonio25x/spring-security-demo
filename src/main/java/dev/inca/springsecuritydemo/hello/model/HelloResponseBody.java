package dev.inca.springsecuritydemo.hello.model;

import lombok.Data;

@Data
public class HelloResponseBody {
    private String name;
    private String lastName;
}
