package dev.inca.springsecuritydemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.http.HttpMethod.GET;

@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    /**
     * This method is used for configuring the <b>authentication</b> configuration in our API.
     * As an example, we are using the in-memory approach. We are also hard coding the user credentials.
     * TODO: We should expand on this example to avoid hard coding user credentials.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("test")
                .password("test")
                .roles("USER");
    }

    /**
     * This bean is needed to <b>encode</b> our password.
     * If we don't specified it, then spring boot will throw an error.
     * As an example, we are using the no operations encoder. This means our password are being passed in plain text.
     * This is a bad practice. This MUST NOT be used for real API's.
     * TODO: Use a different encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * This method is used for the <b>authorization</b> configuration.
     * We will write code to allow specific roles to access specific paths.
     * TODO: Is there a way to configure this dynamically instead of hard coding it?
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(
                        auth ->
                                auth
                                        .antMatchers(GET, "/hello/world")
                                        .permitAll()
                                        .mvcMatchers(GET, "custom/{name}")
                                        .hasRole("USER")

                                  )
                .authorizeRequests()
                .anyRequest()
                .permitAll()

                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

}
