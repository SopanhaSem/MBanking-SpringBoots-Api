package co.istad.springbankingapi.features.card.dto;

public record CardTypeResponse(
        String alias,
        String name,
        Boolean isDeleted
) {
}
