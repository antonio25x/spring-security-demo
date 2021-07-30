package dev.inca.springsecuritydemo.hello;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @GetMapping(path = "world")
    public String hello() {
        return "Hello World";
    }

    @GetMapping(
            path = "custom/{name}", //hello/{name}
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CustomHelloResponse> sayHello(@PathVariable String name) {
        return ResponseEntity.ok(new CustomHelloResponse(name));
    }
}
