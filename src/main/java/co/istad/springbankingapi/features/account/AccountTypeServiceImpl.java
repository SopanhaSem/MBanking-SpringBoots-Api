package co.istad.springbankingapi.features.account;

import co.istad.springbankingapi.domain.AccountType;
import co.istad.springbankingapi.features.account.dto.AccountTypeResponse;
import co.istad.springbankingapi.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;



    @Override
    public List<AccountTypeResponse> findAll() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        return accountTypeMapper.toAccountTypeResponse(accountTypes);
    }
}
