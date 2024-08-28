package co.istad.springbankingapi.features.account;

import co.istad.springbankingapi.features.account.dto.AccountDetailResponse;
import co.istad.springbankingapi.features.account.dto.CreateAccountRequest;

public interface AccountService {
    void createAccount(CreateAccountRequest createAccountRequest);
    AccountDetailResponse findByAccNo(String accNo);
}
