package com.eazybytes.cards.service;

import com.eazybytes.cards.constants.CardConstants;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Card;
import com.eazybytes.cards.exceptions.CardAlreadyExistException;
import com.eazybytes.cards.exceptions.ResouceNotFoundException;
import com.eazybytes.cards.mapper.CardMapper;
import com.eazybytes.cards.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class CardServiceImpl implements ICardService {

    private CardRepository cardRepository;

    @Override
    public void saveCardDetails(String mobileNumber) {
        Optional<Card> card = cardRepository.findByMobileNumber(mobileNumber);
        if (card.isPresent()) {
            throw new CardAlreadyExistException("Card already exists for Mobile number: " + mobileNumber);
        }

        cardRepository.save(createNewCard(mobileNumber));
    }

    private Card createNewCard(String mobileNumber) {
        Card card = new Card();
        card.setMobileNumber(mobileNumber);
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        card.setCardNumber(Long.toString(randomCardNumber));
        card.setCardType(CardConstants.MASTERCARD);
        card.setTotalLimit(CardConstants.CARD_LIMIT);
        card.setAvailableAmount(CardConstants.CARD_LIMIT);
        card.setAmountUsed(0);
        return card;
    }

    @Override
    public CardDto fetchCardDetails(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResouceNotFoundException("Card", "MobilePhone", mobileNumber)
        );
        return CardMapper.mapToCardDto(card, new CardDto());
    }

    @Override
    public boolean updateCardDetails(CardDto cardDto) {
       Card card =  cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
               () -> new ResouceNotFoundException("Card", "CardNumber", cardDto.getCardNumber())
       );
       cardRepository.save(CardMapper.mapToCard(cardDto, card));
       return true;

    }

    @Override
    public boolean deleteCardDetails(String mobileNumber) {
        int rowsDeleted = cardRepository.deleteByMobileNumber(mobileNumber);
        if (rowsDeleted == 0) {
            throw new ResouceNotFoundException("Card", "MobilePhone", mobileNumber);
        }
        return true;
    }
}
