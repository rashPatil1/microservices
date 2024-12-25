package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardDto;

public interface ICardService {

    void saveCardDetails(String  mobileNumber);
    CardDto fetchCardDetails(String mobileNumber);

    boolean updateCardDetails(CardDto cardDto);

    boolean deleteCardDetails(String mobileNumber);
}
