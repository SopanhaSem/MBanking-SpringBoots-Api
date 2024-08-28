package co.istad.springbankingapi.features.account.dto;

public record AccountTypeResponse(
        String name,
        String alias,
        String description
) {
}
