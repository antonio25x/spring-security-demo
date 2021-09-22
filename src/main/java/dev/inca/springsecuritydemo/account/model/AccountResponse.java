package dev.inca.springsecuritydemo.account.model;

import lombok.Data;

import java.util.List;

@Data
public class AccountResponse {
    private List<Account> accounts;
}
