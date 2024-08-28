package co.istad.springbankingapi.mapper;

import co.istad.springbankingapi.domain.Account;
import co.istad.springbankingapi.features.account.dto.AccountDetailResponse;
import co.istad.springbankingapi.features.account.dto.CreateAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreateAccountMapper {
    Account fromCreateAccountRequestMapper(CreateAccountRequest createAccountRequest);
    @Mapping(source = "cards.cardTypes.name",target = "accountTypeName")
    AccountDetailResponse toAccountDetailResponse(Account account);
}
