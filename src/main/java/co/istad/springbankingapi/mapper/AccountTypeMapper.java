package co.istad.springbankingapi.mapper;

import co.istad.springbankingapi.domain.AccountType;
import co.istad.springbankingapi.features.account.dto.AccountTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    List<AccountTypeResponse> toAccountTypeResponse(List<AccountType> accountTypes);
}
