package dev.inca.springsecuritydemo.account;

import dev.inca.springsecuritydemo.configuration.AnotherSpringBean;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {AccountController.class})
@ImportAutoConfiguration(classes = {AnotherSpringBean.class})
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllAccounts_shouldReturnOk_givenGetRequest() {
        mockMvc
                .perform(
                        get("/account")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accounts.length()", equalTo(2)));
    }

}
