package co.istad.springbankingapi.mapper;

import co.istad.springbankingapi.domain.CardType;
import co.istad.springbankingapi.features.card.dto.CardTypeResponse;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {
    List<CardTypeResponse> toCardTypeResponseMapper(List<CardType> cardTypes);
}
