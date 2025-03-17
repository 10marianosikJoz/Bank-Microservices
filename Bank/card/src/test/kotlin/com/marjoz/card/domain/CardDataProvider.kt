package com.marjoz.card.domain

import org.example.com.marjoz.card.domain.dto.CardRequestDto

internal class CardDataProvider {

    internal fun cardData(cardType: String) : CardRequestDto {
        return CardRequestDto(customerEmail = "example@email",
                              cardNumber = "123456789",
                              cardType = cardType,
                              totalLimit = 1000,
                              availableAmount = 500
        )
    }
}