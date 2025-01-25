package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Card;

public class CardMapper {

    public static CardDto mapToCardDto(Card cardEntity, CardDto cardDto) {
        cardDto.setMobileNumber(cardEntity.getMobileNumber());
        cardDto.setCardNumber(cardEntity.getCardNumber());
        cardDto.setCardType(cardEntity.getCardType());
        cardDto.setTotalLimit(cardEntity.getTotalLimit());
        cardDto.setAvailableAmount(cardEntity.getAvailableAmount());
        cardDto.setAmountUsed(cardEntity.getAmountUsed());
        return cardDto;
    }

    public static Card mapToCard(CardDto cardDto, Card cardEntity) {
        cardEntity.setMobileNumber(cardDto.getMobileNumber());
        cardEntity.setCardNumber(cardDto.getCardNumber());
        cardEntity.setCardType(cardDto.getCardType());
        cardEntity.setTotalLimit(cardDto.getTotalLimit());
        cardEntity.setAvailableAmount(cardDto.getAvailableAmount());
        cardEntity.setAmountUsed(cardDto.getAmountUsed());
        return cardEntity;
    }
}
