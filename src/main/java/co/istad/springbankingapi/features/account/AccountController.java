package co.istad.springbankingapi.features.account;

import co.istad.springbankingapi.features.account.dto.AccountDetailResponse;
import co.istad.springbankingapi.features.account.dto.CreateAccountRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accNo}")
    AccountDetailResponse findAccountNo(@PathVariable String accNo){
            return accountService.findByAccNo(accNo);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        accountService.createAccount(createAccountRequest);
    }

}
