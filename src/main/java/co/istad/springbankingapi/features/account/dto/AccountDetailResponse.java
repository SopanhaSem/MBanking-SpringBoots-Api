package co.istad.springbankingapi.features.account.dto;

import co.istad.springbankingapi.domain.Account;

import java.math.BigDecimal;

public record AccountDetailResponse(
        String aliasName,
        String accNo,
        BigDecimal balance,
        BigDecimal transferLimits,
        Boolean isHidden,
        String accountTypeName

) {

}
