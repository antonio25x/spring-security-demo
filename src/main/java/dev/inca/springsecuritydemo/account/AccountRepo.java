package dev.inca.springsecuritydemo.account;

import dev.inca.springsecuritydemo.account.model.Account;

import java.util.List;

public interface AccountRepo {
    List<Account> getAllAccounts();
}
