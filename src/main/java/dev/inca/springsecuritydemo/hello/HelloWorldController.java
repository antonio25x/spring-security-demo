package dev.inca.springsecuritydemo.hello;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @GetMapping(path = "world")
    public String hello() {
        return "Hello World";
    }

    @GetMapping(
            path = "custom/{name}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CustomHelloResponse> sayHello(@PathVariable String name) {
        return ResponseEntity.ok(new CustomHelloResponse(name));
    }


    @GetMapping(
            path = "with-body",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<HelloResponseBody> getWithBody(
            @Valid @RequestBody HelloRequestBody requestBody
                                                        ) {
        HelloResponseBody response = new HelloResponseBody();
        response.setName(requestBody.getName());
        response.setLastName(requestBody.getLastName());

        return ResponseEntity.ok(response);
    }
}
