package org.example.com.marjoz.card.domain

import org.example.com.marjoz.card.domain.dto.*
import org.example.com.marjoz.card.domain.dto.toCardEntity
import org.example.com.marjoz.card.domain.exception.CardDomainException

class CardFacade internal constructor(private val cardRepository: CardRepository) {

    fun createCard(cardRequestDto: CardRequestDto) : CardResponseDto {
        return cardRepository.save(cardRequestDto.toCardEntity()).toCardResponseDto()
    }

    fun fetchCardDetails(customerEmail: String) : CardResponseDto {
        val card = cardRepository.findByCustomerEmail(customerEmail) ?: throw CardDomainException("Card with customer email: $customerEmail does not exist.")

        return card.toCardResponseDto()
    }

    fun updateCardDetails(cardRequestDto: CardRequestDto) {
        val card = cardRepository.findByCardNumber(cardRequestDto.cardNumber) ?: throw CardDomainException("Card with card number: ${cardRequestDto.cardNumber} does not exist")
        val updatedCard = card.copy(customerEmail = cardRequestDto.customerEmail,
                                    cardType = Card.CardType.valueOf(cardRequestDto.cardType),
                                    totalLimit = cardRequestDto.totalLimit,
                                    availableAmount = cardRequestDto.availableAmount)

        cardRepository.save(updatedCard)
    }

    fun deleteCard(customerEmail: String) {
        val card = cardRepository.findByCustomerEmail(customerEmail) ?: throw CardDomainException("Card with customer email: $customerEmail does not exist.")

        cardRepository.deleteById(card.cardId)
    }
}