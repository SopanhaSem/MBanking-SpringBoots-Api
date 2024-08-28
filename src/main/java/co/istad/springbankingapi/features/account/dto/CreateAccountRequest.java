package co.istad.springbankingapi.features.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateAccountRequest(
        @Size(message = "Account number must be 9 digits",max = 9,min = 3)
        @NotBlank(message = "field is required")
        String accNo,
        @Positive(message = "Balance must be positive")
        BigDecimal balance,
        @NotBlank(message = "field is required")
        String accountTypeAlias,
        @Positive(message = "Phone Number must be positive")
        @NotBlank(message = "field is required")
        String phoneNumber


) {
}
