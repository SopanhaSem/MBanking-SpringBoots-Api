package co.istad.springbankingapi.features.card;

import co.istad.springbankingapi.features.card.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {
    List<CardTypeResponse> findAll();
}
