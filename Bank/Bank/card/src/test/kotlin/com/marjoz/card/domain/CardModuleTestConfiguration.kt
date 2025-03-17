package com.marjoz.card.domain

import org.example.com.marjoz.card.domain.CardFacade
import org.example.com.marjoz.card.domain.CardModuleConfiguration

internal class CardModuleTestConfiguration {

    private val cardModuleConfiguration = CardModuleConfiguration()

    internal fun cardFacade(inMemoryCardRepository: InMemoryCardRepository) : CardFacade {
        return cardModuleConfiguration.cardFacade(inMemoryCardRepository)
    }

    internal fun inMemoryCardRepository() : InMemoryCardRepository {
        return InMemoryCardRepository()
    }
}