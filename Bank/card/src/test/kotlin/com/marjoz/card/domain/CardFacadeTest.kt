package com.marjoz.card.domain

import org.example.com.marjoz.card.domain.Card
import org.example.com.marjoz.card.domain.dto.toCardEntity
import org.junit.jupiter.api.AfterEach
import kotlin.test.Test

internal class CardFacadeTest {

    private val cardModuleTestConfiguration = CardModuleTestConfiguration()
    private val inMemoryCardRepository = cardModuleTestConfiguration.inMemoryCardRepository()
    private val cardFacade = cardModuleTestConfiguration.cardFacade(inMemoryCardRepository)
    private val cardDataProvider = CardDataProvider()

    @AfterEach
    internal fun tearDown() {
        inMemoryCardRepository.truncate()
    }

    @Test
    internal fun `should create card`() {
        //given
        val initialCard = cardDataProvider.cardData("DEBIT")

        //when
        val createdCard = cardFacade.createCard(initialCard)

        //then
        assert(inMemoryCardRepository.findByCardNumber(createdCard.cardNumber) != null)
    }

    @Test
    internal fun `should fetch card details`() {
        //given
        val initialCard = cardDataProvider.cardData("DEBIT").toCardEntity()
        inMemoryCardRepository.save(initialCard)

        //when
        val expectedCard = cardFacade.fetchCardDetails(initialCard.customerEmail)

        //then
        assert(expectedCard.cardNumber == initialCard.cardNumber)
    }

    @Test
    internal fun `should update card`() {
        //given
        val updatedCard = cardDataProvider.cardData("CREDIT")
        val initialCard = cardDataProvider.cardData("DEBIT").toCardEntity()
        inMemoryCardRepository.save(initialCard)

        //when
        cardFacade.updateCardDetails(updatedCard)

        //then
        assert(inMemoryCardRepository.findByCardNumber(updatedCard.cardNumber)?.cardType == Card.CardType.CREDIT)
    }

    @Test
    internal fun `should delete card`() {
        //given
        val initialCard = cardDataProvider.cardData("DEBIT").toCardEntity()
        inMemoryCardRepository.save(initialCard)

        //when
        cardFacade.deleteCard(initialCard.customerEmail)

        //then
        assert(inMemoryCardRepository.findByCustomerEmail(initialCard.customerEmail) == null)
    }
}