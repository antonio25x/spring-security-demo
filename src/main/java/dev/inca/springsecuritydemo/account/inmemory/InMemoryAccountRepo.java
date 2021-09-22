package dev.inca.springsecuritydemo.account.inmemory;

import dev.inca.springsecuritydemo.account.AccountRepo;
import dev.inca.springsecuritydemo.account.model.Account;

import java.util.List;

public class InMemoryAccountRepo implements AccountRepo {

    private List<Account> accounts;

    public InMemoryAccountRepo(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accounts;
    }
}
