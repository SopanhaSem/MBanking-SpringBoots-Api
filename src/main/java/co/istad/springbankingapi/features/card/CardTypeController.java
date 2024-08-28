package co.istad.springbankingapi.features.card;

import co.istad.springbankingapi.features.card.dto.CardTypeResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card-types")
@RequiredArgsConstructor
public class CardTypeController {
    private final CardTypeService cardTypeService;

    @RequestMapping
    List<CardTypeResponse> findAll(){
        return cardTypeService.findAll();
    }
}
