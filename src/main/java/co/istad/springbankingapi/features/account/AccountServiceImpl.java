package co.istad.springbankingapi.features.account;

import co.istad.springbankingapi.domain.Account;
import co.istad.springbankingapi.domain.AccountType;
import co.istad.springbankingapi.domain.User;
import co.istad.springbankingapi.domain.UserAccount;
import co.istad.springbankingapi.features.account.dto.AccountDetailResponse;
import co.istad.springbankingapi.features.account.dto.CreateAccountRequest;
import co.istad.springbankingapi.features.user.UserRepository;
import co.istad.springbankingapi.mapper.CreateAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CreateAccountMapper createAccountMapper;
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    @Override
    public void createAccount(CreateAccountRequest createAccountRequest) {

        AccountType accountType = accountTypeRepository
                .findByAlias(createAccountRequest.accountTypeAlias())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account type not found"
                ));

        if (accountRepository.existsByAccNo(createAccountRequest.accNo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Account NO already exists");
        }

        User user = userRepository
                .findByPhoneNumber(createAccountRequest.phoneNumber())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Phone number doesn't exist"
                ));


        Account account = createAccountMapper.fromCreateAccountRequestMapper(createAccountRequest);
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setAliasName("");
        account.setAccountTypes(accountType);
        account.setIsHidden(false);
        account.setIsDeleted(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setUsers(user);
        userAccount.setAccounts(account);
        userAccount.setCreateAt(LocalDateTime.now());
        userAccount.setIsDeleted(false);
        userAccount.setIsBlocked(false);

        userAccountRepository.save(userAccount);
    }

    @Override
    public AccountDetailResponse findByAccNo(String accNo) {
        Account account  = accountRepository.findByAccNo(accNo)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account doesn't existed"
                ));
        return createAccountMapper.toAccountDetailResponse(account);
    }
}
