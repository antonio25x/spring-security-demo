package dev.inca.springsecuritydemo.hello.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class HelloRequestBody {

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastName;
}
