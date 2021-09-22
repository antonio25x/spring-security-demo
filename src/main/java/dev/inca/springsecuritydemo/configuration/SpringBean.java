package dev.inca.springsecuritydemo.configuration;

import dev.inca.springsecuritydemo.account.AccountRepo;
import dev.inca.springsecuritydemo.account.inmemory.InMemoryAccountRepo;
import dev.inca.springsecuritydemo.account.model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringBean {

    @Bean
    public AccountRepo inMemoryBean() {
        return new InMemoryAccountRepo(List.of(new Account("1")));
    }
}
