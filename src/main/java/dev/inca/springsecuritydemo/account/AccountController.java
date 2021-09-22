package dev.inca.springsecuritydemo.account;

import dev.inca.springsecuritydemo.account.model.AccountResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
@AllArgsConstructor
public class AccountController {

    private AccountRepo accountRepo;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> getAllAccounts() {
        AccountResponse response = new AccountResponse();
        response.setAccounts(accountRepo.getAllAccounts());
        return ResponseEntity.ok(response);
    }
}
