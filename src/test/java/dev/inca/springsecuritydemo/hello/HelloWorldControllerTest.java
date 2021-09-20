package dev.inca.springsecuritydemo.hello;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloWorldControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    void testHelloWorldRestAPI() throws Exception {
        this.mockMvc.perform(
                get("/hello/world")
                            )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }

    @Test
    @WithMockUser
    void testCustomHelloMessage() throws Exception {
        this.mockMvc
                .perform(
                        get("/hello/custom/luis")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)

                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"message\":\"luis\"")));
    }


    @Test
    @WithMockUser
    void getWithBody_shouldReturnOk_givenValidJsonBody() throws Exception {
        this.mockMvc
                .perform(
                        get("/hello/with-body")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\"name\": \"luis\", \"lastName\": \"test\"}")
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("luis")))
                .andExpect(jsonPath("$.lastName", equalTo("test")));
    }


    @Test
    @WithMockUser
    void getWithBody_shouldReturnBadRequest_givenInvalidJsonBody() throws Exception {
        this.mockMvc
                .perform(
                        get("/hello/with-body")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\"name\": \"\", \"lastName\": \"\"}") // NOTE here that we are sending empty name
                        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorDescription", equalTo("lastName must not be empty, name must not be empty")))
                .andExpect(jsonPath("$..trace").doesNotExist());
    }

}
