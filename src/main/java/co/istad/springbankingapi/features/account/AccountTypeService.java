package co.istad.springbankingapi.features.account;

import co.istad.springbankingapi.features.account.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findAll();
}
