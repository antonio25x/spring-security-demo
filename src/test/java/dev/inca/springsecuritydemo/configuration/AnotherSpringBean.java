package dev.inca.springsecuritydemo.configuration;

import dev.inca.springsecuritydemo.account.AccountRepo;
import dev.inca.springsecuritydemo.account.inmemory.InMemoryAccountRepo;
import dev.inca.springsecuritydemo.account.model.Account;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public class AnotherSpringBean {

    @Bean
    public AccountRepo anotherInMemory() {
        return new InMemoryAccountRepo(Arrays.asList(new Account("1"), new Account("2")));
    }
}
