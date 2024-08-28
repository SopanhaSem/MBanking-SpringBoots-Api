package co.istad.springbankingapi.features.card;

import co.istad.springbankingapi.domain.CardType;
import co.istad.springbankingapi.features.card.dto.CardTypeResponse;
import co.istad.springbankingapi.mapper.CardTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTypeServiceImpl implements CardTypeService{
    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;
    @Override
    public List<CardTypeResponse> findAll() {
        List<CardType> cardTypes = cardTypeRepository.findAll();
        return cardTypeMapper.toCardTypeResponseMapper(cardTypes);
    }
}
